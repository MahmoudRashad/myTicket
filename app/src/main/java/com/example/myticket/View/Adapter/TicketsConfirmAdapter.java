package com.example.myticket.View.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketDetailResult;
import com.example.myticket.R;
import com.example.myticket.View.Activity.QrcodePage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TicketsConfirmAdapter extends RecyclerView.Adapter<TicketsConfirmAdapter.ConfirmTicketsViewHolder> {

    private Context context;
    private List<MyTicketDetailResult> myTicketDetailResult;
    private MyTicketDetailResult myTicket;
    private int layout;
    private ByteArrayOutputStream bytearrayoutputstream;
    private Typeface myfont;


    public TicketsConfirmAdapter(Context context, List<MyTicketDetailResult> myTicketDetailResult, int layout) {
        this.context = context;
        this.myTicketDetailResult = myTicketDetailResult;
        this.layout = layout;
        if (context!= null)
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }


    @NonNull
    @Override
    public ConfirmTicketsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layout,viewGroup,false);

        return new ConfirmTicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmTicketsViewHolder confirmTicketsViewHolder, int i) {
        myTicket = myTicketDetailResult.get(i);
        confirmTicketsViewHolder.teamOne.setText(myTicket.getTeam1Name());
        confirmTicketsViewHolder.teamTwo.setText(myTicket.getTeam2Name());
        confirmTicketsViewHolder.stadiumName.setText(myTicket.getStadiumName());
        confirmTicketsViewHolder.class_type.setText(myTicket.getType());
        confirmTicketsViewHolder.blockName.setText(myTicket.getBlockName());
        confirmTicketsViewHolder.seat.setText(myTicket.getSymbol_chair() +"-" +myTicket.getSeatNum());
        confirmTicketsViewHolder.date.setText(myTicket.getDate() + " ," + myTicket.getTime());
        Picasso.get()
                .load(myTicket.getTeam1Image())
                .into(confirmTicketsViewHolder.teamOneImage);
        Picasso.get()
                .load(myTicket.getTeam2Image())
                .into(confirmTicketsViewHolder.teamTwoImage);
        Bitmap bitmap = null;
        try {
            bitmap = textToImage(myTicket.getQrCode(),900, 300);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        confirmTicketsViewHolder.barCodeImage.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
       return myTicketDetailResult.size();
    }

    public class ConfirmTicketsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView teamOne;
        private TextView teamTwo;
        private ImageView teamOneImage;
        private ImageView teamTwoImage;
        private TextView stadiumName;
        private TextView seat;
        private TextView class_type;
        private TextView date;
        private ImageView downloadIcon;
        private TextView blockName;
        private ImageView barCodeImage;
        private ConstraintLayout constraintLayout;

        public ConfirmTicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            teamOne = itemView.findViewById(R.id.team_one_name);
            teamOne.setTypeface(myfont);
            teamTwo = itemView.findViewById(R.id.team_two_name);
            teamTwo.setTypeface(myfont);
            teamOneImage = itemView.findViewById(R.id.image_one);
            teamTwoImage = itemView.findViewById(R.id.image_two);
            stadiumName = itemView.findViewById(R.id.stadium_name_text);
            stadiumName.setTypeface(myfont);
            date = itemView.findViewById(R.id.match_time_text);
            date.setTypeface(myfont);
            class_type = itemView.findViewById(R.id.ticket_class_value);
            class_type.setTypeface(myfont);
            seat = itemView.findViewById(R.id.ticket_seat_value);
            seat.setTypeface(myfont);
            downloadIcon = itemView.findViewById(R.id.download_icon);
            blockName = itemView.findViewById(R.id.ticket_block_value);
            blockName.setTypeface(myfont);
            barCodeImage = itemView.findViewById(R.id.bar_code_image);
            downloadIcon.setOnClickListener(this);
            constraintLayout = itemView.findViewById(R.id.green_rv);
            barCodeImage = itemView.findViewById(R.id.bar_code_image);


        }

        @Override
        public void onClick(View v) {
//            int position = getAdapterPosition();
//
//
//            String qrCode = myTicketDetailResult.get(position).getQrCode();
//            Intent intent = new Intent(context, QrcodePage.class);
//            intent.setAction("green");
//            intent.putExtra("qr","3318113");
//            context.startActivity(intent);
            Bitmap bitmap = getBitmapFromView(itemView);
            saveBitmap(bitmap);
            }
        }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    private void saveBitmap(Bitmap bitmap) {
        bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);


        File filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File mainFile = new File(filepath.getAbsolutePath()
                + "/My Ticket/");
        try

        {
            if (!mainFile.exists()) {
                mainFile.mkdir();
            }
            mainFile.createNewFile();
            String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            File file = new File(mainFile,timeStamp+".png");
            FileOutputStream fileoutputstream = new FileOutputStream(file);

            fileoutputstream.write(bytearrayoutputstream.toByteArray());
            fileoutputstream.flush();
            fileoutputstream.close();
            Toast.makeText(context, "Image Saved Successfully to: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();

        }

        catch (Exception e)

        {

            e.printStackTrace();

        }


    }

    private Bitmap textToImage(String text, int width, int height) throws WriterException, NullPointerException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.DATA_MATRIX.CODABAR,
                    width, height, null);
        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        int colorWhite = 0xFFFFFFFF;
        int colorBlack = 0xFF000000;

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? colorBlack : colorWhite;
            }
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, width, 0, 0, bitMatrixWidth, bitMatrixHeight);
        Bitmap rotated = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(), bitmap.getHeight(),matrix, true);

        return rotated;
    }
    }

