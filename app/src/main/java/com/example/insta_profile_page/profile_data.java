package com.example.insta_profile_page;

public class profile_data {
    private int posts;
    private int followers;
    private int following;

    public profile_data(int posts, int followers, int following) {
        this.posts = posts;
        this.followers = followers;
        this.following = following;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }
}
