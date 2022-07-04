package com.inoriii.hello.spring.service.scheduled;

import lombok.extern.java.Log;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sakura
 */
@Log
@Component
public class TaskPrint {
    @Scheduled(cron = "0/5 * * * * ?")
    private void printTime() {
        log.info("定时任务启动！当前时间为：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}