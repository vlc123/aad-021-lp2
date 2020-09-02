package com.cavalerie.aad_021_lp2.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cavalerie.aad_021_lp2.R;

public class Learning_leaderFragment extends Fragment {

    public Learning_leaderFragment() {
        // Required empty public constructor
    }

    public static Learning_leaderFragment newInstance() {
        return new Learning_leaderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_leader, container, false);
    }
}