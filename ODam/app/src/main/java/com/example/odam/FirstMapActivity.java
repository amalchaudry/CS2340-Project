package com.example.odam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.odam.databinding.ActivityFirstMapBinding;
import com.example.odam.fish.Fish;
import com.example.odam.gameLogic.Game;
import com.example.odam.gameLogic.GameApplication;
import com.example.odam.gameLogic.Player;
import com.example.odam.tower.BoatTower;
import com.example.odam.tower.FishermanTower;
import com.example.odam.tower.SpearTower;
import com.example.odam.tower.Tower;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FirstMapActivity extends AppCompatActivity {

    private ActivityFirstMapBinding binding;
    private ImageView chosenTowerImage;
    private Bitmap bitmap;
    private Game game;
    private Timer timer = new Timer();
    private Timer timer2 = new Timer();
    private ArrayList<ImageView> fishViews = new ArrayList<>();
    private View v;
    public static double fps = 24.0;
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
        game = new Game(((GameApplication) getApplication()).getDiff(), FirstMapActivity.this);
        Player player = game.getPlayer();
        binding.moneyText.setText("Money: " + player.getMoney());
        binding.lakeHealthText.setText("HP: " + player.getLakeHP());
        binding.startCombatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startCombat(player);
            }
        });
        binding.fisherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.selectNewTower(new FishermanTower(game.getDiff()));
                Tower tower = game.getChosenTower();
                newTowerImage(tower);
                binding.towerInfo.setText("Buy: \n" + tower.getName() + " $" + tower.getCost());
            }
        });
        binding.Spearman.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.selectNewTower(new SpearTower(game.getDiff()));
                Tower tower = game.getChosenTower();
                newTowerImage(tower);
                binding.towerInfo.setText("Buy: \n" + tower.getName() + " $" + tower.getCost());
            }
        });
        binding.Boatman.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.selectNewTower(new BoatTower(game.getDiff()));
                Tower tower = game.getChosenTower();
                newTowerImage(tower);
                binding.towerInfo.setText("Buy: \n" + tower.getName() + " $" + tower.getCost());
            }
        });
        binding.mapImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return mapImageFunc(event, player);
            };
        });
    }


    public void startCombat(Player player){
        if (!game.isCombatStarted()) {
            game.startCombat();
            // inst. task for updating coordinates
            TimerTask updateTask = new TimerTask() {
                @Override
                public void run() {
                    ArrayList<Integer> fishIndicesToRemove = new ArrayList<>();
                    game.update(timer, fishIndicesToRemove);
                    gameOver(player);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.lakeHealthText.setText("HP: " + player.getLakeHP());
                            for (int i = fishIndicesToRemove.size() - 1; i > 0; i--){
                                fishViews.remove(fishIndicesToRemove.get(i));
                            }
                            for (int i = 0; i < fishViews.size(); i++) {
                                ImageView fishView = fishViews.get(i);
                                Fish fish = game.getFishArr().get(i);
                                fishView.setX(fish.getX());
                                fishView.setY(fish.getY());
                            }
                        }
                    });
                }

            };
            // inst. task for adding new fish onto map
            TimerTask addFishTask = new TimerTask() {
                @Override
                public void run() {
                    Fish fish = game.addFish(timer2);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ImageView fishView = new ImageView(FirstMapActivity.this);
                            binding.getRoot().addView(fishView);
                            fishViews.add(fishView);
                            fishView.bringToFront();
                            fishView.setImageResource(fish.getImage());
                            fishView.setX(fish.getX());
                            fishView.setY(fish.getY());
                            fishView.setAdjustViewBounds(true);
                            fishView.setMaxHeight(100);
                            fishView.setMaxWidth(100);
                            fishView.setLayoutParams(new ConstraintLayout.LayoutParams(
                                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
                            fishView.setDrawingCacheEnabled(true);
                        }
                    });

                }
            };
            // update coordinate of fish time
            // delay: Schedules the specified task for execution after the specified delay.
            // delay: in milliseconds before task is to be executed.
            int period = (int) ((1/ fps) * 1000);
            timer.scheduleAtFixedRate(updateTask, 0, period);
            // add fish until the 15th fish is added, then stop adding new fish
            timer2.scheduleAtFixedRate(addFishTask, 1000, 1000);
        }
    }

    public boolean mapImageFunc(MotionEvent event, Player player) {
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
                binding.testview.setText("X: " + event.getRawX() + " Y: " + event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                if (game.canPlaceChosenTower(event.getX(), event.getY(), bitmap)
                        & game.canBuyChosenTower()) {
                    Tower tower = game.getChosenTower();
                    tower.setX((int) event.getRawX() - 130);
                    tower.setY((int) event.getRawY());
                    game.setPlayerMoney(player.getMoney() - tower.getCost());
                    game.getTowerArr().add(tower);
                    binding.testview.setText(Integer.toString(game.getTowerArr().get(0).getX()) + " " + Integer.toString(game.getTowerArr().get(0).getY()));
                    binding.moneyText.setText("Money: " + player.getMoney());
                    binding.towerInfo.setText("Buy: Purchased! \n "
                            + tower.getName() + " for " + tower.getCost());
                    chosenTowerImage.setAlpha(1f);
                } else if (game.getChosenTower() != null) {
                    game.deselectTower();
                    chosenTowerImage.setImageResource(0);
                    chosenTowerImage.setOnTouchListener(null);
                }
                break;
            default:
                break;
        }
        return true;
    }

    public boolean displayFishHealth(MotionEvent event, Player player) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {

        }
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
    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, GameOverActivity.class);
        startActivity(switchActivityIntent);
    }
    public void gameOver(Player player) {
        boolean gO = game.checkGameOver(player);
        if (gO) {
            timer.cancel();
            switchActivities();
        }
    }
}