package com.example.chintugandhwani.codetoart.Pojoclasses.MovieImages;

import java.util.List;

public class MovieImages
{
    private String id;

    private List<Backdrops> backdrops;

    private List<Posters> posters;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public List<Backdrops> getBackdrops ()
    {
        return backdrops;
    }

    public void setBackdrops (List<Backdrops> backdrops)
    {
        this.backdrops = backdrops;
    }

    public  List<Posters> getPosters ()
    {
        return posters;
    }

    public void setPosters (List<Posters> posters)
    {
        this.posters = posters;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", backdrops = "+backdrops+", posters = "+posters+"]";
    }
}