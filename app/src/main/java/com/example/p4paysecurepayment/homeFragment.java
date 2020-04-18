package com.example.p4paysecurepayment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class homeFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    studentData data;
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
        studentAdapter = new StudentAdapter(studentDataList,getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(studentAdapter);
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
}