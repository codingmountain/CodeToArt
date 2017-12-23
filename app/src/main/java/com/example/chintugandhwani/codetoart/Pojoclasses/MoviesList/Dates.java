package com.example.chintugandhwani.codetoart.Pojoclasses.MoviesList;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chintu gandhwani on 12/22/2017.
 */

public class Dates {

    @SerializedName("maximum")
    private String minimum;

    @SerializedName("minimum")
    private String maximum;

    public String getMinimum ()
    {
        return minimum;
    }

    public void setMinimum (String minimum)
    {
        this.minimum = minimum;
    }

    public String getMaximum ()
    {
        return maximum;
    }

    public void setMaximum (String maximum)
    {
        this.maximum = maximum;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [minimum = "+minimum+", maximum = "+maximum+"]";
    }
}
