package test.blogapplication.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

public class PageRequestUtil {

    public static PageRequest generatePageRequest(Integer page, Integer limit, String sortBy, String sortDir) {
        page = (page != null) ? page : 0;
        limit = (limit != null) ? limit : 10;

        if (Objects.isNull(sortBy)) {
            sortBy = "id";
            sortDir = "asc";
        }

        Sort.Order order;
        try {
            if ("asc".equalsIgnoreCase(sortDir)){
                order = new Sort.Order(Sort.Direction.ASC, sortBy);
            } else {
                order = new Sort.Order(Sort.Direction.DESC, sortBy);
            }
        } catch (Exception e) {
            throw new EntityNotFoundException("");
        }

        Sort sort = Sort.by(order);
        return PageRequest.of(page, limit, sort);
    }
}
