package ece1778.assignment2.Fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import ece1778.assignment2.Activity.MainActivity;
import ece1778.assignment2.Classes.FileAdapter;
import ece1778.assignment2.Classes.MovieEntry;
import ece1778.assignment2.R;

public class LoadFragment extends Fragment
    implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private View resultView;
    private ListView listView;
    private File fileDirectory;
    private FileAdapter fileadapter;
    private File[] f_list;
    private File[] rhead_flist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultView = inflater.inflate(R.layout.frag_load, container, false);
        fileDirectory = getActivity().getFilesDir();
        f_list = fileDirectory.listFiles();
        rhead_flist = Arrays.copyOfRange(f_list, 1, f_list.length);

        listView = (ListView) resultView.findViewById(R.id.list_view2);
        fileadapter = new FileAdapter(getActivity(), R.layout.frag_load_row, rhead_flist);
        listView.setAdapter(fileadapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        return resultView;
    }

    public void removeFile(int pos){
        try{
            File FileToDelete = rhead_flist[pos];
            boolean deleted = FileToDelete.delete();
            if(deleted){
                f_list = fileDirectory.listFiles();
                rhead_flist = Arrays.copyOfRange(f_list, 1, f_list.length);
                fileadapter = new FileAdapter(getActivity(), R.layout.frag_load_row, rhead_flist);
                listView.setAdapter(fileadapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String fileName = rhead_flist[position].getName();
        try{
            FileInputStream fis = getActivity().openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            MainActivity.Movies.clear();
            MainActivity.Movies = (ArrayList<MovieEntry>) ois.readObject();
            ois.close();
            fis.close();
            Toast.makeText(getActivity(), "'" + fileName + "'" + " loaded", Toast.LENGTH_LONG).show();
            MainActivity.EntryAdded = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final int pos = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to delete the file?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeFile(pos);
            }
        });
        builder.setNegativeButton("No", null);
        builder.create().show();
        return true;
    }

}
