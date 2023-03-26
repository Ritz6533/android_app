
package com.example.as1;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdaptorClass  extends RecyclerView.Adapter<RecycleViewAdaptorClass.ViewHolder>{

    private String[] arrayList, date;
    private String USER_ID;

    public RecycleViewAdaptorClass(ArrayList<String> arrayList, String username, ArrayList<String> date) {
        this.arrayList = arrayList.toArray(new String[0]);
        this.USER_ID=username;
        this.date=date.toArray(new String[0]);
        Log.d("MSG","text initial name is "+ USER_ID+ date.get(0));
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }


    @NonNull
    @Override
    public RecycleViewAdaptorClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_view_notepad, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdaptorClass.ViewHolder holder, int position) {

//        holder.getTextView().setText(arrayList[position]);
        String text = arrayList[position];
        String dateis = date[position];
        Log.d("msg", "passed string is "+ text);
        Log.d("msg", "passed data is "+ dateis);


           holder.textView2.setText(text);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NotesView.class);
                intent.putExtra("title", text);
                intent.putExtra("USER_ID", USER_ID);
                intent.putExtra("date", dateis);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView2;
        private Button btnEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView2 = itemView.findViewById(R.id.textTitleView);
            btnEdit = itemView.findViewById(R.id.btnViewTxt);


        }


    }

}
