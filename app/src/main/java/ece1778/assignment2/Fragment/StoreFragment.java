package ece1778.assignment2.Fragment;


import android.support.v7.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ece1778.assignment2.Activity.MainActivity;
import ece1778.assignment2.R;

public class StoreFragment extends Fragment {
    View resultview;
    EditText fileName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultview = inflater.inflate(R.layout.frag_store, container, false);

        fileName = (EditText) resultview.findViewById(R.id.fileName);

        Button btn = (Button) resultview.findViewById(R.id.btnStoreOk);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onOkPressed();
                    }
                }
        );

        btn = (Button) resultview.findViewById(R.id.btnStoreCancel);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onCancelPressed();
                    }
                }
        );

        return resultview;
    }

    public void onOkPressed(){
        if(fileName.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please enter a valid name.");
            builder.setPositiveButton("OK", null);
            builder.setCancelable(true);
            builder.create().show();
            return;
        }

        //check if the list is empty
        if(MainActivity.Movies.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Data record is empty, please at least input some data!");
            builder.setPositiveButton("OK", null);
            builder.setCancelable(true);
            builder.create().show();
            getFragmentManager().popBackStackImmediate();
            return;
        }else{
            saveData();
        }
    }

    public void saveData(){
        try{
            String fName = fileName.getText().toString();
            FileOutputStream fos = getActivity().openFileOutput(fName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(MainActivity.Movies);
            os.close();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();
        fileName.setText("");
        MainActivity.EntryAdded = false;
    }

    public void onCancelPressed(){
        getFragmentManager().popBackStackImmediate();
    }
}
