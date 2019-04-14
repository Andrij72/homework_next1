package com.pattern.adapter;

public class AudioPlayer implements MediaPlayer {
    private static final String AUDIOTYPE_FIRST = "mp3";
    private static final String AUDIOTYPE_SECOND = "vlc";
    private static final String AUDIOTYPE_THIRD = "mp4";

    MediaAdapter mediaAdapter;

    @Override
    public void play (String audioType, String fileName) {

        if (audioType.equalsIgnoreCase(AUDIOTYPE_FIRST)) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else if (audioType.equalsIgnoreCase(AUDIOTYPE_SECOND) || audioType.equalsIgnoreCase(AUDIOTYPE_THIRD)) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
