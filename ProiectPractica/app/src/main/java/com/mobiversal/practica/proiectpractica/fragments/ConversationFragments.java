package com.mobiversal.practica.proiectpractica.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiversal.practica.proiectpractica.R;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class ConversationFragments extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversatii, null, false);
        return view;
    }
}