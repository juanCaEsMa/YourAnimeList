package org.insbaixcamp.animelist.dataclass;

import org.insbaixcamp.animelist.enums.Season;

public class DataAnime {
    public String imageUrl;
    public String title;
    public int episodes;
    public double score;
    public int rank;
    public Season season;
    public String year;
    public String synopsis;

    public DataAnime(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }
}
