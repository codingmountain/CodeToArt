package com.example.chintugandhwani.codetoart.Pojoclasses.MoviesList;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chintu gandhwani on 12/22/2017.
 */

public class Movies {

    @SerializedName("results")
    private List<Results> results;

    @SerializedName("dates")
    private Dates dates;

    @SerializedName("page")
    private String page;

    @SerializedName("total_pages")
    private String total_pages;

    @SerializedName("total_results")
    private String total_results;

    public List<Results> getResults ()
    {
        return results;
    }

    public void setResults (List<Results> results)
    {
        this.results = results;
    }

    public Dates getDates ()
    {
        return dates;
    }

    public void setDates (Dates dates)
    {
        this.dates = dates;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }
}
