package com.example.dmitry.cashmonitor.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dmitry.cashmonitor.Adapter.NotesAdapter;
import com.example.dmitry.cashmonitor.DB.DBForNotes;
import com.example.dmitry.cashmonitor.Note;
import com.example.dmitry.cashmonitor.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {

    private int NOTE_REQUEST_CODE = 0;

    public String BOUGHT_NAME = "nameBght";
    public String BOUGHT_COUNT = "countBght";
    public String BOUGHT_PRICE = "priceBght";

    SQLiteDatabase db;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_notes, container, false);

        ArrayList<Note> notes = createNotesFromDB();

        double allCount = notes.size();

        ((TextView)view.findViewById(R.id.tvAllPriceCount)).setText(allCount + "");

        ((ListView) view.findViewById(R.id.lvNotes))
                .setAdapter(new NotesAdapter(getContext(), notes, getFragmentManager()));

        ((Button) view.findViewById(R.id.btnAddNote)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                view.findViewById(R.id.lvNotes).setAlpha(0);
                view.findViewById(R.id.linearLAllPriceInBottom).setAlpha(0);
                view.findViewById(R.id.linl_top_notes).setAlpha(0);

                getFragmentManager().beginTransaction().replace(R.id.fragmentForFillBght, new FillBoughtFragment()).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ArrayList<Note> createNotesFromDB(){

        db = (new DBForNotes(getContext())).getWritableDatabase();

        Cursor cursor = db.query(DBForNotes.NAME_TABLE,
                new String[]{
                        BaseColumns._ID,
                        DBForNotes.NAME_COLLUMN_NAME,
                        DBForNotes.NAME_COLLUMN_SUCCESS_BOUGHT,
                        DBForNotes.NAME_COLLUMN_COUNT_BOUGHT,
                        DBForNotes.NAME_COLLUMN_TYPE_BOUGHT}, null, null, null, null, null);

        ArrayList<Note> notes = new ArrayList<>();

        while (cursor.moveToNext()){

            notes.add(new Note(
                cursor.getString(cursor.getColumnIndex(DBForNotes.NAME_COLLUMN_NAME)),
                cursor.getInt(cursor.getColumnIndex(DBForNotes.NAME_COLLUMN_SUCCESS_BOUGHT)),
                cursor.getDouble(cursor.getColumnIndex(DBForNotes.NAME_COLLUMN_COUNT_BOUGHT)),
                cursor.getString(cursor.getColumnIndex(DBForNotes.NAME_COLLUMN_TYPE_BOUGHT)),
                cursor.getLong(cursor.getColumnIndex(BaseColumns._ID))));
        }

        cursor.close();

        return notes;
    }


}
