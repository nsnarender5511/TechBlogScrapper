package org.narender.Web;

import org.narender.Objects.Blog;

import java.io.Serializable;
import java.util.List;

public class FetchResponse implements Serializable {

    private int TotalPages;
    private int CurrentPage;
    private List<Blog> blogList;

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }

    public int getCurrentPage() {
        return CurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        CurrentPage = currentPage;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }
}
