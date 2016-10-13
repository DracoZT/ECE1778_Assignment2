package ece1778.assignment2.Fragment;

import android.support.v7.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ece1778.assignment2.Activity.MainActivity;
import ece1778.assignment2.Classes.MovieEntry;
import ece1778.assignment2.R;


public class EnterInfoFragment extends Fragment{
    View resultView;
    EditText textTitle;
    EditText textActor;
    public Integer[] year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultView = inflater.inflate(R.layout.frag_enter, container, false);
        year = new Integer[100];

        for(int i = 0;i < year.length;i++){
            year[i] = 1917 + i;
        }

        Spinner s = (Spinner) resultView.findViewById(R.id.yearSelector);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        textTitle = (EditText) resultView.findViewById(R.id.txtMovieTitle);
        textActor = (EditText) resultView.findViewById(R.id.txtMovieActor);

        Button btn = (Button) resultView.findViewById(R.id.btnEnterDone);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDoneClicked(v);
                    }
                }
        );

        btn = (Button) resultView.findViewById(R.id.btnEnterAdd);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onAddClicked(v);
                    }
                }
        );

        return resultView;
    }

    public void onAddClicked(View view){
        int year;
        String title = textTitle.getText().toString();
        String actor = textActor.getText().toString();

        if(title.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please Enter a Movie Title");
            builder.setPositiveButton("Ok", null);
            builder.setCancelable(true);
            builder.create().show();
            return;
        }

        if(actor.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please Enter an Actor for the Movie");
            builder.setPositiveButton("Ok", null);
            builder.setCancelable(true);
            builder.create().show();
            return;
        }

        Spinner mYear = (Spinner) resultView.findViewById(R.id.yearSelector);
        year = Integer.parseInt(mYear.getSelectedItem().toString());

        MainActivity.Movies.add(new MovieEntry(title, actor, year));
        MainActivity.EntryAdded = true;

        Toast.makeText(getActivity(), "Record Saved", Toast.LENGTH_SHORT).show();

        textTitle.setText("");
        textActor.setText("");
        textTitle.requestFocus();
        mYear.setSelection(0);
    }

    public void onDoneClicked(View view){
        if(textTitle.getText().toString().equals("") && textActor.getText().toString().equals(""))
            getFragmentManager().popBackStackImmediate();
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Are you sure you want to exit without saving?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    getFragmentManager().popBackStackImmediate();
                }
            });
            builder.setNegativeButton("No", null);
            builder.setCancelable(true);
            builder.create().show();
        }

    }
}
