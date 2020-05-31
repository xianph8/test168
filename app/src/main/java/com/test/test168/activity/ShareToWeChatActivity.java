package com.test.test168.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.test.test168.R;
import com.test.test168.adapter.ShareToWeChatAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xian
 */
public class ShareToWeChatActivity extends AppCompatActivity {

    private static final String TAG = ShareToWeChatActivity.class.getSimpleName();

    private EditText mInputEditText;
    private RecyclerView mSharePicture;
    private ArrayList<Uri> mUriArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_to_we_chat);

        mInputEditText = (EditText) findViewById(R.id.et_share_text);
        mSharePicture = (RecyclerView) findViewById(R.id.rv_share_picture);

        findViewById(R.id.btn_request_permissions).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onClick: ");
                        RxPermissions rxPermissions = new RxPermissions(ShareToWeChatActivity.this);
                        rxPermissions.request(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        Log.i(TAG, "accept: " + aBoolean);
                                        if (aBoolean) {
                                            handleFiles();
                                        }
                                    }
                                });
                    }
                }
        );
        findViewById(R.id.btn_share_to_wechat)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shareToWechat(mUriArrayList, mInputEditText.getText().toString());
                    }
                });
    }

    private void handleFiles() {
        Observable.just(Environment.getExternalStorageDirectory())
                .map(new Function<File, File>() {
                    @Override
                    public File apply(File file) throws Exception {
                        return new File(file.getAbsolutePath() + File.separator + "DCIM" + File.separator + "Camera");
                    }
                })
                .flatMap(new Function<File, ObservableSource<File>>() {
                    @Override
                    public ObservableSource<File> apply(File file) throws Exception {
                        Log.d(TAG, "apply() called with: file = [" + file + "]");
                        Log.i(TAG, "apply: file name " + file.getName());
                        Log.i(TAG, "apply: file exists " + file.exists());
                        Log.i(TAG, "apply: file isDirectory " + file.isDirectory());
                        Log.i(TAG, "apply: file listFiles " + Arrays.toString(file.listFiles()));
                        return Observable.fromArray(file.listFiles());
                    }
                })
                .filter(new Predicate<File>() {
                    @Override
                    public boolean test(File pathname) throws Exception {
                        return pathname.exists() && (pathname.getName().endsWith(".jpg") || pathname.getName().endsWith(".png"));
                    }
                })
                .map(new Function<File, Uri>() {
                    @Override
                    public Uri apply(File file) throws Exception {
                        return Uri.fromFile(file);
                    }
                })
                .takeLast(9)
                .toList()
                .map(new Function<List<Uri>, ArrayList<Uri>>() {
                    @Override
                    public ArrayList<Uri> apply(List<Uri> uris) throws Exception {
                        mUriArrayList = new ArrayList<>();
                        mUriArrayList.addAll(uris);
                        return mUriArrayList;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<Uri>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: disposable " + d);
                    }

                    @Override
                    public void onSuccess(ArrayList<Uri> uris) {
                        Log.i(TAG, "onSuccess: uris : " + uris);
                        showList(uris);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }
                });

    }

    private void showList(ArrayList<Uri> uriArrayList) {
        ShareToWeChatAdapter shareToWeChatAdapter = new ShareToWeChatAdapter(this, uriArrayList) {
            @Override
            public void onItemClick(Uri item, int position) {

            }
        };
        mSharePicture.setLayoutManager(new GridLayoutManager(this, 3));
        mSharePicture.setAdapter(shareToWeChatAdapter);
    }

    private void shareToWechat(ArrayList<Uri> uris, String s) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uris);
        intent.putExtra("Kdescription", s);
        startActivity(intent);
    }

}
