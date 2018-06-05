var http = require("http");
setInterval(function() {
    http.get("http://leagueofcomps.herokuapp.com");
}, 300000); // every 5 minutes (300000)