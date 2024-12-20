package com.example.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {
    default Predicate makePredicate(String type, String keyword) {

        QGuestBook qBook = QGuestBook.guestBook;

        BooleanBuilder builder = new BooleanBuilder();

        // id > 0 : range scan
        builder.and(qBook.gno.gt(0L));

        if (type == null)
            return builder;

        // content like '%검색어%' or title like '%검색어%' or writer like '%검색어%'
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("c")) { // 카테고리
            conditionBuilder.and(qBook.content.contains(keyword));
        }
        if (type.contains("t")) { // 제목
            conditionBuilder.and(qBook.title.contains(keyword));
        }
        if (type.contains("w")) { // 저자
            conditionBuilder.and(qBook.writer.contains(keyword));
        }

        // gno > 0 and (content like '%검색어%' or title like '%검색어%' or writer like
        // '%검색어%')
        builder.and(conditionBuilder);
        return builder;
    }

}
