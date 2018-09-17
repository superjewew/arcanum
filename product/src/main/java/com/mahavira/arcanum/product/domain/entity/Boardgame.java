package com.mahavira.arcanum.product.domain.entity;

import java.util.List;

/**
 * Created by norman on 17/07/18.
 *
 */

public class Boardgame {
    private String name;
    private int quantity;
    private List<String> components;
    String numPlayersFrom;
    String numPlayersTo;
    String gameTime;
    String releaseYear;
    String playerAge;
    String howToPlayUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public String getNumPlayersFrom() {
        return numPlayersFrom;
    }

    public void setNumPlayersFrom(final String numPlayersFrom) {
        this.numPlayersFrom = numPlayersFrom;
    }

    public String getNumPlayersTo() {
        return numPlayersTo;
    }

    public void setNumPlayersTo(final String numPlayersTo) {
        this.numPlayersTo = numPlayersTo;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(final String gameTime) {
        this.gameTime = gameTime;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(final String playerAge) {
        this.playerAge = playerAge;
    }

    public String getHowToPlayUrl() {
        return howToPlayUrl;
    }

    public void setHowToPlayUrl(final String howToPlayUrl) {
        this.howToPlayUrl = howToPlayUrl;
    }
}
