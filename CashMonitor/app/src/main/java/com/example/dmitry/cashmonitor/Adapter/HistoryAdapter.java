package com.example.dmitry.cashmonitor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dmitry.cashmonitor.Bought;
import com.example.dmitry.cashmonitor.Note;
import com.example.dmitry.cashmonitor.R;

import java.util.ArrayList;

/**
 * Created by Dmitry on 20.08.2016.
 */
public class HistoryAdapter extends BaseAdapter {
    ArrayList<Bought> boughts;
    LayoutInflater inflater;

    public HistoryAdapter(Context context, ArrayList<Bought> boughts){
            this.boughts = boughts;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return boughts.size();
    }

    @Override
    public Object getItem(int position) {
        return boughts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){
            view = inflater.inflate(R.layout.history_custom_view, parent, false);
        }

        final Bought bought = (Bought) getItem(position);

        ((TextView)(view.findViewById(R.id.tvNameHistory)))
                .setText(bought.getName());

        ((TextView)(view.findViewById(R.id.tvCountHistory)))
                .setText(NotesAdapter.doubleToText(bought.getCount()));

        ((TextView)(view.findViewById(R.id.tvTypeCountHistory)))
                .setText(bought.getTypeCount() + "");

        ((TextView)(view.findViewById(R.id.tvPriceForOneHistory)))
                .setText(NotesAdapter.doubleToText(bought.getPriceForOne()));

        ((TextView)(view.findViewById(R.id.tvPriceForAllHistory)))
                .setText(NotesAdapter.doubleToText(bought.getPriceForAll()));

        ((TextView)(view.findViewById(R.id.tvDateInHistory)))
                .setText(bought.getDay().toString() + "." + bought.getMonth().toString() + "." + bought.getYear().toString());

        return view;

    }
}
