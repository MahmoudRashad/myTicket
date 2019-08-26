package com.example.myticket.View.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.R;
import com.example.myticket.View.Activity.ChangePasswordActivity;
import com.example.myticket.View.Activity.EditAccount;
import com.example.myticket.View.Activity.EditAccountStad;
import com.example.myticket.View.Activity.Gate;
import com.example.myticket.View.Activity.HomeCinema;
import com.example.myticket.View.Activity.Login;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private TextView profileTv;
    private TextView changePasswordTv;
    private TextView backGate;
    private TextView logout;
    private SessionManager sessionManager;
    String token;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        sessionManager = new SessionManager(getContext());
        profileTv = view.findViewById(R.id.textView6);
        changePasswordTv = view.findViewById(R.id.textView11);
        backGate = view.findViewById(R.id.textView29);
        logout = view.findViewById(R.id.text_login);
        token = sessionManager.handleLogin();
        if (!token.equals("")) {
            logout.setText(getString(R.string.logout));
            profileTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), EditAccountStad.class);
                    startActivity(intent);
                }
            });
            changePasswordTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                    intent.putExtra("flag","stad");
                    startActivity(intent);
                }
            });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sessionManager.getUserToken() == null ||
                            sessionManager.getUserToken() == "") {

                        Intent intent = new Intent(getContext(), Login.class);
                        intent.putExtra("flag", "stad");
                        intent.putExtra("name","home");
                        startActivity(intent);
                    } else {
                        sessionManager.clearSessionManager();
                        Intent intent = new Intent(getContext(), Login.class);
                        intent.putExtra("flag", "stad");
                        intent.putExtra("name","home");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                }
            });
        }
        else {

            profileTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Login.class);
                    intent.putExtra("flag", "stad");
                    intent.putExtra("name","home");
                    startActivity(intent);
                }
            });
            changePasswordTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Login.class);
                    intent.putExtra("flag", "stad");
                    intent.putExtra("name","home");
                    startActivity(intent);
                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Login.class);
                    intent.putExtra("flag", "stad");
                    intent.putExtra("name","home");
                    startActivity(intent);
                }
            });
        }
            backGate.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Gate.class);
                    startActivity(intent);
                }
            });

        return view;
    }

}
