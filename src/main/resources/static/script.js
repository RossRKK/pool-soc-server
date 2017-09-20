"use strict";

$(document).ready(function () {
  //switch page to the matchinng hash location
  loadContent();
});

$(window).on('hashchange', function () { //detect hash change
    loadContent();
});

function loadContent() {
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
        $("#body").load("content/tournaments.html");
        break;
      //home
      case "#":
      default:
        $("#body").load("content/home.html");
        break;
     }
}

var Server = {
  generateTournament: function(id, name, players) {
   return $.ajax({
       url: "/api/tournament/create?name=" + name + "&id=" + id,
       method: "POST",
       contentType: "application/json",
       data: JSON.stringify(players)
     });
   }
 }
