package com.example.p4paysecurepayment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class homeFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    studentData data;
    RecyclerView.LayoutManager manager;
    int i =0;
    LinearLayout Layout_bars;
    TextView[] bottomBars;
    private int visibleItemCount, totalItemCount, firstVisibleItemPosition, lastVisibleItem;


    private List studentDataList = new ArrayList<>();


    public  static  homeFragment newInstance(){
        homeFragment fragment = new homeFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        Layout_bars = (LinearLayout) view.findViewById(R.id.recyclewview_layoutBars);
        studentAdapter = new StudentAdapter(studentDataList,getActivity());
         manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();
        ColoredBars(((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition());
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                ColoredBars(firstVisible);
                //Toast.makeText(getActivity()," sss sss ss  "+String.valueOf(firstVisible),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        //recyclerView.addOnScrollListener(onScrollListener);

        /*recyclerView.post(new Runnable() {
            @Override
            public void run() {
                View p =recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getItemCount());
            }
        });*/
        //Toast.makeText(getActivity(),String.valueOf(getCurrentItem()),Toast.LENGTH_SHORT).show();
        StudentDataPrepare();
        return view;
    }

    private void StudentDataPrepare() {
         data = new studentData("02-April-2020 07.455 am", "Akash Gapth 0457 9663 2456 (Account number)",
                "John Wick 9150 4563 0096 (Card Number)","Rs 5480");
        studentDataList.add(data);
         data = new studentData("02-April-2020 07.455 am", "Akash Gapth 0457 9663 2456 (Account number)",
                "John Wick 9150 4563 0096 (Card Number)","Rs 5480");
        studentDataList.add(data);
         data = new studentData("02-April-2020 07.455 am", "Akash Gapth 0457 9663 2456 (Account number)",
                "John Wick 9150 4563 0096 (Card Number)","Rs 5480");
        studentDataList.add(data);
         data = new studentData("02-April-2020 07.455 am", "Akash Gapth 0457 9663 2456 (Account number)",
                "John Wick 9150 4563 0096 (Card Number)","Rs 5480");
        studentDataList.add(data);
         data = new studentData("02-April-2020 07.455 am", "Akash Gapth 0457 9663 2456 (Account number)",
                "John Wick 9150 4563 0096 (Card Number)","Rs 5480");
        studentDataList.add(data);

    }

    private void ColoredBars(int thisScreen) {
        int[] colorsInactive = getResources().getIntArray(R.array.dot_on_page_not_active_recycleview);
        int[] colorsActive = getResources().getIntArray(R.array.dot_on_page_active_recycleview);
        bottomBars = new TextView[studentAdapter.getItemCount()];
        Layout_bars.removeAllViews();
        for (int i = 0; i < bottomBars.length; i++) {
            bottomBars[i] = new TextView(getActivity());
            bottomBars[i].setTextSize(30);
            bottomBars[i].setText(Html.fromHtml("&#8226;"));
            Layout_bars.addView(bottomBars[i]);
            bottomBars[i].setTextColor(colorsInactive[thisScreen]);
        }
        if (bottomBars.length > 0)
            bottomBars[thisScreen].setTextColor(colorsActive[thisScreen]);
    }

}