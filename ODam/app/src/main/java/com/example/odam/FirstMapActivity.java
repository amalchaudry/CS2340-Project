package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.odam.databinding.ActivityFirstMapBinding;

public class FirstMapActivity extends AppCompatActivity {

    private  ActivityFirstMapBinding binding;
    private Tower chosenTower;
    private ImageView chosenTowerImage;
    private Difficulty diff;
    private boolean isPlacingTower = false;
    private Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        binding = ActivityFirstMapBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //change this to be equivalent to other configuration screen's difficulty variable
        chosenTowerImage = new ImageView(FirstMapActivity.this);
        binding.getRoot().addView(chosenTowerImage);
        bitmap = drawableToBitmap(binding.mapImage.getDrawable());

        diff = ((GameApplication) getApplication()).getDiff();
        Log.d("Diff", diff.toString());
        int money;
        int lakeHP;
        switch (diff) {
        case MEDIUM:
            money = 500;
            lakeHP = 150;
            break;
        case HARD:
            money = 400;
            lakeHP = 100;
            break;
        default:
            money = 600;
            lakeHP = 200;
            break;
        }

        binding.moneyText.setText("Money: " + money);
        binding.lakeHealthText.setText("Lake HP: " + lakeHP);

//        int[] imageViewCoordinates = new int[2];
//        binding.mapImage.getLocationOnScreen(imageViewCoordinates);
//        binding.testview.setText(Float.toString(imageViewCoordinates[0]) + " " + Float.toString(imageViewCoordinates[1]));

        //testing
        binding.fisherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseNewTower(new FishermanTower(diff));
                isPlacingTower = true;
            }
        });

        //TODO: Try drag and drop listener instead
        binding.mapImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                chosenTowerImage.bringToFront();
                int action = event.getAction();
                switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (isPlacingTower && chosenTower != null) {
                        chosenTowerImage.setImageResource(chosenTower.getImage());
                        chosenTowerImage.setX((int)event.getX() - chosenTowerImage.getWidth() / 2);
                        chosenTowerImage.setY((int)event.getY() - chosenTowerImage.getHeight());
                        chosenTowerImage.setAlpha(0.5f);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isPlacingTower && chosenTower != null) {
                        chosenTowerImage.setX((int)event.getX() - chosenTowerImage.getWidth() / 2);
                        chosenTowerImage.setY((int)event.getY() - chosenTowerImage.getHeight());
                        int selectedArea = bitmap.getPixel((int) (event.getX()), (int) (event.getY() + 50));
                        float[] hsv = new float[3];
                        Color.colorToHSV(selectedArea, hsv);
                        binding.testview.setText(Float.toString(hsv[0]));
                        if (hsv[0] >= 175 && hsv[0] <= 260) {
                            chosenTowerImage.setAlpha(1f);
                        } else {
                            chosenTowerImage.setAlpha(0.5f);
                        }
                    }
                    binding.testview.setText(event.getX() + " " + event.getY());
                    break;
                case MotionEvent.ACTION_UP://TODO: https://stackoverflow.com/questions/17931816/how-to-tell-if-an-x-and-y-coordinate-are-inside-my-button for bounds check
                    if (isPlacingTower) {
                        isPlacingTower = false;
                        int[] imageViewCoordinates = new int[2];
                        binding.mapImage.getLocationOnScreen(imageViewCoordinates);
                        int selectedArea = bitmap.getPixel((int) (event.getX()), (int) (event.getY() + 100));
                        float[] hsv = new float[3];
                        Color.colorToHSV(selectedArea, hsv);
                        binding.testview.setText(Float.toString(hsv[0]));
                        if (hsv[0] >= 175 && hsv[0] <= 260) {
                            chosenTowerImage.setImageResource(0);
                        }
                        chosenTowerImage.setAlpha(1f);
                    }
                    break;
                }
                return true;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void chooseNewTower(Tower tower) {
        chosenTower = tower;
        chosenTowerImage = new ImageView(FirstMapActivity.this);
        chosenTowerImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                case MotionEvent.ACTION_UP:
                    display(tower, chosenTowerImage); //TODO: This will be the code that displays the tower's stats on press
                    break;
                }
                return true;
            }
        });
        binding.getRoot().addView(chosenTowerImage);
        chosenTowerImage.setAdjustViewBounds(true);
        chosenTowerImage.setMaxHeight(100);
        chosenTowerImage.setMaxWidth(100);
        chosenTowerImage.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        chosenTowerImage.setDrawingCacheEnabled(true);
    }

    public void display(Tower tower, ImageView image) {//TODO: Adjust later to display info
        image.setX(200);
        image.setY(200);
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}