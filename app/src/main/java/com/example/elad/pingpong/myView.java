package com.example.elad.pingpong;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by elad on 14/06/2017.
 */

public class myView extends View {
    int Ax,Bx;
    int ServerFlag = 0;
    int BallX = 500,BallY = 500;
    int BallX_B,BallY_B;
    int BallStepX,BallStepY;
    board upBoard,downBoard;
    boolean FirstTimeDraw = true;
    public myView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (FirstTimeDraw) {
            upBoard = new board(100, 100, 200);
            downBoard = new board(100, 1500, 200);
            BallStepX = canvas.getWidth()/100;
            BallStepY = canvas.getHeight()/180;
            FirstTimeDraw = false;
        }

        Paint pn1 = new Paint();
        pn1.setColor(Color.BLACK);
        pn1.setStrokeWidth(30);

        //     canvas.drawCircle(BallX, BallY, 30, pn1);

        //  server get new x and y of ball
        // if (ServerFlag == 1000) {

        //    }
         ServerFlag++;
        if (MainActivity.YourPlayer.getPlace().equals("A")) {
            if (BallX > canvas.getWidth()) {
                BallStepX = BallStepX * (-1);
            }
            if (BallX < 0) {
                BallStepX = BallStepX * (-1);
            }
        }
        //----- ballb
        if (MainActivity.YourPlayer.getPlace().equals("A")) {
            if (BallY > downBoard.getY()) {
                if (BallX > downBoard.getX()- downBoard.getLength()/2 && BallX < downBoard.getX() + downBoard.getLength()/2) {
                    BallStepY = BallStepY * (-1);
                } else {
                    Goal("B");
                }
            }
        }
        if (MainActivity.YourPlayer.getPlace().equals("B")) {
            if (BallY_B > downBoard.getY()) {
                if (BallX_B > downBoard.getX() -downBoard.getLength()/2&& BallX_B < downBoard.getX() + downBoard.getLength()/2) {
               //     BallStepY = BallStepY * (-1);
                } else {
                    Goal("B");
                }
            }
        }

        // ------ ballb
        if (MainActivity.YourPlayer.getPlace().equals("A")) {
            if (BallY < upBoard.getY()) {
                if (BallX > upBoard.getX()- upBoard.getLength()/2 && BallX < upBoard.getX() + upBoard.getLength()/2) {
                    BallStepY = BallStepY * (-1);
                } else {
                    Goal("A");
                }
            }
        }
        if (MainActivity.YourPlayer.getPlace().equals("B")) {
            if (BallY_B < upBoard.getY()) {
                if (BallX_B > upBoard.getX()-upBoard.getLength()/2 && BallX_B < upBoard.getX() + upBoard.getLength()/2) {
               //     BallStepY = BallStepY * (-1);
                } else {
                    Goal("A");
                }
            }
        }
        if (MainActivity.YourPlayer.getPlace().equals("A")) {
            BallY += BallStepY;
            BallX += BallStepX;
        }
        if (MainActivity.YourPlayer.getPlace().equals("B")) {
            BallY += BallStepY;
            BallX += BallStepX;
        }

            BallX_B = canvas.getWidth() - BallX;
            BallY_B = (int) (downBoard.getY() + upBoard.getY()) - BallY;

        if (MainActivity.YourPlayer.getPlace().equals("A")) {
            canvas.drawCircle(BallX, BallY, 30, pn1);
            //        pn1.setColor(Color.RED);
            //        canvas.drawCircle(BallX_B, BallY_B, 30, pn1);
            //        pn1.setColor(Color.BLACK);
        }
        if (MainActivity.YourPlayer.getPlace().equals("B")) {
            canvas.drawCircle(BallX_B, BallY_B, 30, pn1);
            //          pn1.setColor(Color.RED);
            //          canvas.drawCircle(BallX, BallY, 30, pn1);
            //          pn1.setColor(Color.BLACK);
        }





        // boards get x and y from server
        MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("PlayerAx").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Ax = dataSnapshot.getValue(Integer.class);
                //  Nextid = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

        MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("PlayerBx").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Bx = dataSnapshot.getValue(Integer.class);
                //  Nextid = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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


        //  ball get x and y from server

        if (ServerFlag == 10) {
            ServerFlag = 0;


            if (MainActivity.YourPlayer.getPlace().equals("A")) {
                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("Ballx").child("Ballx1").setValue(BallX);
                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("Bally").child("Bally1").setValue(BallY);
                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("Ballx_b").child("Ballx1_b").setValue(BallX_B);
                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("Bally_b").child("Bally1_b").setValue(BallY_B);
                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("BallStepx").child("BallStepx1").setValue(BallStepX);
                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("BallStepy").child("BallStepy1").setValue(BallStepY);
            }


            //        ServerFlag = 0;


            if (MainActivity.YourPlayer.getPlace().equals("B")) {
                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("Ballx").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        BallX = dataSnapshot.getValue(Integer.class);
                        //  Nextid = dataSnapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("Bally").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        BallY = dataSnapshot.getValue(Integer.class);
                        //  Nextid = dataSnapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("Ballx_b").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        BallX_B = dataSnapshot.getValue(Integer.class);
                        //  Nextid = dataSnapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("Bally_b").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        BallY_B = dataSnapshot.getValue(Integer.class);
                        //  Nextid = dataSnapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("BallStepx").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        BallStepX = (dataSnapshot.getValue(Integer.class));
                        //  Nextid = dataSnapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("BallStepy").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        BallStepY= (dataSnapshot.getValue(Integer.class));
                        //  Nextid = dataSnapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
        //ball movement control



        // boards movement control

        if (MainActivity.YourPlayer.getPlace().equals("A")) {
            if (Ax<downBoard.getX()) {
                downBoard.setX(downBoard.getX() - canvas.getWidth()/40);
            }
            if (Ax>downBoard.getX()) {
                downBoard.setX(downBoard.getX() + canvas.getWidth()/40);
            }

            if (canvas.getWidth()-Bx<upBoard.getX()) {
                upBoard.setX(upBoard.getX() - canvas.getWidth()/40);
            }
            if (canvas.getWidth()-Bx>upBoard.getX()) {
                upBoard.setX(upBoard.getX() + canvas.getWidth()/40);
            }
        }
        if (MainActivity.YourPlayer.getPlace().equals("B")) {
            if (Bx<downBoard.getX()) {
                downBoard.setX(downBoard.getX() - canvas.getWidth()/40);
            }
            if (Bx>downBoard.getX()) {
                downBoard.setX(downBoard.getX() + canvas.getWidth()/40);
            }

            if (canvas.getWidth()-Ax<upBoard.getX()) {
                upBoard.setX(upBoard.getX() - canvas.getWidth()/40);
            }
            if (canvas.getWidth()-Ax>upBoard.getX()) {
                upBoard.setX(upBoard.getX() + canvas.getWidth()/40);
            }
        }



        downBoard.DrawBoard(canvas);
        upBoard.DrawBoard(canvas);

        pn1.setTextSize(40);

        canvas.drawText("Sv = "+ServerFlag+" BallX = "+BallX+" BallY = "+ BallY ,300,300,pn1);
        canvas.drawText(" BallStepX = "+ BallStepX+ " BallStepY = "+ BallStepY,300,350,pn1);
        canvas.drawText(" BallX-B = "+ BallX_B+ " BallY-B = "+ BallY_B,300,400,pn1);
        canvas.drawText(" Ax = "+ Ax + " Bx = "+ Bx,300,450,pn1);





        task1 t1 = new task1();
        t1.execute();
    }
    public void Goal(String WhichPlayer){
        Toast.makeText(getContext(), "Goal for Player " + WhichPlayer, Toast.LENGTH_SHORT).show();
        BallX = 500;
        BallY = 500;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //   Toast.makeText(getContext(), "WAWAWAW", Toast.LENGTH_SHORT).show();
        if (MainActivity.YourPlayer.getPlace().equals("A")) {
            MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("PlayerAx").child("PlayerAx1").setValue(event.getX());
        }
        if (MainActivity.YourPlayer.getPlace().equals("B")) {
            MainActivity.myRef.child("room2").child("gameRooms").child(MainActivity.ThisPlayRoom).child("PlayerBx").child("PlayerBx1").setValue(event.getX());
        }


        return true;
    }
    class task1 extends AsyncTask<Integer, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);


            invalidate();
        }


        @Override
        protected Void doInBackground(Integer... params) {
            try {
                //       while (MainActivity.flag11)
                {


                    publishProgress();
                    Thread.sleep(10);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
