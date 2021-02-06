package com.example.cst438_project_1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cst438_project_1.db.AppDatabase;

/**
 * This Java file is used with Room to save information to DB
 */

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    //mUserID is a unique identifier, next like makes it automatically increment by one
    @PrimaryKey(autoGenerate = true)
    private int mUserId;

    private String mUserName;
    private String mPassword;

    public User(String mUserName, String mPassword) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
