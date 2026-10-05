package com.example.insta_profile_page;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.google.android.material.tabs.TabLayout;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton btnBack;
    ImageView btnmenu;
    TextView posts_count,followers_count,following_count,txtEmpty;
    ImageView imgEmpty;
    Button btnedit;
    TabLayout tabLayout;
    RecyclerView recyclerPosts;
    ArrayList<PostModel> postsList;
    ArrayList<PostModel> reelsList;
    ArrayList<PostModel> taggedList;
    PostAdapter adapter;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnmenu = findViewById(R.id.btnmenu);
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnmenu);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();
            }
        });


        imgEmpty = findViewById(R.id.imgEmpty);
        txtEmpty = findViewById(R.id.txtEmpty);

        profile_data profileData = new profile_data(24,1250,350);
        posts_count = findViewById(R.id.posts_count);
        followers_count = findViewById(R.id.followers_count);
        following_count = findViewById(R.id.following_count);

        posts_count.setText(String.valueOf(profileData.getPosts()));
        followers_count.setText(String.valueOf(profileData.getFollowers()));
        following_count.setText(String.valueOf(profileData.getFollowing()));

        btnedit = findViewById(R.id.btnedit);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Edit Profile clicked",
                        Snackbar.LENGTH_LONG);
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });

        postsList = new ArrayList<>();
        recyclerPosts = findViewById(R.id.recyclerPosts);
        recyclerPosts.setLayoutManager(new GridLayoutManager(this, 3));


        reelsList = new ArrayList<>();
        taggedList = new ArrayList<>();
        loadPostsData();
        loadReelsData();
        loadTaggedData();
        adapter = new PostAdapter(this, postsList);
        recyclerPosts.setAdapter(adapter);
        updateUI(postsList);

        gestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener() {
            private static final int Swipe_threshold = 100;
            private static final int Swipe_Velocity_threshold = 100;

            @Override
            public boolean onFling(MotionEvent e1 ,MotionEvent e2 ,float VelocityX,float VelocityY){
                if (e1 == null || e2 == null){
                    return false;
                }
                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();

                if (Math.abs(diffX) > Math.abs(diffY)){
                    if (Math.abs(diffX) > Swipe_threshold && Math.abs(VelocityX) > Swipe_Velocity_threshold) {
                        int currentTab = tabLayout.getSelectedTabPosition();
                        if(diffX > 0){
                            if (currentTab > 0){
                                tabLayout.getTabAt(currentTab - 1).select();
                            }
                        }else{
                            if (currentTab < tabLayout.getTabCount() - 1){
                                tabLayout.getTabAt(currentTab + 1).select();
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        recyclerPosts.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

        tabLayout = findViewById(R.id.tabLayout);


        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                        if (tab.getPosition() == 0) {
                            adapter = new PostAdapter(MainActivity.this, postsList);
                            updateUI(postsList);

                        } else if (tab.getPosition() == 1) {
                            adapter = new PostAdapter(MainActivity.this, reelsList);
                            updateUI(reelsList);

                        } else {
                            adapter = new PostAdapter(MainActivity.this, taggedList);
                            updateUI(taggedList);
                        }
                        recyclerPosts.setAdapter(adapter);
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
    }

    public void loadPostsData(){
        postsList.add(new PostModel(R.drawable.nature1, "manisha", "Warm morning", 145, 20));

        postsList.add(new PostModel(R.drawable.nature2, "manisha", "Forest beauty", 300, 50));

        postsList.add(new PostModel(R.drawable.nature4, "manisha", "Beautiful scenario", 500, 90));
    }

    public void loadReelsData(){
        reelsList.add(new PostModel(R.drawable.nature1, "manisha", "Reel 1", 100, 10));

        reelsList.add(new PostModel(R.drawable.nature2, "manisha", "Reel 2", 200, 25));
    }

    public void loadTaggedData(){

        taggedList.add(
                new PostModel(R.drawable.nature4, "friend", "Tagged 1", 150, 18));

        taggedList.add(
                new PostModel(R.drawable.nature2, "friend", "Tagged 2", 250, 35));
    }

    private void updateUI(ArrayList<PostModel> list) {
        if(list.isEmpty()){
            recyclerPosts.setVisibility(View.GONE);
            imgEmpty.setVisibility(View.VISIBLE);
            txtEmpty.setVisibility(View.VISIBLE);
        }
        else{
            recyclerPosts.setVisibility(View.VISIBLE);
            imgEmpty.setVisibility(View.GONE);
            txtEmpty.setVisibility(View.GONE);
        }
    }
}