package com.demo.application;

import com.demo.application.model.ApplicationSpecificModel;
import com.demo.common.model.SharedModel;
import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@ApplicationScoped
public class Application {

    @Transactional
    @Scheduled(every = "20s")
    void schedule() {

        SharedModel
                .builder()
                .createdAt(LocalDateTime.now())
                .name("from_shared")
                .build()
                .persist();

        ApplicationSpecificModel
                .builder()
                .createdAt(LocalDateTime.now())
                .name("from_app")
                .build()
                .persist();

        log.info("shared model entries: " + SharedModel.count());
        log.info("application model entries: " + ApplicationSpecificModel.count());
    }

}
