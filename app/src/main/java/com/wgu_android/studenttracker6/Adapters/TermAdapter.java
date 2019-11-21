package com.wgu_android.studenttracker6.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu_android.studenttracker6.Entities.TermEntity;
import com.wgu_android.studenttracker6.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private final List<TermEntity> mTerms;
    private final Context mContext;

    public TermAdapter(List<TermEntity> mTerms, Context mContext) {
        this.mTerms = mTerms;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.term_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //update display of a list item
        final TermEntity term = mTerms.get(position);
        holder.mTextView_TermName.setText(term.getTermName());


    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_Term_Name)
        TextView mTextView_TermName;

        @BindView(R.id.textView_Term_Dates)
        TextView mTextView_TermDates;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
