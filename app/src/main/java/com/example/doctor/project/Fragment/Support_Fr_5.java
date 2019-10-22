package com.example.doctor.project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doctor.R;

import me.yokeyword.fragmentation.SupportFragment;

public class Support_Fr_5 extends SupportFragment {

    public static Support_Fr_5 newInstance() {
        Bundle args = new Bundle();
        Support_Fr_5 fragment = new Support_Fr_5();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragtwo,null);

        return view;
    }
}
