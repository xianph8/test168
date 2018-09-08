package com.test.test168.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.test168.R;
import com.test.test168.activity.ItemListActivity;
import com.test.test168.activity.LitePalActivity;
import com.test.test168.activity.NineGirdActivity;
import com.test.test168.activity.PanelRecycleViewActivity;
import com.test.test168.activity.RecyclerViewActivity;
import com.test.test168.activity.TestKotlinActivity;
import com.test.test168.activity.TestViewActivity;
import com.test.test168.rx.RxJavaActivity;
import com.test.test168.activity.SlideActivity;
import com.test.test168.activity.TestDialogFragmentActivity;
import com.test.test168.activity.TestIntentServiceActivity;
import com.test.test168.juhe.HealthNewsActivity;
import com.xian.common.adapter.RecycleViewAdapter;
import com.xian.common.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private Context mContext;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fr.agment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_main_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        initFloatButton(view);

        initData();
        return view;
    }

    private void initFloatButton(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void initData() {
        recyclerView.setAdapter(new RecycleViewAdapter<String>(mContext,new ArrayList<String>(menuList.keySet())) {
            @Override
            public int getItemLayoutId() {
                return R.layout.item_main_menu;
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, String list) {
                holder.setText(R.id.list_item, list);
            }

            @Override
            public void onItemClick(String list, int position) {
                startActivity(menuList.get(list));
            }
        });

    }


    private HashMap<String, Class> menuList = new HashMap<String, Class>() {
        {
            put("SlideActivity", SlideActivity.class);
            put("LitePalActivity", LitePalActivity.class);
            put("RxJavaActivity", RxJavaActivity.class);
            put("RecyclerViewActivity", RecyclerViewActivity.class);
            put("SwipeRefreshLayout", null);
            put("TestIntentServiceActivity", TestIntentServiceActivity.class);
            put("ItemListActivity", ItemListActivity.class);
            put("TestDialogFragmentActivity", TestDialogFragmentActivity.class);
            put("TestKotlinActivity", TestKotlinActivity.class);
            put("PanelRecycleViewActivity", PanelRecycleViewActivity.class);
            put("NineGirdActivity", NineGirdActivity.class);
            put("JuheActivity", HealthNewsActivity.class);
            put("TestViewActivity", TestViewActivity.class);
        }
    };

    private void startActivity(Class c) {
        if (c == null) return;
        Intent intent = new Intent(mContext, c);
        startActivity(intent);
    }


}
