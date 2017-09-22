"use strict";

$(document).ready(function () {
  //switch page to the matchinng hash location
  Router.route();
});

$(window).on('hashchange', function () { //detect hash change
    Router.route();
});

var Router = function () {
  var route = function() {
       switch (window.location.hash) {
        //killer
        case "#killer":
          $("#body").load("killer/index.html", Killer.load);
          break;
        case "#about":
          $("#body").load("content/about.html");
          break;
        case "#events":
          $("#body").load("content/events.html");
          break;
        case "#tournaments":
          $("#body").load("tournament/page.html", Tournament.init);
          break;
        //home
        case "#":
        default:
          $("#body").load("content/home.html");
          break;
       }
  }
  return {
    route: route
  }
}();

var Server = {
  Tournament: {
    create: function(name, players) {
     return $.ajax({
         url: "/api/tournament/create?name=" + name,
         method: "POST",
         contentType: "application/json",
         data: JSON.stringify(players)
       });
     },
     get: function (id) {
       return $.ajax({
           url: "/api/tournament/" + id,
           method: "GET"
         });
     },
     getAll: function () {
       return $.ajax({
           url: "/api/tournament",
           method: "GET"
        });
     }
   }
 }
