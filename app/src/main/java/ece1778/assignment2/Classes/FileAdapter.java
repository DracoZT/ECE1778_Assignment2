package ece1778.assignment2.Classes;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import ece1778.assignment2.R;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileAdapter extends ArrayAdapter<File>{

    Context context;
    File[] data = null;
    int LayoutResource;

    public FileAdapter(Context context, int LayoutResource, File[] data){
        super(context, LayoutResource, data);
        this.context = context;
        this.LayoutResource = LayoutResource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FileHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.frag_load_row, parent, false);
            holder = new FileHolder();
            holder.txtLoadFile = (TextView) row.findViewById(R.id.txtLoadFile);
            holder.txtSaveTime = (TextView) row.findViewById(R.id.fileSaveTime);
            holder.btnDeleteFile = (ImageButton) row.findViewById(R.id.btnDeleteFile);
            row.setTag(holder);
        }else
            holder = (FileHolder) row.getTag();

        File file = data[position];
        holder.txtLoadFile.setText(file.getName());
        DateFormat date = new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss z");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(file.lastModified());
        holder.txtSaveTime.setText(date.format(calendar.getTime()));
        holder.btnDeleteFile.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Long Press to Delete File", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return row;
    }


    static class FileHolder{
        TextView txtLoadFile;
        TextView txtSaveTime;
        ImageButton btnDeleteFile;
    }
}
