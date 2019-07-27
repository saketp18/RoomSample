package com.lite.myapplication;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Saket on 24,July,2019
 */
@Entity
public class MessageDB{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "messageId")
    private int messageId;

    private String message;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
