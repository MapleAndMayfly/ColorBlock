package com.kaede.model;

public abstract class Note
{
    private enum NoteTrack {
        SUSPENDED,  // 无轨 - without track
        RIGHT,
        UP,
        LEFT,
        DOWN
    }
    
    private enum ColourType
    {
        UR,         // 右上 - upper right
        UL,         // 左上 - upper left
        UMIX        // 双色 - both of above
    }

    private final NoteTrack track;
    private final ColourType color;
    private float speed;
    private int judgeTick;
    private double position;
    private Result judgeStat;

    Note(NoteTrack track, ColourType color, float speed)
    {
        this.track = track;
        this.color = color;
        this.speed = speed;
    }

    protected abstract void changeStat();

    public void move()
    {

    }

    public void update()
    {

    }

    public Result judge()
    {
        return null;
    }
}