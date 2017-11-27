package com.minor2k17.nimble;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class simple_posting_books extends Fragment {


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference bookRef = rootRef.child("BookAds");
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    EditText book_name, book_price, book_descrp, book_contact_number, book_email;
    Button book_post_button;
    CheckBox checkBox_input_negotiate;

    String b_name, b_price, b_descrp, b_cnumber, b_email;

    @Override
    public void onStart() {
        super.onStart();
        book_name.setText("");
        book_price.setText("");
        book_contact_number.setText("");
        book_descrp.setText("");
        book_contact_number.setText("");
        book_email.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simple_posting_books, container, false);

        // Get the title of the book from smart posting
        //final String smarttitle = getArguments().getString("title");

        book_name = view.findViewById(R.id.book_name_input);
        book_price = view.findViewById(R.id.book_price_input);
        book_descrp = view.findViewById(R.id.book_simple_description_input);
        book_contact_number = view.findViewById(R.id.book_person_contact_input);
        book_email = view.findViewById(R.id.book_person_email_input);

        book_post_button = view.findViewById(R.id.book_simple_form_button);
        checkBox_input_negotiate = view.findViewById(R.id.checkBox_input);

        book_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get text from edit text
                /*if (smarttitle != null){
                    book_name.setText(smarttitle, TextView.BufferType.EDITABLE);
                }*/
                b_name = book_name.getText().toString().trim();
                b_price = book_price.getText().toString().trim();
                b_descrp = book_descrp.getText().toString().trim();
                b_cnumber = book_contact_number.getText().toString().trim();
                b_email = book_email.getText().toString().trim();


                if (b_name != null && !b_name.equals("") && b_price != null && !b_price.equals("") && !b_descrp.equals("") && b_descrp != null && b_cnumber != null && !b_cnumber.equals("")) {
                    //TODO:Send data to Firebase Database
                    final HashMap<String,String> bookMap = new HashMap<String,String>();
                    bookMap.put("UserId",firebaseAuth.getCurrentUser().getUid());
                    bookMap.put("BookName",b_name);
                    bookMap.put("BookPrice",b_price);
                    if(checkBox_input_negotiate.isChecked())
                    {
                        bookMap.put("Negotiable","true");
                    }
                    else if(!checkBox_input_negotiate.isChecked())
                    {
                        bookMap.put("Negotiable","false");
                    }
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
                                Toast.makeText(getActivity(), "Sucessfully Posted", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getActivity(), "Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } else {
                    Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }

        });



        return view;
    }
}