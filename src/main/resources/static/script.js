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

function loadContent(page) {
     document.getElementById("body").innerHTML="<object type=\"text/html\" data=" + page + "></object>";
}

$(document).ready(function () {
  //do stuff
});
