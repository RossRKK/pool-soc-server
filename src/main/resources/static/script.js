"use strict";
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
