package com.databasepatterns.example;

import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class MyNotificationListener implements PGNotificationListener {

    @Autowired
    SimpMessagingTemplate template; //the object that sends websocket messages
    private String simpleBrokerPrefix;

    public MyNotificationListener(String simpleBrokerPrefix) {
        this.simpleBrokerPrefix = simpleBrokerPrefix;
    }

    @Override
    public void notification(int processId, String channelName, String payload) {
        template.convertAndSend(simpleBrokerPrefix + "/" + channelName, payload);
    }

}
