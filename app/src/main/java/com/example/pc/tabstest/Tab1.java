package com.example.pc.tabstest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tab1 extends Fragment {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab1, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(requireActivity());

        // Set the adapter here.
        // Use getActivity() instead of getContext()
        mExampleAdapter = new ExampleAdapter(getContext(), mExampleList);
        mRecyclerView.setAdapter(mExampleAdapter);
        parseJson();
        return rootView;
    }

    private void parseJson() {
        String url = "http://maranamassapp.cf/json_getdata.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject ser = jsonArray.getJSONObject(i);
                                String creatorname = ser.getString("filmname");
                                String imageUrl = ser.getString("filmimage");
                                String cat = ser.getString("filmcat");

                                mExampleList.add(new ExampleItem(imageUrl, creatorname, cat));
                            }

                            // Just call notifyDataSetChanged here
                            mExampleAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}
