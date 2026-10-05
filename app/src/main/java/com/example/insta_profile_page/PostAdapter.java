package com.example.insta_profile_page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    ArrayList<PostModel> list;
    Dialog dialog;

    public PostAdapter(Context context, ArrayList<PostModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {
        PostModel post = list.get(position);
        holder.imgPost.setImageResource(post.getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostDetailsActivity.class);
            intent.putExtra("image", post.getImage());
            intent.putExtra("username", post.getUsername());
            intent.putExtra("caption", post.getCaption());
            intent.putExtra("likes", post.getLikes());
            intent.putExtra("comments", post.getComments());
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            showPostDialog(post);
            return true;
        });

        holder.itemView.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v,MotionEvent event){
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                        dialog = null;
                    }
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {
        ImageView imgPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPost = itemView.findViewById(R.id.imgPost);
        }
    }

    private void showPostDialog(PostModel post) {

        dialog = new Dialog(context);

        dialog.setContentView(R.layout.dialog_post);
        ImageView profileImg = dialog.findViewById(R.id.dialogimage);
        TextView username = dialog.findViewById(R.id.dialogUsername);
        ImageView item = dialog.findViewById(R.id.dialogPost);
        TextView likes = dialog.findViewById(R.id.dialogLikes);
        TextView comments = dialog.findViewById(R.id.dialogComments);
        profileImg.setImageResource(R.drawable.lotusimg);

        username.setText(post.getUsername());
        item.setImageResource(post.getImage());
        likes.setText(post.getLikes() + "Likes ");
        comments.setText(post.getComments() + "Comments");
        dialog.show();
    }
}
