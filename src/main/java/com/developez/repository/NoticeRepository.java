package com.developez.repository;

import com.developez.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    // Trova tutte le notifiche attive in base alla data corrente.
    @Query("from Notice n where current_date() between n.noticBegDt and n.noticEndDt")
    List<Notice> findAllActiveNotices();

}
