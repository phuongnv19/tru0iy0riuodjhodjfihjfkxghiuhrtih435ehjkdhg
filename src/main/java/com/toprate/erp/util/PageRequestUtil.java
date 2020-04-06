package com.toprate.erp.util;

import org.springframework.data.domain.PageRequest;

public class PageRequestUtil {
    public static PageRequest getPageRequest(Integer page, Integer pageSize  ) {

        if (page == null || page < 0)
        {page = 0 ;}
        if (pageSize == null || pageSize < 0)
        {pageSize = 10 ;}


        return PageRequest.of(page, pageSize);
    }
}
