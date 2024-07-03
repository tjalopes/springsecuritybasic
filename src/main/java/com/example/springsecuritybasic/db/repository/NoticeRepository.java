package com.example.springsecuritybasic.db.repository;

import com.example.springsecuritybasic.db.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query("from Notice notice where CURRENT_DATE BETWEEN notice.noticBegDt and notice.noticEndDt")
    List<Notice> findAllActiveNotices();
}
