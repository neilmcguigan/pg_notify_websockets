A basic PostgreSQL NOTIFY to WebSocket sample application.

The key was to use the PGJDBC-NG driver, instead of the regular JDBC driver, as it supports asynchronous notifications.

You can double-click the JAR to run this server. You may need to setup the following environment variables:

    PGHOST (default localhost)
    PGPORT (default 5432)
    PGDATABASE (default postgres)
    PGUSER (default postgres)
    PGPASSWORD (no default)

Then open your browser to http://localhost:9876 and follow the instructions.

Todo:

use embedded jetty or tomcat so can just run as a jar (spring boot)
try to not use spring-webmvc dependency at all? just serving a static html page.


