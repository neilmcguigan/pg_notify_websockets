A basic PostgreSQL NOTIFY to WebSocket sample application.

The key was to use the PGJDBC-NG driver, instead of the regular JDBC driver, as it supports asynchronous notifications.

You can double-click the JAR to run this server. You may need to setup the following O/S environment variables:

    PGPASSWORD (no default)
    PGHOST (defaults to localhost)
    PGPORT (defaults to 5432)
    PGDATABASE (defaults to postgres)
    PGUSER (defaults to postgres)

Then open your browser to http://localhost:9876 and follow the instructions.

Todo:

- use embedded jetty or tomcat so can just run as a jar (spring boot)
- allow user to indicate which channels wants to listen to in browser


