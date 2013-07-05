<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/reset-fonts-grids.css">
    <link rel="stylesheet" type="text/css" href="/resources/base.css">
    <link rel="stylesheet" type="text/css" href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css">

    <style>
        #map-canvas img {
            max-width: none;
        }
    </style>

    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9PTc9RgylBuf0tSRrQsPI3SSPTjPXOJY&sensor=false">
    </script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

    <script type="text/javascript">
        var markers = [];
        var map;
        var json;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        var findUnresolvedUrl = '/issues/findunresolved?lat=' + center.lat().toFixed(5) + '&lng=' + center.lng().toFixed(5);
        var postUrl = '/issues/add';
        var iconUrl = 'https://maps.gstatic.com/mapfiles/ms2/micons/blue-dot.png';

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 15,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            addNewMarker();
            addExistingMarkers();
        }
        google.maps.event.addDomListener(window, 'load', initialize);


        /**
         *
         */
        function addNewMarker() {
            var markerOptions = {
                position: center,
                draggable:true,
                map:map,
                icon:iconUrl
            };
            $("input#lat").val(center.lat().toFixed(5));
            $("input#lng").val(center.lng().toFixed(5));

            var marker = new google.maps.Marker(markerOptions);
            markers.push(marker);

            google.maps.event.addListener(marker, "dragend", function() {
                var point = marker.getPosition();
                map.panTo(point);
                $("input#lat").val(point.lat().toFixed(5));
                $("input#lng").val(point.lng().toFixed(5));
            });

            google.maps.event.addListener(map, "moveend", function() {
                map.clearOverlays();
                var center = map.getCenter();
                var marker = new google.maps.Marker(center, {draggable: true});
                map.addOverlay(marker);
                google.maps.event.addListener(marker, "dragend", function() {
                    var point = marker.getPosition();
                    map.panTo(point);
                    $("input#lat").val(point.lat().toFixed(5));
                    $("input#lng").val(point.lng().toFixed(5));
                });
            });
        }

        /**
         *
         */
        function addExistingMarkers() {
            $.getJSON(findUnresolvedUrl, function(issues) {
                var infoWindow = new google.maps.InfoWindow();
                for (var i = 0; i < issues.length; i++) {
                    var myLatLng = new google.maps.LatLng(issues[i].latitude, issues[i].longitude);
                    var marker = new google.maps.Marker({
                        position: myLatLng,
                        map: map,
                        animation: google.maps.Animation.DROP,
                        title: issues[i].title
                    });
                    markers.push(marker);
                    google.maps.event.addListener(marker, 'click', (function(marker, i) {
                        return function() {
                            infoWindow.setContent(issues[i].description);
                            infoWindow.open(map, marker);
                        }
                    })(marker, i));
                }
            })
        }

        function setAllMap(map) {
            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(map);
            }
        }
        function deleteOverlays() {
            clearOverlays();
            markers = [];
        }

        function clearOverlays() {
            setAllMap(null);
        }

        // Shows any overlays currently in the array.
        function showOverlays() {
            setAllMap(map);
        }

        function clearInputs() {
            $("input#title").val(null);
            $("textarea#description").val(null);
        }

        $(function() {
            $("#submitBtn").click(function() {
                var title = $("input#title").val();
                var description = $("textarea#description").val();
                var lat = $("input#lat").val();
                var lng = $("input#lng").val();
                $.ajax({
                    type: "GET",
                    url: postUrl,
                    data: "title=" + title + "&description=" + description + "&lat=" + lat + "&lng=" + lng,
                    success: function(data) {
                        deleteOverlays();
                        clearInputs();
                        addNewMarker();
                        addExistingMarkers();
                    }
                });
                return false;
            });
        });
    </script>

</head>
<body style="background-color:#cccccc">
<div id="doc3" class="yui-t3">
    <div id="hd">
    </div>
    <div id="bd">
        <div class="yui-b" style="width:22%; height:59em">
            <p id="result"></p>

            <form id="issueForm" action="" method="POST">
                <fieldset>
                    <legend>ACROPOLIS: ADUAN</legend>
                    <label>Title</label>
                    <input id="title" name="title" type="text" placeholder="Your title..." size="100"/>
                    <label>Description</label>
                    <textarea id="description" name="description" cols="15" rows="16"
                              placeholder="Your description..."></textarea>
                    <input id="lat" name="lat" hidden="true"/>
                    <input id="lng" name="lng" hidden="true"/>
                    <br/>
                    <button id="submitBtn" type="submit" class="btn">Submit</button>
                </fieldset>
            </form>
        </div>
        <div id="yui-main">
            <div class="yui-b">
                <div id="map-canvas" style="width:100%; height:59em"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>

