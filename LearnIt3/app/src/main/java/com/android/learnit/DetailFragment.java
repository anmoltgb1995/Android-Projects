package com.android.learnit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    private final int imageResourceId;
    private ImageView imageView;

    public DetailFragment(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Test", "hello");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page2, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_21);
        TextView textView1 = (TextView) view.findViewById(R.id.text_22);
        imageView = (ImageView) view.findViewById(R.id.img_2);
        imageView.setImageResource(imageResourceId);
        return view;
    }

}