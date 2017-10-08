package com.example.elad.pingpong;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static String NickName = "Guest1";
    Button JoinChat;
    EditText etNickname;
    static DatabaseReference myRef,NextIdref;
    static Player YourPlayer;
    static Player OpponetPlayer;

    int Nextid;

    Intent intent;
    public static Context cn1;

    static String ThisPlayRoom = "null";

    static List<Player> Players = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        cn1 = this;

        intent = new Intent(getApplication(), RoomActivity.class);
        // Write a message to the database

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        NextIdref = database.getReference("NextID");
        myRef = database.getReference("message");

        NextIdref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                   Nextid = dataSnapshot.getValue(Integer.class);
                Nextid++;

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Nextid = dataSnapshot.getValue(Integer.class);
                Nextid++;
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        JoinChat = (Button) findViewById(R.id.btnSignIn);
        etNickname = (EditText) findViewById(R.id.etNickName);


        //


        DatabaseReference refToRaed = myRef.child("room1");

        refToRaed.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Players.clear();
                for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                    Player p2 = ds1.getValue(Player.class);
                    Players.add(p2);

                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}




    public void JoinClick(View v) {
        //   Toast.makeText(this, "WQWQWQWQ", Toast.LENGTH_SHORT).show();

        NextIdref.child("thisone").setValue(Nextid);

        if (etNickname.getText().toString().equals("")) {
            NickName = "guest" + (int) (Math.random() * 100000);
        } else {
            NickName = etNickname.getText().toString();
        }

        YourPlayer = new Player(NickName, Nextid , "A");

        myRef.child("room1").child("loginPlayers").push().setValue(YourPlayer);
        startActivity(intent);
    }


}



