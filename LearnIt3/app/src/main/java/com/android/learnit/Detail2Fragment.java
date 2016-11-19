package com.android.learnit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail2Fragment extends Fragment {
    private final int imageResourceId;

    public Detail2Fragment(int imageResourceId) {
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
        View view = inflater.inflate(R.layout.page3, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_31);
        TextView textView1 = (TextView) view.findViewById(R.id.text_32);

        ImageView imageView = (ImageView) view.findViewById(R.id.img_3);
        imageView.setImageResource(imageResourceId);
        return view;
    }
}