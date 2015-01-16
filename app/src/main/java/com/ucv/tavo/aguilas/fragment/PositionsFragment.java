package com.ucv.tavo.aguilas.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucv.tavo.aguilas.Constants;
import com.ucv.tavo.aguilas.LVBPApp;
import com.ucv.tavo.aguilas.R;
import com.ucv.tavo.aguilas.adapter.GameAdapter;
import com.ucv.tavo.aguilas.adapter.PositionAdapter;
import com.ucv.tavo.aguilas.dto.Game;
import com.ucv.tavo.aguilas.dto.Position;
import com.ucv.tavo.aguilas.util.Utils;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import volley.MyJsonArrayRequest;

public class PositionsFragment extends Fragment {

    ViewPager dashboardPager;
    Context context;
    RelativeLayout noInternet;
    ProgressBar progressBar;
    List<Position> positions;
    PositionAdapter adapter;
    AbsListView positionsList;
    SwipeRefreshLayout refresh;

    public static PositionsFragment newInstance() {
        PositionsFragment fragment = new PositionsFragment();
        return fragment;
    }

    public PositionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_positions, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.loading);
        noInternet = (RelativeLayout)view.findViewById(R.id.modal_no_internet);
        positionsList = (ListView)view.findViewById(R.id.positions_list);

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                loadPositions();
            }
        });
        refresh.setColorScheme(R.color.primary_color);

        loadPositions();
        return view;
    }

    private void loadPositions() {
        if(Utils.isOnline(context)){
            JsonArrayRequest jsonObjReq = new MyJsonArrayRequest(Constants.POSITIONS_REQUESTS_URL, listener, errorListener);
            LVBPApp.getInstance().addToRequestQueue(jsonObjReq, "json_obj_req");
        }else{
            progressBar.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
            refresh.setRefreshing(false);
        }
    }

    Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray jsonArray) {
            savePositionsPeferences(jsonArray.toString());
            updatePositions();
        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e(HomeFragment.class.getSimpleName(), volleyError.toString());
            progressBar.setVisibility(View.GONE);
            refresh.setRefreshing(false);
            Toast.makeText(getActivity(), "Connection error.", Toast.LENGTH_LONG).show();
        }
    };

    private void savePositionsPeferences(String jsonString) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LVBPApp.getInstance());
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("positions_preference", jsonString);
        edit.commit();
    }

    private void updatePositions() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LVBPApp.getInstance());
        String positions_preference = prefs.getString("positions_preference", null);
        try {
            Type collectionType = new TypeToken<List<Position>>() {
            }.getType();

            positions = new Gson().fromJson(positions_preference, collectionType);
            adapter = new PositionAdapter(getActivity(), positions);
            positionsList.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
            noInternet.setVisibility(View.GONE);
            refresh.setRefreshing(false);

        } catch (Exception e) {
            Log.e(HomeFragment.class.getSimpleName(), e.toString());
        }
    }
}
