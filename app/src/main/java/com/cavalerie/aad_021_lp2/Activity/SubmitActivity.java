package com.cavalerie.aad_021_lp2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.cavalerie.aad_021_lp2.Entity.Mysingleton;
import com.cavalerie.aad_021_lp2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class SubmitActivity extends AppCompatActivity {

    Dialog dialog;
    private androidx.appcompat.widget.Toolbar toolbar;
    private ImageView arrowBack;
    private Button projectSubmit;
    private TextView firstname, lastname, email, github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        initID();
        initAction();
    }

    @SuppressLint("RestrictedApi")
    private void initAction() {

        //for inflate toolbar
        setSupportActionBar(toolbar);

        arrowBack.setOnClickListener(view -> finish());

        projectSubmit.setOnClickListener(view -> {

            if (validate())
                dialog();

        });

    }

    private void submit() {

        String Firstname = firstname.getText().toString().trim();
        String Lastname = lastname.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Github = github.getText().toString().trim();

        StringRequest update = new StringRequest(Request.Method.POST, "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse",
                response -> {
                    System.out.println(response);
                    setDialog(R.layout.dialog_state, this);

                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(2000);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            finally {
                                dialog.dismiss();
                                finish();
                            }
                        }
                    };
                    timer.start();

                    if(dialog.getWindow() != null) {
                        dialog.getWindow().setAttributes(getLayoutParams(dialog));
                    }

                    dialog.show();
                },
                error -> {
                    setDialog(R.layout.dialog_state, this);

                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(2000);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            finally {
                                dialog.dismiss();
                                finish();
                            }
                        }
                    };
                    timer.start();

                    if(dialog.getWindow() != null) {
                        dialog.getWindow().setAttributes(getLayoutParams(dialog));
                    }

                    dialog.show();
                    error.printStackTrace();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("entry.1877115667", Firstname);
                params.put("entry.2006916086", Lastname);
                params.put("entry.1824927963", Email);
                params.put("entry.284483984", Github);

                return params;
            }
        };

        Mysingleton.getInstance(this).addToRequestqueue(update);

    }

    private void dialog(){
        setDialog(R.layout.dialog_warning, this);

        ImageView warning_close = dialog.findViewById(R.id.warning_close);
        Button warning_button = dialog.findViewById(R.id.warning_button);

        warning_close.setOnClickListener(view1 -> dialog.dismiss());
        warning_button.setOnClickListener(view1 -> {
            dialog.dismiss();
            submit();
        });

        if(dialog.getWindow() != null) {
            dialog.getWindow().setAttributes(getLayoutParams(dialog));
        }

        dialog.show();
    }

    private boolean validate() {
        boolean valid = true;

        if (firstname.getText().toString().trim().equals("")){
            valid = false;
            firstname.setError("firstname required");
        }

        if (lastname.getText().toString().trim().equals("")){
            valid = false;
            lastname.setError("lastname required");
        }

        if (email.getText().toString().trim().equals("")){
            valid = false;
            email.setError("email required");
        }

        if (github.getText().toString().trim().equals("")){
            valid = false;
            github.setError("github required");
        }

        return valid;
    }

    private void setDialog(int layout, Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(layout);
    }

    private void initID() {

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarSubmit);
        projectSubmit = (Button) findViewById(R.id.projectSubmit);
        arrowBack = (ImageView) findViewById(R.id.arrowBack);
        firstname = (TextView) findViewById(R.id.firstname);
        lastname = (TextView) findViewById(R.id.lastname);
        email = (TextView) findViewById(R.id.email);
        github = (TextView) findViewById(R.id.github);
    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }

    public static WindowManager.LayoutParams getLayoutParams(@NonNull Dialog dialog)
    {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if(dialog.getWindow() != null) {
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
        }
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        return layoutParams;
    }
}