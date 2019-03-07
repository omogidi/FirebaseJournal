package com.example.user.firebasejournal.Model;

/**
 * Created by User on 2/19/2019.
 */

public class Journal
{
    public String desc;
    public String timestamp;
    public String userId;

    public Journal()
    {
    }

    public Journal(String desc, String timestamp, String userId)
    {
        this.desc = desc;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
