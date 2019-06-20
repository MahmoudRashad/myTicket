package com.example.myticket.View.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.EditUserData.EditUserDataResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ReserveCinemaResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ResultReserveCinema;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.CustomSpinnerAdapter;
import com.example.myticket.helper.Variables;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReserveActivity extends AppCompatActivity
        implements GeneralListener {


    private ApiCalling apiCalling;

    Variables variables;
    //    AppDialog appDialog;
    ProgressDialog pd;
    SessionManager sessionManager;
    public int cameraRequest = 0 , galleryRequest = 1 ;
    Dialog  dialogChangePic ;
    ProgressDialog dialog;
    int movieId ;
    ReserveCinemaResponse reserveCinemaResponse;
    CustomSpinnerAdapter customSpinnerAdapter;

    //--------------------------------  references of views -------------------------------------------------//
    private ConstraintLayout layout ;
    EditText nameTv , phoneTv,emailTv,addressTv;
    ImageView userIv , editImageIv , nameIv,phoneIv,emailIv,addressIv;
    Button saveEditBtn;
    Spinner cinemaS , dateS , timeS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        findViewsToReferences();
        setListenerOfViews();

        if( getIntent().getExtras() != null )
        {
            movieId = getIntent().getExtras().getInt("movie_id" , -1);
        }

        sessionManager = new SessionManager(this);
        apiCalling = new ApiCalling(this);

        Map <String , String> queryMap = new HashMap();
        queryMap.put("film_id" , movieId+"");
        apiCalling.getCinemasOfMovie("Bearer " +sessionManager.getUserToken()
                , "ar" ,
                queryMap ,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setDataOfViews();
    }


    public Dialog showDialogChangePiecture() {

        final Dialog dialogReportProduct = new Dialog(this);
        dialogReportProduct.requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        dialogReportProduct.setContentView(R.layout.dialog_change_pic);
        dialogReportProduct.setCancelable(true);
        dialogReportProduct.setCanceledOnTouchOutside(true);
        ConstraintLayout layout =  dialogReportProduct.findViewById(R.id.container);

//        if (languageHelper.getAppLanguage().equals("ar")) {
//            layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//        } else
//
//        {
//            layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//        }
        ////////////////////////////////////////////////////////////////////////////////
//        dialogReportProduct.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int width = displayMetrics.widthPixels;
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogReportProduct.getWindow().getAttributes());
//        lp.width = (int) (width * 0.8);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogReportProduct.getWindow().setAttributes(lp);
        dialogReportProduct.setCancelable(true);

        //------------- logic-------------------//


        // references of views
        TextView titleTv = dialogReportProduct.findViewById(R.id.textView60);
        ImageButton galleryIb = dialogReportProduct.findViewById(R.id.imageButton3);
        ImageButton cameraIb = dialogReportProduct.findViewById(R.id.imageButton4);


//        Typeface typeLight= Typeface.createFromAsset(context.getAssets(),"montserrat_alternates_light.otf");
//        Typeface typeMed = Typeface.createFromAsset(context.getAssets(),"montserrat_alternates_medium.otf");
//        Typeface typeBold = Typeface.createFromAsset(context.getAssets(),"montserrat_alternates_bold.otf");


//        titleTv.setTypeface(typeMed);
        galleryIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickBtnGallery();
            }
        });

        cameraIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickBtnCamera();
            }
        });

        return dialogReportProduct;
    }


    private void setDataOfViews()
    {
//        try {

        Log.e("test**" , sessionManager.getNameOfUser() );
            nameTv.setText(sessionManager.getNameOfUser());
            phoneTv.setText(sessionManager.getUserPhone());
        emailTv.setText(sessionManager.getUserEmail());
            addressTv.setText(sessionManager.getUserAddress());

        if (sessionManager.getUserImage() != null && sessionManager.getUserImage() != "")
        {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.my_ticket_white_logo)
                    .error(R.drawable.my_ticket_white_logo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH);


            Glide.with(this)
                    .load(sessionManager.getUserImage())
//                        .error(R.drawable.arrow_back)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("Glide erorr**", "failed to load image");

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .apply(options)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                    .into(userIv);
        }

//        }catch (Exception e)
//        {
//
//        }
    }

    public void findViewsToReferences()
    {
//        try {



        layout = findViewById(R.id.container);
        nameTv = findViewById(R.id.name);
        phoneTv = findViewById(R.id.phone);
        emailTv = findViewById(R.id.email);
        addressTv = findViewById(R.id.address);
        userIv = findViewById(R.id.profile_image);
        editImageIv = findViewById(R.id.profile_pen);
        nameIv = findViewById(R.id.arrowOne);
        phoneIv= findViewById(R.id.arrowTwo);
        emailIv= findViewById(R.id.arrowThree);
        addressIv = findViewById(R.id.arrowFour);
        saveEditBtn = findViewById(R.id.submit_edit_profile_btn);
        cinemaS = findViewById(R.id.spinner);
        dateS = findViewById(R.id.spinner2);
        timeS = findViewById(R.id.spinner3);



//        }
//        catch ( Exception e)
//        {
//            Log.e("exception" , e.getMessage());
//        }

    }


    public void onClickBtnGallery() {
        checkPhotoPermission(galleryRequest);
        dialogChangePic.dismiss();
    }


    public void onClickBtnCamera() {
        checkPhotoPermission(cameraRequest);
        dialogChangePic.dismiss();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if( requestCode == galleryRequest)
        {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED)
            {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , galleryRequest);//one can be replaced with any action code

            } else
            {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.

            }
        }
        else
        {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED)
            {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, cameraRequest);//zero can be replaced with any action code
            }
            else
            {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.

            }
        }

    }


    private void checkPhotoPermission(int flagRequest)
    {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED  ) {

            // Permission is not granted
            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
//                    Manifest.permission.READ_CONTACTS)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE ,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE ,
                            Manifest.permission.CAMERA},
                    flagRequest);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
//            }
        } else {
            // Permission has already been granted
            if(flagRequest == galleryRequest)
            {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , galleryRequest);//one can be replaced with any action code
            }
            else {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, cameraRequest);//zero can be replaced with any action code
            }
        }
    }


    private void showWatingDialog()
    {
        dialog = new ProgressDialog(ReserveActivity.this);
        String message = getString(R.string.waiting);
        SpannableString spannableString =  new SpannableString(message);

//                            Typeface typeMed = Typeface.createFromAsset(getAssets(),"montserrat_alternates_medium.otf");
//        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.load(getAssets(),"montserrat_alternates_medium.otf"));
//        spannableString.setSpan(typefaceSpan, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        dialog.setMessage(spannableString);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
    }


    public void setListenerOfViews()
    {
//        try {


//        saveEditBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (TextUtils.isEmpty(nameTv.getText()) ||
//                        TextUtils.isEmpty(phoneTv.getText()) ||
//                        TextUtils.isEmpty(emailTv.getText()) ||
//                        TextUtils.isEmpty(addressTv.getText() ))
//                {
//                    Toast.makeText(ReserveActivity.this
//                            , "Please fill all fields"
//                            , Toast.LENGTH_LONG).show();
//                }
//
//                else if (!isEmailValid(emailTv.getText().toString())){
//                    Toast.makeText(ReserveActivity.this
//                            , "Email Not Valid",
//                            Toast.LENGTH_LONG).show();
//                }
//                else {
//                    showWatingDialog();
//
//                    Map<String , String> queryMap = new HashMap<>();
//                    queryMap.put("name" , nameTv.getText().toString());
//                    queryMap.put("phone" , phoneTv.getText().toString());
//                    queryMap.put("email" , emailTv.getText().toString());
//                    queryMap.put("address" , addressTv.getText().toString());
//
//                    apiCalling.editUserData("Bearer " +sessionManager.getUserToken()
//                            , "ar" ,
//                            queryMap , ReserveActivity.this );
//                }
//
//            }
//        });
        editImageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChangePic =  showDialogChangePiecture();
                dialogChangePic.show();
            }
        });



//        }
//        catch ( Exception e)
//        {
//            Log.e("exception" , e.getMessage());
//        }
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        dialog.dismiss();
        if(status == ErrorTypeEnum.noError.getValue())
        {
            if( tApiResponse instanceof ReserveCinemaResponse)
            {
                this.reserveCinemaResponse =
                        (ReserveCinemaResponse) tApiResponse;

                ResultReserveCinema resultReserveCinema =
                        new ResultReserveCinema();
                resultReserveCinema.setName("select cinema");
                resultReserveCinema.setId(-1);
                this.reserveCinemaResponse.getResult().add(0,resultReserveCinema);
                customSpinnerAdapter = new CustomSpinnerAdapter(
                        this, this.reserveCinemaResponse.getResult() );
                cinemaS.setAdapter(customSpinnerAdapter);

            }

//            Toast.makeText(this , "updated successfully"
//                    , Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this , "failed updated"
                    , Toast.LENGTH_LONG).show();
        }
    }
}
