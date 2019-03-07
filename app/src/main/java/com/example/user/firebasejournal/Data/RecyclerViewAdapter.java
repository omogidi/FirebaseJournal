package com.example.user.firebasejournal.Data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.firebasejournal.Model.Journal;
import com.example.user.firebasejournal.R;

import java.util.List;

/**
 * Created by User on 2/19/2019.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Journal> journalList;


    public RecyclerViewAdapter(Context context, List<Journal> journalList)
    {
        this.context = context;
        this.journalList = journalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return journalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView journalTitle, dateTitle;
        public ViewHolder(View itemView, Context ctx)
        {
            super(itemView);
            journalTitle = itemView.findViewById(R.id.journal_adapter);
            dateTitle = itemView.findViewById(R.id.date_adapter);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int position = getAdapterPosition();
                    Journal journal = journalList.get(position);
                }
            });
        }
    }
}
