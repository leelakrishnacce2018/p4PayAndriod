package com.example.p4paysecurepayment;

import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    List<studentData> studentDataList;
    FragmentActivity activity;
    ArrayList<String> slider_image_list;
    int pos ;


    public StudentAdapter(List studentDataList, FragmentActivity activity) {
        this.studentDataList = studentDataList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.student_list_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        studentData data = studentDataList.get(position);
        pos = position;
        slider_image_list = new ArrayList<>();
        Random rnd = new Random();
        /*int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        viewHolder.parent.setBackgroundColor(currentColor);*/
        slider_image_list.add(data.datetime);
        viewHolder.datetime.setText(data.datetime);
        viewHolder.transfero.setText(data.transferto);
        viewHolder.transferfrom.setText(data.transferfrom);
        viewHolder.transferamount.setText(data.transferamount);
//        viewHolder.name.setText(data.name);
//        viewHolder.age.setText(String.valueOf(data.age));

    }
    @Override
    public int getItemCount() {
        return studentDataList.size();
    }

    public int getPos(){return pos;}
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView datetime,transfero,transferfrom,transferamount;
        LinearLayout parent;
        public MyViewHolder(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            datetime = itemView.findViewById(R.id.datetime);
            transfero = itemView.findViewById(R.id.transfer_to);
            transferfrom = itemView.findViewById(R.id.transfer_from);
            transferamount = itemView.findViewById(R.id.transfer_amount);
        }
    }
    private void addBottomDots(int currentPage, LinearLayout ll_dots) {
        TextView[] dots = new TextView[slider_image_list.size()];
        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(activity);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#343434"));
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#A2A2A2"));
    }
}
