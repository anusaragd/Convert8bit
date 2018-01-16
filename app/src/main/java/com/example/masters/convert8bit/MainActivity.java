package com.example.masters.convert8bit;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IInterface;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.DIRECTORY_PICTURES;

public class MainActivity extends Activity {

    Button convertButton;
    ImageView fingerVeiw;
    ImageView BGVeiw;
    ImageView ConvertVeiw;
    TextView text_status;
    Bitmap bitmapOriginal;
    Bitmap bitmapScaled;

    String fname ; // ชื่อไฟล์
    Uri URI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerVeiw = (ImageView) findViewById(R.id.imageView_finger);
        fingerVeiw.setImageResource(R.drawable.fp);

        BGVeiw = (ImageView)findViewById(R.id.imageView_bg);
        BGVeiw.setImageResource(R.drawable.bg);

        ConvertVeiw = (ImageView)findViewById(R.id.imageView_convert);


        text_status = (TextView)findViewById(R.id.textView_status);
//                if(convertButton == null){
//                    text_status.setText("AGREE !!");
//                }
//                else {
//                    text_status.setText("NO !!");
//                }
//        );

        convertButton = (Button)findViewById(R.id.button_convert);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create scaled bitmap using Matrix
                Matrix matrix = new Matrix();

                int Width = 0b110010000 ;
                int Height = 0b110010000 ;

//                ConvertVeiw.setImageResource(R.drawable.bg);
//                ConvertVeiw.getDrawable();
//                ConvertImage();
//                ConvertVeiw.setImageDrawable(null);
                bitmapOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
                BGVeiw.setImageBitmap(bitmapOriginal);

//                Bitmap bitmapScaled = Bitmap.createBitmap(bitmapOriginal, 0, 0,
//                        bitmapOriginal.getWidth(), bitmapOriginal.getHeight(), matrix,
//                        false);

                bitmapScaled = BitmapFactory.decodeResource(getResources(), R.drawable.fp);
                fingerVeiw.setImageBitmap(bitmapScaled);


                //Merge two bitmaps to one
                Bitmap bitmapMerged = Bitmap.createBitmap(
                        bitmapOriginal.getWidth(),
                        bitmapOriginal.getHeight(),
                        bitmapOriginal.getConfig());
                Canvas canvasMerged = new Canvas(bitmapMerged);
                canvasMerged.drawBitmap(bitmapOriginal, 0, 0, null);
                canvasMerged.drawBitmap(bitmapScaled, 50, 0, null);
//                ConvertVeiw.setImageBitmap(Bitmap.createScaledBitmap(bitmapMerged, 120, 120, false));
//                getResizedBitmap(bitmapMerged, 400,400);
                bitmapMerged = getResizedBitmap(bitmapMerged,400,400);
                ConvertVeiw.setImageBitmap(bitmapMerged);
                ConvertVeiw.getDrawable();
                SaveImage(bitmapMerged);
            }
        });
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();

        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;

        float scaleHeight = ((float) newHeight) / height;

// CREATE A MATRIX FOR THE MANIPULATION

        Matrix matrix = new Matrix();

// RESIZE THE BIT MAP

        matrix.postScale(scaleWidth, scaleHeight);

// RECREATE THE NEW BITMAP

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;

    }


//
//    private void ConvertImage() {
//        fingerVeiw.setImageResource(R.drawable.fp);
//        BGVeiw.setImageResource(R.drawable.bg);
////        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
////                Uri.parse("file://" + Environment.getExternalStorageDirectory())));
//        ConvertVeiw.setImageBitmap(bitmap);
//        SaveImage();
//        SaveImage(bitmap);
//    }

    private void SaveImage(Bitmap finalBitmap) {

        //get bitmap from ImageVIew
        //not always valid, depends on your drawable
        Bitmap bitmap = ((BitmapDrawable)ConvertVeiw.getDrawable()).getBitmap();


        //always save as
//        String fileName = "AAAA.jpg";

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap.setWidth(0b110010000);
//        bitmap.setHeight(0b110010000);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);


//        File ExternalStorageDirectory = Environment.getExternalStorageDirectory();
//        File file = new File(ExternalStorageDirectory + File.separator + fileName);
        File file = new File(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "demo_image", "demo_image"));

        FileOutputStream fileOutputStream = null;
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes.toByteArray());

            Toast.makeText(MainActivity.this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }



    }

}
