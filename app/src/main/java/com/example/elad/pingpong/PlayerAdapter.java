package com.example.elad.pingpong;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by elad on 18/06/2017.
 */

    public class PlayerAdapter extends ArrayAdapter {

    public PlayerAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


        TextView TVNickName;

        String str;
    Player ThisPlayer;


        public PlayerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
            super(context, resource, objects);
            this.TVNickName = TVNickName;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View MyView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_xml,parent,false);



            TVNickName = (TextView)MyView.findViewById(R.id.tvOnePlayer);
            ThisPlayer = (Player)getItem(position);
            str =   ThisPlayer.getNickname();
            TVNickName.setTextColor(Color.BLACK);
            TVNickName.setTextSize(25);
            TVNickName.setText("  "+ str);
            TVNickName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.cn1,GameActivity.class);
            //        Toast.makeText(getContext(), (String) getItem(position) + "" , Toast.LENGTH_SHORT).show();
                   MainActivity.OpponetPlayer = (Player) getItem(position);
                 if (MainActivity.YourPlayer.getPk()>MainActivity.OpponetPlayer.getPk()) {
                     MainActivity.YourPlayer.setPlace("A");
                     MainActivity.OpponetPlayer.setPlace("B");
                        MainActivity.ThisPlayRoom = MainActivity.NickName + "" +(MainActivity.OpponetPlayer.getNickname());
                  }
                  else {
                      MainActivity.ThisPlayRoom =  (MainActivity.OpponetPlayer.getNickname()+ "" + MainActivity.YourPlayer.getNickname());
                     MainActivity.YourPlayer.setPlace("B");
                     MainActivity.OpponetPlayer.setPlace("A");
                  }
                    MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).push().setValue(MainActivity.YourPlayer);
                    MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).push().setValue(MainActivity.OpponetPlayer);

                    MainActivity.cn1.startActivity(intent);



                }



            });
            return MyView;
        }




    }



