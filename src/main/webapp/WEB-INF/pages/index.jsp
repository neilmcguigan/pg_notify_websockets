<!doctype html>
<html>
<head>
    <title>PostgreSQL NOTIFY to Websocket</title>

    <script src="//cdn.sockjs.org/sockjs-0.3.min.js"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.1/stomp.js"></script>

    <script>

        var STOMP_ENDPOINT_PATH = '/hello';
        var CHANNELS = ['dml_events', 'ddl_events'];
        var SIMPLE_BROKER_PREFIX = '/topic';

        var socket = new SockJS(STOMP_ENDPOINT_PATH);
        var stompClient = Stomp.over(socket);
        var headers = {};

        stompClient.connect(headers, function (frame) {

            for (var i = 0; i < CHANNELS.length; i++) {

                var channel = CHANNELS[i];

                stompClient.subscribe(SIMPLE_BROKER_PREFIX + '/' + channel, function (message) {
                    console.log(message);
                });
            }

        });

    </script>

</head>
<body>
<p>Open your browser console, and run NOTIFY dml_events, 'some message'; in Postgres. Tested with Chrome and
    Firefox.</p>
</body>
</html>