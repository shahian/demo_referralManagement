package com.haytech.demo_referralmanagement.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component
public class PageableUtility {

    public Pageable createPageable(Integer pageNumber, Integer pageSize) {
        if (pageNumber == null) return null;
        if (pageSize == null || pageSize < 1) pageSize = Integer.parseInt(ApplicationProperties.getProperty("application.page.size"));
        return PageRequest.of(
                pageNumber - 1,
                pageSize,
                Sort.by("id").descending()
        );
    }

    public Pageable createPageable(Integer pageNumber, Integer pageSize, Sort sort) {
        if (pageNumber == null) return null;
        if (pageSize == null || pageSize < 1) pageSize = Integer.parseInt(ApplicationProperties.getProperty("application.page.size"));
        return PageRequest.of(
                pageNumber - 1,
                pageSize,
                sort
        );
    }

}
