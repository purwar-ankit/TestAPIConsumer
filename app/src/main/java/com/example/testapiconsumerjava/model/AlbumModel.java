package com.example.testapiconsumerjava.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
* Model Class to store the data values
* @Entity is used with our model to represent a table in the database
* @SerializedName is the name of the corresponding variable in the json response received from the API
* @ColumnInfo is the name of the column in the table
* */

@Entity(tableName = "Album",indices = @Index(value = {"id"},unique = true))
public class AlbumModel {

    @SerializedName("userId")
    @ColumnInfo(name="userId")
    private Integer userId;

    @PrimaryKey@NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Integer id;


    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
