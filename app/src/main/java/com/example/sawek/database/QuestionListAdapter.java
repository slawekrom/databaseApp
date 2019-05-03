package com.example.sawek.database;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private final TextView QuestionItemView;
        private final TextView wordItemAnswer1;
        private final TextView wordItemAnswer2;
        private final TextView wordItemAnswer3;
        private final TextView wordItemAnswer4;
        private QuestionViewHolder(View itemView) {
            super(itemView);
            QuestionItemView = itemView.findViewById(R.id.textView);
            wordItemAnswer1 = itemView.findViewById(R.id.textView_a1);
            wordItemAnswer2 = itemView.findViewById(R.id.textView_a2);
            wordItemAnswer3 = itemView.findViewById(R.id.textView_a3);
            wordItemAnswer4 = itemView.findViewById(R.id.textView_a4);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle("Select action");
            menu.add(Menu.NONE, 1, 1, "Update");
            menu.add(Menu.NONE, 2, 2, "Delete");
            menu.add(Menu.NONE, 3, 3, "Search");
        }
    }

    private final LayoutInflater mInflater;
    private List<Question> mQuestions; // Cached copy of questions

    QuestionListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final QuestionViewHolder holder, int position) {
        if (mQuestions != null) {
            Question current = mQuestions.get(position);
            holder.QuestionItemView.setText(current.getQuestion());
            holder.wordItemAnswer1.setText(current.getAnswer1());
            holder.wordItemAnswer2.setText(current.getAnswer2());
            holder.wordItemAnswer3.setText(current.getAnswer3());
            holder.wordItemAnswer4.setText(current.getAnswer4());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    setPosition(holder.getPosition());
                    Log.i("positionclick",String.valueOf(holder.getPosition()));
                    return false;
                }
            });


        } else {
            // Covers the case of data not being ready yet.
            holder.QuestionItemView.setText("No Question");
        }
    }


    void setQuestions(List<Question> questions){
        mQuestions = questions;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mQuestions != null)
            return mQuestions.size();
        else return 0;
    }
}