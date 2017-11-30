package com.minor2k17.nimble;

/**
 * Created by Hopeless on 17-Nov-17.
 */

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class smart_posting_books extends Fragment implements View.OnClickListener {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference bookRef = rootRef.child("BookAds");
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 111;
    private static final int CAMERA_REQUEST = 1888;

    Bitmap image;
    private TessBaseAPI mTess;
    String datapath = "";
    Button run, save;
    ImageView testImage;
    TextView OCRTextView;
    FloatingActionButton gallery, camera;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_smart_posting_books, container, false);

        // Initialize all variables
        run = (Button) view.findViewById(R.id.OCRbutton);
        save = (Button) view.findViewById(R.id.SaveFeed);
        testImage = (ImageView) view.findViewById(R.id.testImage);
        OCRTextView = (TextView) view.findViewById(R.id.OCRTextView);
        camera = (FloatingActionButton) view.findViewById(R.id.floatCamera);
        gallery = (FloatingActionButton) view.findViewById(R.id.floatGallery);

        // Initialize Tesseract API
        String language = "eng";
        datapath = getContext().getFilesDir()+ "/tesseract/";
        mTess = new TessBaseAPI();
        checkFile(new File(datapath + "tessdata/"));
        mTess.init(datapath, language);

        gallery.setOnClickListener(smart_posting_books.this);
        camera.setOnClickListener(smart_posting_books.this);
        save.setOnClickListener(smart_posting_books.this);
        run.setOnClickListener(smart_posting_books.this);

        return view;
    }

    public void processImage(){
        String OCRresult = null;
        mTess.setImage(image);
        OCRresult = mTess.getUTF8Text();
        OCRTextView.setText("[Book Price PhoneNo]\r\n" + OCRresult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            image = photo;
            testImage.setImageBitmap(photo);
        }
        else if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            if (requestCode == PICK_IMAGE_REQUEST) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                    image = bitmap;
                    testImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkFile(File dir) {
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles();
        }
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);

            if (!datafile.exists()) {
                copyFiles();
            }
        }
    }

    private void copyFiles() {
        try {
            String filepath = datapath + "/tessdata/eng.traineddata";
            AssetManager assetManager = getContext().getAssets();

            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }

            outstream.flush();
            outstream.close();
            instream.close();

            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == gallery){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
        }
        else if (view == camera){
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        else if (view == run){
            processImage();
        }
        else if (view == save){
            String extract = OCRTextView.getText().toString().trim();
            String lines[] = extract.split("\\r?\\n");
            String b_name = lines[1], b_price = lines[2], b_cnumber = lines[3];
            String b_descrp = "None", b_email = "Anonymous";

            if (b_name != null && !b_name.equals("") && b_price != null && !b_price.equals("") && !b_descrp.equals("") && b_descrp != null && b_cnumber != null && !b_cnumber.equals("")) {
                //TODO:Send data to Firebase Database
                final HashMap<String,String> bookMap = new HashMap<String,String>();
                bookMap.put("UserId",firebaseAuth.getCurrentUser().getUid());
                bookMap.put("BookName",b_name);
                bookMap.put("BookPrice",b_price);
                bookMap.put("Negotiable","true");
                bookMap.put("Description",b_descrp);
                bookMap.put("ContactNumber",b_cnumber);

                if(b_email!=null && !b_email.equals(""))
                    bookMap.put("Email",b_email);
                else
                    bookMap.put("Email","nimblehelp@gmail.com");
                //Datapush to Database
                bookRef.push().setValue(bookMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Log.i("bookmap", bookMap.toString());
                            Toast.makeText(getContext(), "Sucessfully Posted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
            }
        }
    }
}