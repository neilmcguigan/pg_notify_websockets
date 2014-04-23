package com.databasepatterns.example;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: neilmcguigan@gmail.com
 * @since: 4/11/14
 *
 * I take PostgreSQL NOTIFY messages and send them to Spring WebSocket.
 */
public class PGNotifyToWebsocket {

    @Value("${websocket.simpleBrokerPrefix}")
    private String simpleBrokerPrefix;

    @Autowired
    SimpMessagingTemplate template; //the object that sends websocket messages
    private DataSource dataSource;
    private String[] channels;
    private PGConnection pgConnection;

    public void init() throws SQLException {

        pgConnection = (PGConnection) dataSource.getConnection();

        pgConnection.addNotificationListener(new PGNotificationListener() {
            @Override
            public void notification(int processId, String channelName, String payload) {
                template.convertAndSend(simpleBrokerPrefix + "/" + channelName, payload);
            }
        });

        Statement statement = pgConnection.createStatement();
        for (String channel : channels) {
            statement.addBatch("LISTEN " + channel);
        }
        statement.executeBatch();
        statement.close();
    }

    public void destroy() throws SQLException {

        Statement statement = pgConnection.createStatement();
        for (String channel : channels) {
            statement.addBatch("UNLISTEN " + channel);
        }
        statement.executeBatch();
        statement.close();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
    }

}
