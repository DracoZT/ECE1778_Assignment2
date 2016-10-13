package ece1778.assignment2.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import ece1778.assignment2.Classes.MovieEntry;
import ece1778.assignment2.Fragment.MainFragment;
import ece1778.assignment2.R;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<MovieEntry> Movies;
    public static boolean EntryAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MainFragment fragment = new MainFragment();
            fragmentTransaction.add(R.id.fragMain, fragment);
            fragmentTransaction.commit();
        }

        Movies = new ArrayList<MovieEntry>();

    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0)
            getFragmentManager().popBackStackImmediate();
        else
            super.onBackPressed();
    }
}
