package com.example.dmitry.cashmonitor.Fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dmitry.cashmonitor.DB.DBForNotes;
import com.example.dmitry.cashmonitor.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FillBoughtFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FillBoughtFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FillBoughtFragment extends Fragment {

    public FillBoughtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_fill_bought, container, false);

        Spinner spinner = ((Spinner) view.findViewById(R.id.spinnerAddBoughtType));

        final String[] data = {"шт", "кг", "л"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data);

        spinner.setAdapter(spinnerAdapter);

        spinner.setSelection(1);

        Button button = ((Button) view.findViewById(R.id.btnAddBought));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = ((EditText) view.findViewById(R.id.edAddBoughtName)).getText().toString();
                String countBght = ((EditText) view.findViewById(R.id.edAddBoughtCount)).getText().toString();

                if (TextUtils.isEmpty(name))
                    Toast.makeText(getContext(), "наименование не должно быть пустым!", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(countBght) || countBght.equals("0"))
                    Toast.makeText(getContext(), "количество должно быть больше 0!", Toast.LENGTH_SHORT).show();
                else {
                    final String[] data = {"шт", "кг", "л"};

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DBForNotes.NAME_COLLUMN_NAME, name);

                    contentValues.put(DBForNotes.NAME_COLLUMN_COUNT_BOUGHT,
                            Double.parseDouble(countBght));

                    contentValues.put(DBForNotes.NAME_COLLUMN_TYPE_BOUGHT,
                            (data[((Spinner) view.findViewById(R.id.spinnerAddBoughtType)).getSelectedItemPosition()]));

                    contentValues.put(DBForNotes.NAME_COLLUMN_SUCCESS_BOUGHT, 0);

                    ((new DBForNotes(getContext())).getWritableDatabase()).insert(DBForNotes.NAME_TABLE, null, contentValues);

                    getFragmentManager().beginTransaction().replace(R.id.MainFragmentInUserActivity, new NotesFragment()).commit();
                }

            }
        });

        ((Button) view.findViewById(R.id.btnNotAddBought)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.MainFragmentInUserActivity, new NotesFragment()).commit();
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
