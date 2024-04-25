package org.narender.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Blog {
    @Id
    private Long blogid;
    private String timeDate;
    private String title;
    private String url;
    private String company;
    //private String Sumamrry;


    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
