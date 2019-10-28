package com.example.doctor.project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctor.R;
import com.example.doctor.project.Adapter.ExpandAdapter;

import me.yokeyword.fragmentation.SupportFragment;

public class Support_Fr_3 extends SupportFragment {
ExpandableListView expandableListView;
ExpandAdapter expandAdapter;
    public static Support_Fr_3 newInstance() {
        Bundle args = new Bundle();
        Support_Fr_3 fragment = new Support_Fr_3();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tiku,null);
        intview(view);
        return view;
    }
private  void intview(View view){
        expandableListView=view.findViewById(R.id.expand1);
        expandAdapter=new ExpandAdapter(getActivity());
        expandableListView.setAdapter(expandAdapter);
    expandableListView.setIndicatorBounds(expandableListView.getWidth()+800, expandableListView.getWidth());


}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
