<html>
<head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Month', 'Resolved', 'Unresolved'],
                ['January',  100,      310],
                ['February',  1170,      160],
                ['March',  660,       120],
                ['April',  1030,      250],
                ['May',  33,      250],
                ['Jun',  45,      340],
                ['July',  23,      140],
                ['August',  231,      540] ,
                ['September',  12,      244],
                ['October',  45,      22],
                ['November',  265,      121],
                ['December',  29,      123]
            ]);

            var options = {
                title: 'Issue Resolution Performance',
                hAxis: {title: 'Month',  titleTextStyle: {color: 'red'}}
            };

            var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div id="chart_div" style="width: 900px; height: 500px;"></div>
</body>
</html>