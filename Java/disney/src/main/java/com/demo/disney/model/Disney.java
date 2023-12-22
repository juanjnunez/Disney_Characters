package com.demo.disney.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Disney {
    private int id;
    private String title;
    @JsonProperty("char_id")
    private int charId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    @Override
    public String toString() {
        return "Disney{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", charId=" + charId +
                '}';
    }
}
