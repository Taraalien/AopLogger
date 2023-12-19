package com.webApp.User.logs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class AsyncLogger {


    private  final Logger logger = LoggerFactory.getLogger(AsyncLogger.class);

    private   final int LOG_LIMIT = 5;



    private  final Queue<Log>logQueue=new LinkedList<>();

    private LogRepository logRepository;

    @Autowired
    public AsyncLogger(LogRepository logRepository) {
        this.logRepository = logRepository;
        loadLogsFromDatabase();
    }



    @Async("asyncExecutor")
    public void log(Log log) {

        try {
            logger.info("Async log: {}", log);

            logQueue.add(log);

            if(logQueue.size() > LOG_LIMIT){
                removeOldLogs();
            }
            logRepository.save(log);
        } catch (Exception e) {
            logger.error("Error during asynchronous logging: {}", e.getMessage(), e);
        }
    }


    public  void removeOldLogs(){

        int logToRemove=(int)(LOG_LIMIT * 0.2);

        for (int i=0;i<logToRemove;i++){
            Log removeLog=logQueue.poll();
            if(removeLog != null){
                deleteLog(removeLog);
            }
        }
    }


    private  void deleteLog(Log log){

        try {
            logRepository.delete(log);
        }catch (Exception e){
            logger.error("Error deleting log"+e.getMessage(),e);
        }

    }


    //check db if has record delete previous record
    private void loadLogsFromDatabase() {
        List<Log> existingLogs = logRepository.findAll();
        logQueue.addAll(existingLogs);
    }

}
