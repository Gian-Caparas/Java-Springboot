package com.example.firstspringbootapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int itemid;
    private String name;
    private String unit;
    private float price;

    //generate constructor
    public ItemEntity(){} //default constructor

    public ItemEntity(int itemid, String name, String unit, float price) {
        this.itemid = itemid;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    //generate setter methods for certain attributes
    public void setItemId(int itemid){ this.itemid = itemid;}
    public void setName(String name){ this.name = name;}
    public void setUnit(String unit){ this.unit = unit;}
    public void setPrice(float price){ this.price = price;}

    //generate getter methods for all attributes
    public int getItemId(){ return itemid;}
    public String getName(){ return name;}
    public String getUnit(){ return unit;}
    public float getPrice(){ return price;}
}