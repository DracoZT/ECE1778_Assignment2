package ece1778.assignment2.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import ece1778.assignment2.Classes.MovieAdapter;
import ece1778.assignment2.R;


public class ViewActivity extends AppCompatActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        listview = (ListView) findViewById(R.id.list_view1);
        MovieAdapter movieEntryArrayAdapter = new MovieAdapter(this, R.layout.list_view_row, MainActivity.Movies);
        listview.setAdapter(movieEntryArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_clear:
                if(!MainActivity.Movies.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(listview.getContext());
                    builder.setMessage("Are you sure you want to clear all the data here?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.Movies.clear();
                            MovieAdapter movieEntryArrayAdapter = new MovieAdapter(listview.getContext(), R.layout.list_view_row, MainActivity.Movies);
                            listview.setAdapter(movieEntryArrayAdapter);
                            MainActivity.EntryAdded = false;
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.setCancelable(true);
                    builder.create().show();

                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
