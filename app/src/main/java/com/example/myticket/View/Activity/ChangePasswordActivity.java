package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;

import java.util.HashMap;
import java.util.Map;


public class ChangePasswordActivity extends AppCompatActivity
        implements GeneralListener

{

    private ConstraintLayout layout;
    private SessionManager sessionManager;
    private ApiCalling apiCalling;
    GeneralApiesponse generalApiesponse;
    private Typeface myfont;



    //------------ reference of views -------------------//

    Button sendBtn ;
    EditText oldEt , newEt , confirmEt;
    ProgressBar loadingPb;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_change_password);

        layout = findViewById(R.id.container);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");


        findViewsToReferences1();
        setListenerOfViews1();
//        setFontOfViews();
        setToolbar();

        sessionManager = new SessionManager(this);
        apiCalling = new ApiCalling(this);
    }

    public void findViewsToReferences1() {


        sendBtn = findViewById(R.id.button6);
        oldEt = findViewById(R.id.textView23);
        newEt = findViewById(R.id.textView24);
        confirmEt = findViewById(R.id.textView25);
        loadingPb = findViewById(R.id.progressBar);
        TextView enterpassword = findViewById(R.id.textView22);
        TextView newPassword = findViewById(R.id.textView27);
        TextView confirmPassword = findViewById(R.id.textView28);
        enterpassword.setTypeface(myfont);
        newPassword.setTypeface(myfont);
        confirmPassword.setTypeface(myfont);
        sendBtn.setTypeface(myfont);
//        chnagePb= findViewById(R.id.progressBar2);

    }

//    public void setFontOfViews() {
//        Typeface typeLight = Typeface.createFromAsset(getAssets(), "montserrat_alternates_light.otf");
//        Typeface typeMed = Typeface.createFromAsset(getAssets(), "montserrat_alternates_medium.otf");
//        Typeface typeBold = Typeface.createFromAsset(getAssets(), "montserrat_alternates_bold.otf");
//
//
//        sendBtn.setTypeface(typeMed);
//        oldEt.setTypeface(typeLight);
//        newEt.setTypeface(typeLight);
//        confirmEt.setTypeface(typeLight);
//
//
//    }

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.change_password));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePasswordActivity.this,SearchPage.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setListenerOfViews1()
    {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (TextUtils.isEmpty(oldEt.getText()) ||
                        TextUtils.isEmpty(newEt.getText()) ||
                        TextUtils.isEmpty(confirmEt.getText()) )
                {
                    Toast.makeText(ChangePasswordActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else if (!newEt.getText().toString().equals(
                        confirmEt.getText().toString()
                )){
                    Toast.makeText(ChangePasswordActivity.this, "Please make sure passwords match", Toast.LENGTH_LONG).show();
                }
                else {
                    sendBtn.setVisibility(View.INVISIBLE);
                    loadingPb.setVisibility(View.VISIBLE);
                    Map<String,String>queryMap = new HashMap<>();
                    queryMap.put("old_password" , oldEt.getText().toString().trim());
                    queryMap.put("password" , newEt.getText().toString().trim());
                    apiCalling.changePassword( "Bearer "+sessionManager.getUserToken(),
                            queryMap,
                            ChangePasswordActivity.this);
                }

            }
        });

//        showOldIb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(oldEt.getText().toString().trim().length() > 0)
//                {
//                    if ( showOldIb.getTag().equals("hide") )
//                    {
//                        showOldIb.setTag("show");
//                        oldEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                        oldEt.setSelection(oldEt.length());
//                        showOldIb.setImageDrawable(getResources().getDrawable(R.drawable.showpassword_icon));
//                    }
//                    else if ( showOldIb.getTag().equals("show") )
//                    {
//                        showOldIb.setTag("hide");
//                        oldEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        oldEt.setSelection(oldEt.length());
//                        showOldIb.setImageDrawable(getResources().getDrawable(R.drawable.eyepassword_icon));
//                    }
//                }
//            }
//        });
//
//        showNewIb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(newEt.getText().toString().trim().length() > 0)
//                {
//                    if ( showNewIb.getTag().equals("hide") )
//                    {
//                        showNewIb.setTag("show");
//                        newEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                        newEt.setSelection(newEt.length());
//                        showNewIb.setImageDrawable(getResources().getDrawable(R.drawable.showpassword_icon));
//                    }
//                    else if ( showNewIb.getTag().equals("show") )
//                    {
//                        showNewIb.setTag("hide");
//                        newEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        newEt.setSelection(newEt.length());
//                        showNewIb.setImageDrawable(getResources().getDrawable(R.drawable.eyepassword_icon));
//                    }
//                }
//            }
//        });
//
//
//        showConfirmIb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(confirmEt.getText().toString().trim().length() > 0)
//                {
//                    if ( showConfirmIb.getTag().equals("hide") )
//                    {
//                        showConfirmIb.setTag("show");
//                        confirmEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                        confirmEt.setSelection(confirmEt.length());
//                        showConfirmIb.setImageDrawable(getResources().getDrawable(R.drawable.showpassword_icon));
//                    }
//                    else if ( showConfirmIb.getTag().equals("show") )
//                    {
//                        showConfirmIb.setTag("hide");
//                        confirmEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        confirmEt.setSelection(confirmEt.length());
//                        showConfirmIb.setImageDrawable(getResources().getDrawable(R.drawable.eyepassword_icon));
//                    }
//                }
//            }
//        });

//        this.backIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }





//    @Override
//    public void getApiResponse(int status, String message, Object tApiResponse) {
//        ForgetPasswordResponce forgetPasswordResponce = (ForgetPasswordResponce) tApiResponse;
//        loadingPb.setVisibility(View.GONE);
//        Toast.makeText(this,forgetPasswordResponce.getMessage(),Toast.LENGTH_LONG).show();
//
//    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {

        loadingPb.setVisibility(View.GONE);
        sendBtn.setVisibility(View.VISIBLE);

        if(status == ErrorTypeEnum.noError.getValue())
        {
            if( tApiResponse instanceof ForgetPasswordResponce )
            {
                ForgetPasswordResponce generalApiesponse =
                        (ForgetPasswordResponce)tApiResponse;

                Toast.makeText(this , generalApiesponse.getMessage()
                        , Toast.LENGTH_LONG).show();


//                Intent intent = new Intent(ChangePasswordActivity.this,
//                        HomeCinema.class);
//                startActivity(intent);
            }
        }
        else if(status == ErrorTypeEnum.BackendLogicFail.getValue())
        {
            ForgetPasswordResponce generalApiesponse =
                    (ForgetPasswordResponce)tApiResponse;

            Toast.makeText(this , generalApiesponse.getMessage()
                    , Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this , "failed"
                    , Toast.LENGTH_LONG).show();
        }
    }
}
