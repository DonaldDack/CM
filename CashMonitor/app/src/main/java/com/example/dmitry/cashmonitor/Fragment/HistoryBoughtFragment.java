package com.example.dmitry.cashmonitor.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.dmitry.cashmonitor.Adapter.HistoryAdapter;
import com.example.dmitry.cashmonitor.Bought;
import com.example.dmitry.cashmonitor.DB.DBForHistory;
import com.example.dmitry.cashmonitor.DB.DBForNotes;
import com.example.dmitry.cashmonitor.Note;
import com.example.dmitry.cashmonitor.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistoryBoughtFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistoryBoughtFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryBoughtFragment extends Fragment {

    final String YEAR_PROMT = DBForHistory.NAME_COLLUMN_DATE_YEAR_WHEN_BGHT;
    final String MONTH_PROMT = DBForHistory.NAME_COLLUMN_DATE_MONTH_WHEN_BGHT;
    final String DAY_PROMT = DBForHistory.NAME_COLLUMN_DATE_DAY_WHEN_BGHT;
    final public static String DEFAULT_SPINNER_COLUMN = "--";

    public HistoryBoughtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_history_bought, container, false);

        final ArrayList<String> yearArr = createSpinner(view, R.id.spinnerChoiceYearInHistory,
                DBForHistory.NAME_COLLUMN_DATE_YEAR_WHEN_BGHT);

        final ArrayList<String> monthArr = createSpinner(view, R.id.spinnerChoiceMonthInHistory,
                DBForHistory.NAME_COLLUMN_DATE_MONTH_WHEN_BGHT);

        final ArrayList<String> dayArr = createSpinner(view, R.id.spinnerChoiceDayInHistory,
                DBForHistory.NAME_COLLUMN_DATE_DAY_WHEN_BGHT);

        final ArrayList<Bought> boughts = new ArrayList<Bought>();

        (view.findViewById(R.id.btnShowHistory)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boughts.removeAll(boughts);

                String selection = new String();

                ArrayList<String> selectionArgs = new ArrayList<String>();

                Spinner spinner = ((Spinner) view.findViewById(R.id.spinnerChoiceYearInHistory));

                if (yearArr.get(spinner.getSelectedItemPosition()) != DEFAULT_SPINNER_COLUMN) {
                    selection += (YEAR_PROMT + " = ?");
                    selectionArgs.add(yearArr.get(spinner.getSelectedItemPosition()));
                }

                spinner = ((Spinner) view.findViewById(R.id.spinnerChoiceMonthInHistory));

                if (monthArr.get(spinner.getSelectedItemPosition()) != DEFAULT_SPINNER_COLUMN) {
                    if (!selection.isEmpty())
                        selection += " and ";
                    selection += MONTH_PROMT + " = ?";
                    selectionArgs.add(monthArr.get(spinner.getSelectedItemPosition()));
                }

                spinner = ((Spinner) view.findViewById(R.id.spinnerChoiceDayInHistory));

                if (dayArr.get(spinner.getSelectedItemPosition()) != DEFAULT_SPINNER_COLUMN) {
                    if (!selection.isEmpty())
                        selection += " and ";
                    selection += DAY_PROMT + " = ?";
                    selectionArgs.add(dayArr.get(spinner.getSelectedItemPosition()));
                }

                String[] args = new String[selectionArgs.size()];
                args = selectionArgs.toArray(args);

                Cursor cursor = new DBForHistory(getContext()).getWritableDatabase()
                        .query(DBForHistory.NAME_TABLE, new String[]{
                                        BaseColumns._ID,
                                        DBForHistory.NAME_COLLUMN_NAME,
                                        DBForHistory.NAME_COLLUMN_DATE_YEAR_WHEN_BGHT,
                                        DBForHistory.NAME_COLLUMN_DATE_MONTH_WHEN_BGHT,
                                        DBForHistory.NAME_COLLUMN_DATE_DAY_WHEN_BGHT,
                                        DBForHistory.NAME_COLLUMN_COUNT_BOUGHT,
                                        DBForHistory.NAME_COLLUMN_PRICE_FOR_ONE,
                                        DBForHistory.NAME_COLLUMN_TYPE_BOUGHT,
                                        DBForHistory.NAME_COLLUMN_SUCCESS_BOUGHT},
                                selection, args, null, null, null);

                while (cursor.moveToNext()) {
                    boughts.add(new Bought(
                            cursor.getString(cursor.getColumnIndex(DBForHistory.NAME_COLLUMN_NAME)),
                            cursor.getInt(cursor.getColumnIndex(DBForHistory.NAME_COLLUMN_SUCCESS_BOUGHT)),
                            cursor.getInt(cursor.getColumnIndex(DBForHistory.NAME_COLLUMN_COUNT_BOUGHT)),
                            cursor.getString(cursor.getColumnIndex(DBForHistory.NAME_COLLUMN_TYPE_BOUGHT)),
                            cursor.getInt(cursor.getColumnIndex(DBForHistory.NAME_COLLUMN_PRICE_FOR_ONE)),
                            cursor.getLong(cursor.getColumnIndex(BaseColumns._ID)),
                            cursor.getString(cursor.getColumnIndex(DBForHistory.NAME_COLLUMN_DATE_YEAR_WHEN_BGHT)),
                            cursor.getString(cursor.getColumnIndex(DBForHistory.NAME_COLLUMN_DATE_MONTH_WHEN_BGHT)),
                            cursor.getString(cursor.getColumnIndex(DBForHistory.NAME_COLLUMN_DATE_DAY_WHEN_BGHT))));
                }

                cursor.close();

                boughts.add(new Bought(getResources().getString(R.string.text_all_price), CountAllPrice(boughts)));

                ((ListView) view.findViewById(R.id.lvHistoryBght)).setAdapter(new HistoryAdapter(getContext(), boughts));
            }
        });

        ((ListView) view.findViewById(R.id.lvHistoryBght)).setAdapter(new HistoryAdapter(getContext(), boughts));

        return view;
    }

    private ArrayList<String> createSpinner(View view, int id, String dbCollumn){

        Spinner spinnerDate = ((Spinner) view.findViewById(id));
        
        ArrayList<String> typeDate = getDifferentDate(dbCollumn);

        final String[] ar = new String[typeDate.size()];
        typeDate.toArray(ar);

        spinnerDate.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, getDifferentDate(dbCollumn)));

        return typeDate;
    }

    private ArrayList<String> getDifferentDate(String Collumn) {

        Cursor cursor = (new DBForHistory(getContext()))
                .getWritableDatabase().query(DBForHistory.NAME_TABLE,
                        new String[]{Collumn},
                        null, null, null, null, null);

        ArrayList<String> difDate = new ArrayList<>();
        difDate.add(DEFAULT_SPINNER_COLUMN);

        while (cursor.moveToNext()){

            String typeDate = cursor.getString(cursor.getColumnIndex(Collumn));

            if (!difDate.contains(typeDate))
                 difDate.add(typeDate);

        }

        cursor.close();

        return difDate;
    }

    private double CountAllPrice(ArrayList<Bought> boughts){
        double allprice = 0;

        for (Bought x : boughts){
            allprice += x.getPriceForAll();
        }

        return allprice;
    }

}
