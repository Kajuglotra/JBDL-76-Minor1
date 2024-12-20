package org.gfg.JBDL_76_Minor1.service.impl;

import org.gfg.JBDL_76_Minor1.enums.BookFilter;
import org.gfg.JBDL_76_Minor1.service.BookFilterStratergy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookFilterFactory {

    private final Map<BookFilter, BookFilterStratergy> stratergies = new HashMap<>();

    public BookFilterFactory(BookNoFilterImpl bookNoFilter, BookTitleFilterImpl bookTitleFilter, BookTypeFilterImpl bookTypeFilter){
        stratergies.put(BookFilter.BOOK_NO, bookNoFilter);
        stratergies.put(BookFilter.BOOK_TYPE, bookTypeFilter);
        stratergies.put(BookFilter.BOOK_TITLE, bookTitleFilter);
    }
    public BookFilterStratergy getStratergy(BookFilter filter){
        return stratergies.get(filter);
    }

}
