package com.example.dmitry.cashmonitor.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitry.cashmonitor.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AboutProgrammFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AboutProgrammFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutProgrammFragment extends Fragment {

    public AboutProgrammFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_programm, container, false);
    }
}
