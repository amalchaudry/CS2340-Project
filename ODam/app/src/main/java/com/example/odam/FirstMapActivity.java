package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.odam.databinding.ActivityFirstMapBinding;

public class FirstMapActivity extends AppCompatActivity {

    private  ActivityFirstMapBinding binding;
    private ImageView chosenTowerImage;
    private Bitmap bitmap;
    private Game game;
    //private int money;
    //private int lakeHP;

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

        game = new Game(((GameApplication) getApplication()).getDiff());
        Player player = game.getPlayer();

        binding.moneyText.setText("Money: " + player.getMoney());
        binding.lakeHealthText.setText("Lake HP: " + player.getLakeHP());

        //fisherButton
        binding.fisherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.chooseNewTower(new FishermanTower(game.getDiff()));
                Tower tower = game.getChosenTower();
                newTowerImage(tower);
                binding.towerInfo.setText("Buy: \n" + tower.getName() + " $" + tower.getCost());
            }
        });

        //spearButton
        binding.Spearman.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.chooseNewTower(new SpearTower(game.getDiff()));
                Tower tower = game.getChosenTower();
                newTowerImage(tower);
                binding.towerInfo.setText("Buy: \n" + tower.getName() + " $" + tower.getCost());
            }
        });

        // boatButton
        binding.Boatman.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.chooseNewTower(new BoatTower(game.getDiff()));
                Tower tower = game.getChosenTower();
                newTowerImage(tower);
                binding.towerInfo.setText("Buy: \n" + tower.getName() + " $" + tower.getCost());
            }
        });


        binding.mapImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                chosenTowerImage.bringToFront();
                int action = event.getAction();
                float offsetX = binding.mapImage.getX() - chosenTowerImage.getWidth() / 2;
                float offsetY = binding.mapImage.getY() - chosenTowerImage.getHeight() / 2;
                switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (game.isPlacingChosenTower()) {
                        if (game.canBuyChosenTower()) {
                            chosenTowerImage.setImageResource(game.getChosenTower().getImage());
                            chosenTowerImage.setX((int) event.getX() + offsetX);
                            chosenTowerImage.setY((int) event.getY() + offsetY);
                            chosenTowerImage.setAlpha(0.5f);
                        } else {
                            binding.towerInfo.setText("Buy: Don't have \n enough money!");
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (game.isPlacingChosenTower()) {
                        chosenTowerImage.setX((int) event.getX() + offsetX);
                        chosenTowerImage.setY((int) event.getY() + offsetY);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (game.canPlaceChosenTower(event.getX(), event.getY(), bitmap)) {
                        Tower tower = game.getChosenTower();
                        game.setPlayerMoney(player.getMoney() - tower.getCost());

                        binding.moneyText.setText("Money: " + player.getMoney());
                        binding.towerInfo.setText("Buy: Purchased! \n "
                                + tower.getName() + " for " + tower.getCost());
                        chosenTowerImage.setAlpha(1f);
                        //game.stopChoosingTower();
                    } else {
                        game.stopChoosingTower();
                        chosenTowerImage.setImageResource(0);
                        chosenTowerImage.setOnTouchListener(null);
                    }
                    break;
                default:
                    break;
                }

                return true;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void newTowerImage(Tower tower) {
        chosenTowerImage = new ImageView(FirstMapActivity.this);
        chosenTowerImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    display(tower, chosenTowerImage);
                }
                return true;
            }
        });
        binding.getRoot().addView(chosenTowerImage);
        chosenTowerImage.setAdjustViewBounds(true);
        chosenTowerImage.setMaxHeight(100);
        chosenTowerImage.setMaxWidth(100);
        chosenTowerImage.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT));
        chosenTowerImage.setDrawingCacheEnabled(true);
    }

    public void display(Tower tower, ImageView image) {
        image.setX(200);
        image.setY(200);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(
            1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}