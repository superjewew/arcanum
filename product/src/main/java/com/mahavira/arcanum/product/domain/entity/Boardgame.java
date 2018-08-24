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
}
