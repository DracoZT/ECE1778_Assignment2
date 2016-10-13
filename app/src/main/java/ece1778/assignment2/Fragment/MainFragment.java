package ece1778.assignment2.Fragment;

import android.support.v7.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import ece1778.assignment2.Activity.MainActivity;
import ece1778.assignment2.Activity.ViewActivity;
import ece1778.assignment2.R;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.frag_main, container, false);

        OnClickListener btnListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClicked(view);
            }
        };

        res.findViewById(R.id.btnTitle).setOnClickListener(btnListener);
        res.findViewById(R.id.btnView).setOnClickListener(btnListener);
        res.findViewById(R.id.btnStore).setOnClickListener(btnListener);
        res.findViewById(R.id.btnLoad).setOnClickListener(btnListener);
        res.findViewById(R.id.btnExit).setOnClickListener(btnListener);

        return res;
    }

    public void onButtonClicked(View v){
        FragmentManager fragmentManager = getFragmentManager();
        switch(v.getId()){
            case R.id.btnTitle:
                EnterInfoFragment ef = new EnterInfoFragment();
                FragmentTransaction f1 = fragmentManager.beginTransaction();
                f1.replace(R.id.fragMain, ef);
                f1.addToBackStack(null);
                f1.commit();
                break;
            case R.id.btnView:
                getActivity().startActivity(new Intent(this.getActivity(), ViewActivity.class));
                break;
            case R.id.btnLoad:
                LoadFragment lf = new LoadFragment();
                FragmentTransaction f2 = fragmentManager.beginTransaction();
                f2.replace(R.id.fragMain, lf);
                f2.addToBackStack(null);
                f2.commit();
                break;
            case R.id.btnStore:
                StoreFragment sf = new StoreFragment();
                FragmentTransaction f3 = fragmentManager.beginTransaction();
                f3.replace(R.id.fragMain, sf);
                f3.addToBackStack(null);
                f3.commit();
                break;
            case R.id.btnExit:
                if(MainActivity.EntryAdded){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Do you want to exit without saving your data?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            getActivity().finish();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.setCancelable(true);
                    builder.create().show();
                }else{
                    getActivity().finish();
                }
                break;
        }

    }
}
