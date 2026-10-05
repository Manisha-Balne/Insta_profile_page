package com.example.insta_profile_page;

public class PostModel {

    int image;
    String username;
    String caption;
    int likes;
    int comments;

    public PostModel(int image,
                     String username,
                     String caption,
                     int likes,
                     int comments) {

        this.image = image;
        this.username = username;
        this.caption = caption;
        this.likes = likes;
        this.comments = comments;
    }

    public int getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public String getCaption() {
        return caption;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }
}
