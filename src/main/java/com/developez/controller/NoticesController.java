package com.developez.controller;

import com.developez.model.Notice;
import com.developez.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticesController {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticesController(NoticeRepository noticesRepository) {
        this.noticeRepository = noticesRepository;
    }

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        // Recupera tutte le notifiche attive
        List<Notice> notices = noticeRepository.findAllActiveNotices();

        // Verifica se sono presenti notifiche
        if (notices != null) {
            // Restituisci le notifiche con ResponseEntity con cache control di 60 secondi
            return ResponseEntity.ok()
                    .cacheControl( CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        } else {
            // Se non sono presenti notifiche, restituisci null
            return null;
        }
    }

}
