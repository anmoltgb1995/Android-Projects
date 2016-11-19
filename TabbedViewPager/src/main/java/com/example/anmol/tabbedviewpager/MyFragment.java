package com.example.anmol.tabbedviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Anmol-PC on 12/22/2015.
 */
public class MyFragment extends Fragment {

    int myId;

    public MyFragment(int myId){
        this.myId = myId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment, container, false);

        if(myId==1)
        rootView.setBackgroundColor(getActivity().getResources().getColor(R.color.color2));
        return rootView;


    }
}
