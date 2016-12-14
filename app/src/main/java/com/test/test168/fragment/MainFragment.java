package com.test.test168.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.test168.R;
import com.test.test168.activity.ItemListActivity;
import com.test.test168.activity.LitePalActivity;
import com.test.test168.activity.RecyclerViewActivity;
import com.test.test168.activity.RxJavaActivity;
import com.test.test168.activity.SlideActivity;
import com.test.test168.activity.TestIntentServiceActivity;
import com.test.test168.adapter.RecycleViewAdapter;
import com.test.test168.adapter.ViewHolder;

import java.util.Arrays;
import java.util.List;

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
     * this fragment using the provided parameters.
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

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initData();
        return view;
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

        String[] menu = mContext.getResources().getStringArray(R.array.main_menu);

        recyclerView.setAdapter(new RecycleViewAdapter<String>(mContext, Arrays.asList(menu)) {
            @Override
            public int getItemLayoutId() {
                return R.layout.item_main_menu;
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, List<String> list, int position) {
                holder.setText(R.id.list_item, list.get(position));
            }

            @Override
            public void onItemClick(List<String> list, int position) {
                if (position == 7) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BaseDialog);
                    builder.setCustomTitle(new TextView(getActivity()));
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test_dialog, null);
                    builder.setView(view);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else if (position == 8) {
                    TestDialogFragment dialogFragment = new TestDialogFragment();
                    dialogFragment.show(getFragmentManager(), "");
                } else {
                    startActivity(mMoreClass[position]);
                }
            }
        });

    }

    private Class[] mMoreClass = new Class[]{
            SlideActivity.class,
            LitePalActivity.class,
            RxJavaActivity.class,
            RecyclerViewActivity.class,
            null,
            TestIntentServiceActivity.class,
            ItemListActivity.class,
            null,
            null,
    };

    private void startActivity(Class c) {
        if (c == null) return;
        Intent intent = new Intent(mContext, c);
        startActivity(intent);
    }


}
