package com.example.as1;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewScoreList  extends RecyclerView.Adapter<RecycleViewScoreList.ViewHolder>{

    private String[] arrayList;

    public RecycleViewScoreList(String[] arrayList) {
        this.arrayList = arrayList;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private TextView txtscores;
       // private Button btnEdit;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }


    @NonNull
    @Override
    public RecycleViewScoreList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_view_score_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewScoreList.ViewHolder holder, int position) {

//        holder.getTextView().setText(arrayList[position]);
        holder.textView3.setText(arrayList[position]);

    }


    @Override
    public int getItemCount() {
        return arrayList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView3;
       // private TextView txtEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView3 = itemView.findViewById(R.id.textView3);

        }


    }
}
