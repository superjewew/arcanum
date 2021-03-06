package com.mahavira.base.entity;

import com.mahavira.base.exception.FriendAlreadyAddedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private String name = "";
    private String email = "";
    private String phone = "";
    private List<String> friends = new ArrayList<>();
    private List<String> recentStores = new ArrayList<>();
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

    public void addFriend(final String friend) {
        if(friends.contains(friend)) {
            throw new FriendAlreadyAddedException();
        }
        friends.add(friend);
    }

    public List<String> getRecentStores() {
        return recentStores;
    }

    public void addRecentStores(final String recentStore) {
        if(recentStores.contains(recentStore)) {
            recentStores.remove(recentStore);
        }
        Collections.reverse(recentStores);
        recentStores.add(recentStore);
        Collections.reverse(recentStores);
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
