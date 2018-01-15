package com.example.masters.convert8bit;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class MainActivity extends Activity {

    Button convertButton;
    ImageView fingerVeiw;
    ImageView BGVeiw;
    ImageView ConvertVeiw;
    TextView text_status;

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
//                ConvertVeiw.setImageResource(R.drawable.bg);
//                ConvertVeiw.getDrawable();
                ConvertImage();
                ConvertVeiw.setImageDrawable(null);
            }
        });
    }

    Bitmap bitmap;
    Uri URI;
    Drawable drawable;

    private void ConvertImage() {
        fingerVeiw.setImageResource(R.drawable.fp);
        BGVeiw.setImageResource(R.drawable.bg);
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
//                Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        ConvertVeiw.setImageBitmap(bitmap);
//        SaveImage();
        SaveImage(bitmap);
    }
    private void SaveImage(Bitmap finalBitmap) {

        Toast.makeText(MainActivity.this, "zzzzz", Toast.LENGTH_SHORT).show();

//        //create name in Storage
//        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString()).toString();
//        File myDir = new File(root + "/" + "MixFinger455");
//        myDir.mkdirs();


        //create name Image
        drawable = getResources().getDrawable(R.drawable.fp);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        String fname = " ";
        fname = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "demo_image", "demo_image");
        URI = Uri.parse(fname);
        Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
//        File file = new File(myDir, fname );
//        if (file.exists())
//            file.delete();
//        try {
//
////            FileOutputStream out = new FileOutputStream(file);
////            MyBitmapFile fileBMP1 = new MyBitmapFile(256, 360, mImageFP);
////            finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
////            out.write(fileBMP1.toBytes());
////            out.close();
////
////
////            String fileName = "AAAA.bmp";
////            File dir= Environment.getExternalStoragePublicDirectory(root + "/" + "MixFinger111.4");
////            File path = new File(dir, fileName);
////            String s = path.toString();
////            File file1 = new File(s);
////            int size = (int) file1.length();
////            byte[] bytes1 = new byte[size];
////
////
////            // 1. bmp to byte array  =  CORRECT METHODE
////            try {
////                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
////                buf.read(bytes1, 0, bytes1.length);
////                buf.close();
////            } catch (FileNotFoundException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            } catch (IOException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
//            FileOutputStream out = new FileOutputStream(file);
//            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
//            out.flush();
//            out.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//            final Uri contentUri = Uri.fromFile(myDir);
//            scanIntent.setData(contentUri);
//            sendBroadcast(scanIntent);
//        } else {
//            final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
//            sendBroadcast(intent);
//        }
//
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
//                Uri.parse("file://" + Environment.getExternalStorageDirectory())));

    }

//    Drawable drawable;
//    Bitmap bitmap;
//    String ImagePath;
//    Uri URI;
//
//    private void SaveImage(){
//        drawable = getResources().getDrawable(R.drawable.fp);
//        bitmap = ((BitmapDrawable)drawable).getBitmap();
//        ImagePath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "demo_image", "demo_image");
//        URI = Uri.parse(ImagePath);
//        Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
//
//    }
}
