package org.narender.Web;

import java.io.Serializable;

public class FetchRequest implements Serializable {

    private int pageNo = 1;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
