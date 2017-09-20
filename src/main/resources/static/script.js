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
     //document.getElementById("body").innerHTML="<object type=\"text/html\" data=" + page + "></object>";
     //the jquery way doesn't load the scripts but loads the content without a weird wrapper thing
     //the object tag was like an iframe
     $("#body").load(page, Killer.load);
}

$(document).ready(function () {
  //do stuff
});
