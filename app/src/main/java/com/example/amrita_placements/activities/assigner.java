package com.example.amrita_placements.activities;

public class assigner {
    private String Title;
    private String description;
    public assigner()
    {

    }

    public assigner(String title, String description1)
    {
        Title = title;
        description = description1;
    }

    public String getTitle()
    {
        return Title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description2) {
        description = description2;
    }
}
