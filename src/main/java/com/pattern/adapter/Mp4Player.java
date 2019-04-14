package com.pattern.adapter;

public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc (String fileName) {
        System.out.println("Unplaying format" + fileName);
    }

    @Override
    public void playMp4 (String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
