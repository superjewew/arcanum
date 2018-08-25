package com.mahavira.base.entity;

import java.util.List;

public class User {

    private String name = "";
    private String email = "";
    private String phone = "";
    private List<String> friends;
    private List<String> recentStores;
    private boolean isPlaying;
    private String playingAt;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(final List<String> friends) {
        this.friends = friends;
    }

    public List<String> getRecentStores() {
        return recentStores;
    }

    public void setRecentStores(final List<String> recentStores) {
        this.recentStores = recentStores;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(final boolean playing) {
        isPlaying = playing;
    }

    public String getPlayingAt() {
        return playingAt;
    }

    public void setPlayingAt(final String playingAt) {
        this.playingAt = playingAt;
    }
}
