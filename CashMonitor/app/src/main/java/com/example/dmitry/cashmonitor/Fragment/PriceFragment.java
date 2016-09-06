package com.example.dmitry.cashmonitor.Fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.cashmonitor.Bought;
import com.example.dmitry.cashmonitor.DB.DBForHistory;
import com.example.dmitry.cashmonitor.DB.DBForNotes;
import com.example.dmitry.cashmonitor.Note;
import com.example.dmitry.cashmonitor.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PriceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PriceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriceFragment extends Fragment {

    Note note;

    public PriceFragment() {
    }

    public PriceFragment(Note note) {
        this.note = note;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_price, container, false);

        ((Button) view.findViewById(R.id.btnAddBoughtPrice)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String price = ((TextView) view.findViewById(R.id.edAddBoughtPrice)).getText().toString();
                if(!price.isEmpty() || price.equals("0")){

                    //Bought bought = new Bought(note.getName(), note.getSuccess(), note.getCount(), note.getTypeCount(),
                            //Double.parseDouble(price), note.getId(), note.getYear(), note.getMonth(), note.getDay());

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DBForNotes.NAME_COLLUMN_SUCCESS_BOUGHT, 1);

                    ((new DBForNotes(getContext())).getWritableDatabase()).
                            update(DBForNotes.NAME_TABLE, contentValues, DBForNotes.COLUMN_ID + " = ?",
                                    new String[]{note.getId().toString()});

                    contentValues = new ContentValues();

                    contentValues.put(DBForHistory.NAME_COLLUMN_NAME,
                            note.getName());

                    contentValues.put(DBForHistory.COLUMN_ID,
                            note.getId().toString());

                    contentValues.put(DBForHistory.NAME_COLLUMN_COUNT_BOUGHT,
                            note.getCount() + "");

                    contentValues.put(DBForHistory.NAME_COLLUMN_TYPE_BOUGHT,
                            note.getTypeCount());

                    contentValues.put(DBForHistory.NAME_COLLUMN_PRICE_FOR_ONE,
                            Double.parseDouble(price));

                    contentValues.put(DBForHistory.NAME_COLLUMN_DATE_YEAR_WHEN_BGHT,
                            note.getYear());

                    contentValues.put(DBForHistory.NAME_COLLUMN_DATE_MONTH_WHEN_BGHT,
                            note.getMonth());

                    contentValues.put(DBForHistory.NAME_COLLUMN_DATE_DAY_WHEN_BGHT,
                            note.getDay());

                    (new DBForHistory(getContext())).getWritableDatabase().insert(DBForHistory.NAME_TABLE, null, contentValues);

                    getFragmentManager().beginTransaction().replace(R.id.MainFragmentInUserActivity, new NotesFragment()).commit();
            }else
                    Toast.makeText(getContext(), "цена должна быть больше 0!", Toast.LENGTH_SHORT).show();
            }
        });

        ((Button) view.findViewById(R.id.btnNotAddBoughtPrice)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.MainFragmentInUserActivity, new NotesFragment()).commit();
            }
        });

        return view;
    }
}
