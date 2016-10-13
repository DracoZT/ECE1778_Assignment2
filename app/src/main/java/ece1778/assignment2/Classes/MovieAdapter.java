package ece1778.assignment2.Classes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ece1778.assignment2.R;


import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<MovieEntry> {

    Context context;
    ArrayList<MovieEntry> data = null;
    int LayoutResource;

    public MovieAdapter(Context context, int LayoutResource, ArrayList<MovieEntry> data){
        super(context, LayoutResource, data);
        this.context = context;
        this.LayoutResource = LayoutResource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MovieHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
            row = inflater.inflate(LayoutResource, parent, false);
            holder = new MovieHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.labelTitle);
            holder.txtActor = (TextView) row.findViewById(R.id.labelActor);
            holder.txtYear = (TextView) row.findViewById(R.id.labelYear);
            row.setTag(holder);
        }else{
            holder = (MovieHolder) row.getTag();
        }

        MovieEntry MovieEntry = (MovieEntry) data.toArray()[position];
        holder.txtTitle.setText(MovieEntry.Title);
        holder.txtYear.setText(String.valueOf(MovieEntry.Year));
        holder.txtActor.setText(MovieEntry.Actor);

        return row;
    }

    static class MovieHolder{
        TextView txtTitle;
        TextView txtActor;
        TextView txtYear;
    }
}
