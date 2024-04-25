package org.narender.Web;

import org.narender.DOA.BlogManagerWithJDBC;
import org.narender.Objects.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FetchBlogController {

    private static final Logger logger = LoggerFactory.getLogger(FetchBlogController.class);
    @PostMapping("/fetchBlog")
    public FetchResponse fetchBlog(@RequestBody FetchRequest request) {
        FetchResponse response = new FetchResponse();

        logger.info("Request received for /fetchBlog - {}", request.toString());

        // Here, insert your logic to fetch the blog data.  This example assumes fetching is successful.

        List<Blog> blogList = BlogManagerWithJDBC.fetchBlogsPaginated(request.getPageNo());
        int totalPages = BlogManagerWithJDBC.TotalBlogs();
        response.setTotalPages(totalPages);
        response.setCurrentPage(request.getPageNo());
        response.setBlogList(blogList);
        return response;
    }


}
