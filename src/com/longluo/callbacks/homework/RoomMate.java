package com.callbacks.homework;

public class RoomMate {

    public String getAnswer(String homework) {
        if ("1+1=?".equals(homework)) {
            return "2";
        } else {
            return null;
        }
    }
}