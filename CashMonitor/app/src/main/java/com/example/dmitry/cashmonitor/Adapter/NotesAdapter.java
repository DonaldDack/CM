package com.example.dmitry.cashmonitor.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.cashmonitor.DB.DBForHistory;
import com.example.dmitry.cashmonitor.DB.DBForNotes;
import com.example.dmitry.cashmonitor.Fragment.NotesFragment;
import com.example.dmitry.cashmonitor.Fragment.PriceFragment;
import com.example.dmitry.cashmonitor.Note;
import com.example.dmitry.cashmonitor.R;

import java.util.ArrayList;


/**
 * Created by Dmitry on 21.03.2016.
 */
public class NotesAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Note> notes;
    FragmentManager fragmentManager;

    public NotesAdapter(Context context, ArrayList<Note> notes, FragmentManager fragmentManager){
        this.context = context;
        this.notes = notes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.note_custom_view, parent, false);
        }

        final Note note = (Note)getItem(position);

        final TextView tvBghtProgress = (TextView) view.findViewById(R.id.tv_bought_progress);

        ((TextView)(view.findViewById(R.id.tvNameNote))).setText(note.getName());

        final CheckBox successNote = (CheckBox) (view.findViewById(R.id.chbSuccessNote));

        successNote.setChecked(note.getSuccess());

        tvBghtProgress.setText(
                successNote.isChecked() ?
                        R.string.text_bought_progress_ok:
                        R.string.text_bought_progress_no);

        successNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){

                    Toast.makeText(context, "уточните пожалуйста цену", Toast.LENGTH_LONG).show();

                    fragmentManager.beginTransaction().replace(R.id.fragmentForFillBght, new PriceFragment(note)).commit();

                }else{

                    tvBghtProgress.setText(R.string.text_bought_progress_no);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DBForNotes.NAME_COLLUMN_SUCCESS_BOUGHT, 0);

                    ((new DBForNotes(context)).getWritableDatabase()).
                            update(DBForNotes.NAME_TABLE, contentValues, DBForNotes.COLUMN_ID + " = ?", new String[]{note.getId().toString()});

                    ((new DBForHistory(context)).getWritableDatabase())
                            .delete(DBForHistory.NAME_TABLE, BaseColumns._ID + " = ?", new String[]{note.getId().toString()});

                    fragmentManager.beginTransaction().replace(R.id.MainFragmentInUserActivity, new NotesFragment()).commit();
                }


            }
        });

        ((TextView)(view.findViewById(R.id.tvCountNote))).setText(doubleToText(note.getCount()));
        ((TextView)(view.findViewById(R.id.tvTypeCountNote))).setText(note.getTypeCount().toString());

        ((Button) (view.findViewById(R.id.btnDeleteNote))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((new DBForNotes(context)).getWritableDatabase())
                        .delete(DBForNotes.NAME_TABLE, BaseColumns._ID + " = ?", new String[]{note.getId().toString()});
                fragmentManager.beginTransaction().replace(R.id.MainFragmentInUserActivity, new NotesFragment()).commit();
            }
        });

        return view;
    }

    public static String doubleToText(double d){
        if(d == 0.0)
            return " ";
        else if ((int) d == d)
            return "" + (int) d;
        else
            return d + "";
    }
}
