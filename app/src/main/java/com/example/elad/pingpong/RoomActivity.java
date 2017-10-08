package com.example.elad.pingpong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class RoomActivity extends AppCompatActivity {
        ListView listv1 ;
    PlayerAdapter playerAdapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_room);

            listv1 = (ListView)findViewById(R.id.l1);
            playerAdapter = new PlayerAdapter(getApplicationContext(),0,MainActivity.Players);
            listv1.setAdapter(playerAdapter);


        }

    public void Refresh(View v) {
        //   Toast.makeText(this, "WQWQWQWQ", Toast.LENGTH_SHORT).show();
        MainActivity.myRef.child("room1").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MainActivity.Players.clear();
                for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                    Player p2 = ds1.getValue(Player.class);
                    MainActivity.Players.add(p2);

                }
                listv1.setAdapter(playerAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                MainActivity.Players.clear();
                for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                    Player p2 = ds1.getValue(Player.class);
                    MainActivity.Players.add(p2);

                }
                listv1.setAdapter(playerAdapter);
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
    }
    }

