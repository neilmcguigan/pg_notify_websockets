package com.databasepatterns.example;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * I LISTEN and UNLISTEN to PostgreSQL channels, and register a Listener.
 */
public class PGNotifyToWebsocket {

    private PGNotificationListener pgNotificationListener;
    private DataSource dataSource;
    private String[] channels;
    private PGConnection pgConnection;

    public PGNotifyToWebsocket(String[] channels, DataSource dataSource, PGNotificationListener pgNotificationListener) {
        this.channels = channels;
        this.dataSource = dataSource;
        this.pgNotificationListener = pgNotificationListener;
    }

    public void init() throws SQLException {

        pgConnection = (PGConnection) dataSource.getConnection();

        pgConnection.addNotificationListener(pgNotificationListener);

        Statement statement = pgConnection.createStatement();
        for (String channel : channels) {
            statement.addBatch("LISTEN " + SqlFilter.identifier(channel) );
        }
        statement.executeBatch();
        statement.close();
    }

    public void destroy() throws SQLException {

        Statement statement = pgConnection.createStatement();
        statement.execute("UNLISTEN *");
        statement.close();
    }

}
