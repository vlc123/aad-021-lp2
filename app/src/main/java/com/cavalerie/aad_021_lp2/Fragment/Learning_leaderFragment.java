package com.cavalerie.aad_021_lp2.Fragment;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.cavalerie.aad_021_lp2.Adapter.Adapter;
import com.cavalerie.aad_021_lp2.Entity.Leader;
import com.cavalerie.aad_021_lp2.Entity.Mysingleton;
import com.cavalerie.aad_021_lp2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Learning_leaderFragment extends Fragment {

    RecyclerView recyclerViewLearning;
    List<Leader> leaderList = new ArrayList<>();
    Adapter adapter;

    ProgressDialog progressDialog;

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_learning_leader, container, false);

        init(v);
        loadData();

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadData() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("loading");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://gadsapi.herokuapp.com/api/hours",
                response -> {
                    try {
                        System.out.println(response);
                        JSONArray data = new JSONArray(response);
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            leaderList.add( new Leader(
                                    jsonObject.getString("name"),
                                    jsonObject.getString("hours"),
                                    jsonObject.getString("country"),
                                    jsonObject.getString("badgeUrl"),
                                    true
                            ));
                        }
                        adapter = new Adapter(getContext(), leaderList);
                        recyclerViewLearning.setAdapter(adapter);
                        progressDialog.dismiss();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                },
                error -> progressDialog.dismiss());
        Mysingleton.getInstance(getContext()).addToRequestqueue(stringRequest);
    }

    private void init(View v) {

        recyclerViewLearning = (RecyclerView) v.findViewById(R.id.recyclerViewLearning);
        recyclerViewLearning.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLearning.setHasFixedSize(true);

    }
}