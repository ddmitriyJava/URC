package com.example.urc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class BookedTicketsAdapter extends BaseAdapter {
    List<BookedTicket> bookedTickets;
    Context context;

    public BookedTicketsAdapter(List<BookedTicket> bookedTickets, Context context) {
        this.bookedTickets = bookedTickets;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bookedTickets.size();
    }

    @Override
    public Object getItem(int i) {
        return bookedTickets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View view1 = LayoutInflater.from(context).inflate(R.layout.booked_ticket_style, viewGroup, false);
        TextView numberTextView = view1.findViewById(R.id.ticket_number);
        TextView timeFromTextView = view1.findViewById(R.id.time_from_textView);
        TextView timeToTextView = view1.findViewById(R.id.time_to_textView);
        TextView fromTextView = view1.findViewById(R.id.from_textView);
        TextView toTextView = view1.findViewById(R.id.to_textView);
        TextView overallTextView = view1.findViewById(R.id.time_overall_textView);

        numberTextView.setText(String.format("КВИТОК №%s", bookedTickets.get(i).getNumber()));
        timeFromTextView.setText(bookedTickets.get(i).getTimeFrom());
        timeToTextView.setText(bookedTickets.get(i).getTimeTo());
        fromTextView.setText(bookedTickets.get(i).getFrom());
        toTextView.setText(bookedTickets.get(i).getTo());
        overallTextView.setText(String.format("%s год.", bookedTickets.get(i).getOverall()));

        return view1;
    }
}
