package com.lambdaschool.congressdetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lambdaschool.congressdataapiaccess.CongresspersonOverview;

import java.util.ArrayList;

public class CongresspeopleAdapter extends RecyclerView.Adapter<CongresspeopleAdapter.ViewHolder> {

    private ArrayList<CongresspersonOverview> congresspersonOverviews = new ArrayList<>();
    private OnCongresspersonClickListener onCongresspersonClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_congressperson, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final CongresspersonOverview congresspersonOverview = congresspersonOverviews.get(i);

        String text = congresspersonOverview.getFirstName() + " " + congresspersonOverview.getLastName() + ", " +
                congresspersonOverview.getParty() + ", " + congresspersonOverview.getState();
        viewHolder.detailsTextView.setText(text);

        viewHolder.detailsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCongresspersonClickListener != null) {
                    onCongresspersonClickListener.onClick(congresspersonOverview);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return congresspersonOverviews.size();
    }

    public void setCongresspersonOverviews(@NonNull ArrayList<CongresspersonOverview> congresspersonOverviews) {
        this.congresspersonOverviews = congresspersonOverviews;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            detailsTextView = itemView.findViewById(R.id.item_congressperson_text_details);
        }

        private TextView detailsTextView;

    }

    public interface OnCongresspersonClickListener {
        void onClick(CongresspersonOverview congresspersonOverview);
    }

    public void setOnCongresspersonClickListener(OnCongresspersonClickListener l) {
        onCongresspersonClickListener = l;
    }
}
