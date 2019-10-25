package com.example.doctor.project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctor.R;

import me.yokeyword.fragmentation.SupportFragment;

public class Support_Fr_3 extends SupportFragment {

    public static Support_Fr_3 newInstance() {
        Bundle args = new Bundle();
        Support_Fr_3 fragment = new Support_Fr_3();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragtwo,null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Allshuju();
        super.onCreate(savedInstanceState);
    }

    public void Allshuju() {
        {
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = "http://106.53.9.58:8761/subject";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("成功");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("失败");
                }
            });
            queue.add(stringRequest);
        }
    }
}
