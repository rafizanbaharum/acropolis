<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        html {
            height: 100%
        }

        body {
            height: 100%;
            margin: 0;
            padding: 0
        }

        #map-canvas {
            height: 100%
        }
    </style>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9PTc9RgylBuf0tSRrQsPI3SSPTjPXOJY&sensor=false">
    </script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script type="text/javascript">

        var map;
        var json;
        /**
         * initialize
         */
        function initialize() {
            var mapOptions = {
                center: new google.maps.LatLng(1.5333, 103.388),
                zoom: 12,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            setMarkers();

        }
        google.maps.event.addDomListener(window, 'load', initialize);


        /**
         *
         * @param map
         * @param issues
         */
        function setMarkers() {
            $.getJSON('nearme_json', function(issues) {
                for (var i = 0; i < issues.length; i++) {
                    var issue = issues[i];
                    var myLatLng = new google.maps.LatLng(issue.latitude, issue.longitude);
                    var marker = new google.maps.Marker({
                        position: myLatLng,
                        map: map,
                        title: issue.title,
                        zIndex: 1
                    });

                    var contentString = '<div id="content">' +
                            '<div id="issueInfo">' +
                            '</div>' +
                            '<h3 id="firstHeading">' + issue.title + '</h3>' +
                            '<div id="bodyContent">' +
                            '<p>' + issue.description + '</p>' +
                            '</div>' +
                            '</div>';

                    var infowindow = new google.maps.InfoWindow({
                        content: contentString
                    });

                    google.maps.event.addListener(marker, 'click', function() {
                        infowindow.open(map, marker);
                    });
                }
            })
        }
        ;

    </script>

</head>
<body>
<div id="map-canvas"/>

</body>


</html>

