package com.example.fanfightchat.Model;

public class ChatData {
    String name, dateTime, bgColor, text, profileTextColor;
    boolean isMyText;

    public ChatData(String name, String dateTime, String bgColor, String text, boolean isMyText, String profileTextColor) {
        this.name = name;
        this.dateTime = dateTime;
        this.bgColor = bgColor;
        this.text = text;
        this.isMyText = isMyText;
        this.profileTextColor = profileTextColor;
    }

    public String getProfileTextColor() {
        return profileTextColor;
    }

    public void setProfileTextColor(String profileTextColor) {
        this.profileTextColor = profileTextColor;
    }

    public boolean isMyText() {
        return isMyText;
    }

    public void setMyText(boolean myText) {
        isMyText = myText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
