package com.ucv.tavo.aguilas.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
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
import com.ucv.tavo.aguilas.LVBPApp;
import com.ucv.tavo.aguilas.Constants;
import com.ucv.tavo.aguilas.R;
import com.ucv.tavo.aguilas.adapter.GameAdapter;
import com.ucv.tavo.aguilas.dto.Game;
import com.ucv.tavo.aguilas.util.Utils;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import volley.MyJsonArrayRequest;

public class HomeFragment extends Fragment {

    ViewPager dashboardPager;
    Context context;
    RelativeLayout noInternet;
    ProgressBar progressBar;
    List<Game> games;
    GameAdapter adapter;
    AbsListView gameList;
    SwipeRefreshLayout refresh;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.loading);
        noInternet = (RelativeLayout)view.findViewById(R.id.modal_no_internet);
        gameList = (ListView)view.findViewById(R.id.game_list);

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                loadTodaysGame();
            }
        });
        refresh.setColorScheme(R.color.primary_color);

        loadTodaysGame();
        return view;
    }



    private void updateSchedule() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LVBPApp.getInstance());
        String games_preference = prefs.getString("games_preference", null);
        try {
            Type collectionType = new TypeToken<List<Game>>() {
            }.getType();

            games = new Gson().fromJson(games_preference, collectionType);
            adapter = new GameAdapter(getActivity(), games);
            gameList.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
            noInternet.setVisibility(View.GONE);
            refresh.setRefreshing(false);

        } catch (Exception e) {
            Log.e(HomeFragment.class.getSimpleName(), e.toString());
        }
    }

    private void saveGamesPeferences(String jsonString) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LVBPApp.getInstance());
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("games_preference", jsonString);
        edit.commit();
    }

    Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray jsonArray) {
            saveGamesPeferences(jsonArray.toString());
            updateSchedule();
        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e(HomeFragment.class.getSimpleName(), volleyError.toString());
            progressBar.setVisibility(View.GONE);
            refresh.setRefreshing(false);
            Toast.makeText(getActivity(),"Connection error.",Toast.LENGTH_LONG).show();
        }
    };

    private void loadTodaysGame() {
        if(Utils.isOnline(context)){
            JsonArrayRequest jsonObjReq = new MyJsonArrayRequest(Constants.GAMES_REQUESTS_URL, listener, errorListener);
            LVBPApp.getInstance().addToRequestQueue(jsonObjReq, "json_obj_req");
        }else{
            progressBar.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
            refresh.setRefreshing(false);
        }
    }




}
