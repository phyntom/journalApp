package com.phyntom.android.journalapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.ViewHolder> {


    private List<DiaryEntry> diaryEntryList;

    private Context context;

    public DiaryEntryAdapter(Context context) {
        this.context=context;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        try {
            final DiaryEntry diaryEntryData = diaryEntryList.get(position);
            holder.textViewDiaryTime.setText(diaryEntryData.getEntryDatetime() + " Time " + diaryEntryData.getEntryTime());
            holder.textViewDiaryContentDescription.setText(diaryEntryData.getContent());
            final Context contexts=holder.itemView.getContext();
            holder.buttonEditEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Intent diaryEntryEditActivity= new Intent(v.getContext(),DiaryEntryActivity.class);
                     diaryEntryEditActivity.putExtra("entry_data",diaryEntryData);
                     v.getContext().startActivity(diaryEntryEditActivity);
                }
            });
            holder.buttonDeleteEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainViewModel viewModel= ViewModelProviders.of((AppCompatActivity)v.getContext()).get(MainViewModel.class);
                    viewModel.deleteEntry(diaryEntryData);
                }
            });
        }
        catch(Exception ex){

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return diaryEntryList.size();
    }


    public void setDiaryEntryList(List<DiaryEntry> diaryEntryList) {
        this.diaryEntryList = diaryEntryList;
    }

    // creating reference to the view  for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDiaryTime;

        public TextView textViewDiaryContentDescription;

        public Button buttonEditEntry;

        public Button buttonDeleteEntry;

        private List<DiaryEntry> diaryEntryList;

        public ViewHolder(View view) {
            super(view);
            textViewDiaryTime = (TextView) view.findViewById(R.id.tv_diary_time);
            textViewDiaryContentDescription = (TextView) view.findViewById(R.id.tv_diary_content_desc);
            buttonEditEntry = (Button) view.findViewById(R.id.action_button_edit);
            buttonDeleteEntry=(Button)view.findViewById(R.id.action_button_delete);
        }

    }
}
