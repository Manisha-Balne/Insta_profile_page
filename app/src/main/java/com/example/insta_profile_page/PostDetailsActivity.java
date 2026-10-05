package com.example.insta_profile_page;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PostDetailsActivity extends AppCompatActivity {

    ImageView imgPost;
    Button btnback;
    TextView txtUsername, txtCaption,txtLikes,txtComments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_details);

        imgPost = findViewById(R.id.imgPost);
        txtUsername = findViewById(R.id.txtUsername);
        txtCaption = findViewById(R.id.txtCaption);
        txtLikes = findViewById(R.id.txtLikes);
        txtComments = findViewById(R.id.txtComments);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int image = getIntent().getIntExtra("image", 0);
        String username = getIntent().getStringExtra("username");
        String caption = getIntent().getStringExtra("caption");
        int likes = getIntent().getIntExtra("likes", 0);
        int comments = getIntent().getIntExtra("comments", 0);

        imgPost.setImageResource(image);
        txtUsername.setText("Username : " + username);
        txtCaption.setText("Caption : " + caption);
        txtLikes.setText("Likes : " + likes);
        txtComments.setText("Comments : " + comments);
    }
}