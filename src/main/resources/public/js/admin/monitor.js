/**
 * Created by lzz on 2018/10/9.
 */

autoGetUser(function(obj){
    window.user = obj.res;
    window.database=obj.res["databaseName"];

    getTotalCount(window.database, 1, function (obj) {
        $("#monitor-today").text( obj.res["c"] );
    });

    getTotalCount(window.database, 7, function (obj) {
        $("#monitor-week").text( obj.res["c"] );
    });

    getTotalCount(window.database, 30, function (obj) {
        $("#monitor-month").text( obj.res["c"] );
    });

    getTotalCount(window.database, 1000, function (obj) {
        $("#monitor-total").text( obj.res["c"] );
    });

    getGroupByUserAgent(window.database, function (obj) {
        var labelArr = [];
        var dataArr = [];
        var arr = obj.res;
        for(var i = 0; i < arr.length; i++){
            dataArr.push( arr[i]["c"] );
            labelArr.push( arr[i]["user_agent"] );
        }
        var pieData = {
            type: 'pie',
            data:{
                datasets: [{
                    data: dataArr
                }],

                labels: labelArr
            }
        };
        var ctx = document.getElementById("pie").getContext("2d");
        new Chart(ctx, pieData);
    });

    getGroupByCountTotal(window.database,function (obj) {
        console.log(obj);
        var labelArr = [];
        var totalArr = [];
        var uidArr = [];
        var uidObj = obj.res["uid"];
        var totalObj = obj.res["total"];
        for(var i = 0; i < totalObj.length; i++){
            labelArr.push( totalObj[i]["date"] );
            totalArr.push( totalObj[i]["c"] );
            uidArr.push( uidObj[i]["c"] );
        }
        var barChartData = {
            type: 'bar',
            data: {
                labels: labelArr,
                datasets: [{
                    label: "My1",
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    borderWidth: 1,
                    data : totalArr
                },
                {
                    label: "My2",
                    backgroundColor: 'rgb(209, 209, 209)',
                    borderColor: 'rgb(209, 209, 209)',
                    borderWidth: 1,
                    data : uidArr
                }
                ]
            },
            options: {
                responsive: true,
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: 'Chart.js Bar Chart'
                }
            }

        };
        var ctx = document.getElementById("bar").getContext("2d");
        new Chart(ctx, barChartData);
    });

    getMonitorDetail(window.database, function (data) {
        var obj = data.res;
        var tableStr = "";
        for(var i in obj){
            tableStr += '<tr>';
            tableStr += '<th>' + obj[i]['id'] + '</th>';
            tableStr += '<th>' + obj[i]['note'] + '</th>';
            tableStr += '<th>' + obj[i]['user_agent'] + '</th>';
            tableStr += '<th>' + obj[i]['ip'] + '</th>';
            tableStr += '<th>' + obj[i]['add_time'] + '</th>';
            tableStr += '</tr>';
        }
        $("#monitor-detail-table").html(tableStr);
        $("#detail-table").dataTable({
            pageLength:15,
            lengthMenu: [15, 30, 50, 100, 200, 300 ],
            order: [[ 1, 'asc' ]]
        });
    })
});
