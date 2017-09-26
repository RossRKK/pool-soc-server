var Tournament = function() {
  var tournaments = [];

  var tournament;

  var init = function () {
    structure.root = document.getElementById("bracket");

    if (tournaments.length === 0) {
      getAll();
    }

    display();
  }

  var display = function () {
    structure.root.innerText = "";
    structure.rounds = [];

    if (tournament) {
      loadBracket();
    } else {
      structure.root.innerText = "No Tournament Selected";
    }
  }

  var getAll = function () {
    Server.Tournament.getAll().done(function (results) {
      results.forEach(result => {
        tournaments[result.id] = result;
        addToSelector(tournaments[result.id]);
      });
    });
  }

  var select = function (id) {
    tournament = tournaments[id];
    display();
  }

  var addToSelector = function (t) {
    var nav = document.createElement("div");
    nav.className = "nav-element";
    nav.innerText = t.name;
    nav.value = t.id;

    nav.onclick = function (e) {
      select(e.target.value);
    }

    var selector = document.getElementById("tournament-selector");

    var nextElement = null;

    for (var i = 0; i < selector.children.length; i++) {
      if (!nextElement) {
        nextElement = selector.children[i];
      } else if (nextElement.innerText > t.name) {
        nextElement = selector.children[i];
      }
    }

    selector.insertBefore(nav, nextElement);
  }

  var determineWinner = function (node) {
    if (node && (node.contestant1 && node.contestant2)) {
      if (node.score1 > node.score2) {
        return node.contestant1;
      } else if (node.score2 > node.score1) {
        return node.contestant2;
      } else {
        return null;
      }
    } else {
      return node;
    }
  }

  var getPlayerName = function (node) {
    if (node) {
      if (node.contestant1 && node.contestant2) {
        return getPlayerName(determineWinner(node));
      } else {
        return node;
      }
    } else {
      return { name: "TBC" };
    }
  }

  var structure = {
    root: null,
    rounds: [],
    players: []
  }

  var loadBracket = function () {
    createNodeChildren(tournament.finalMatch, 0);

    //fill out the prelim round
    var maxDepth = structure.rounds.length - 1;
    var expectedSize = Math.pow(2, maxDepth + 1);
    var actualSize = structure.rounds[maxDepth].children.length;
    if (actualSize < expectedSize) {
      var difference = expectedSize - actualSize;

      for (var i = 0; i < difference/2; i++) {
        var gameSpacerTop = document.createElement("li");

        var player = structure.players[maxDepth - 1][i + actualSize/3];

        gameSpacerTop.innerText = player.name;
        gameSpacerTop.className = "game game-top player-" + player.id;
        structure.players[maxDepth].push(player);

        var gameSpacerBottom = document.createElement("li");
        gameSpacerBottom.innerText = "BYE"
        gameSpacerBottom.className = "game game-bottom";

        var spacer = document.createElement("li");

        structure.rounds[maxDepth].appendChild(gameSpacerTop);
        structure.rounds[maxDepth].appendChild(spacer);
        structure.rounds[maxDepth].appendChild(gameSpacerBottom);
      }
    }

    //highlight player names
    structure.players[maxDepth].forEach((player) => {
      var playerTags = $(".player-" + player.id);

      playerTags.css("cursor", "default");

      playerTags.on("mouseover", function() {
        playerTags.css("background-color", "#cccccc");
      });
      playerTags.on("mouseleave", function () {
        playerTags.css("background-color", "white");
      })
    })
  }

  var createNodeChildren = function (node, depth) {
    if (node.contestant1 && node.contestant2) {
      //create the new list
      if (!structure.rounds[depth]) {
        var list = document.createElement("ul");

        structure.root.insertBefore(list, structure.root.firstChild);
        structure.rounds[depth] = list;
        structure.players[depth] = [];
      } else {
        var spacer = document.createElement("li");
        structure.rounds[depth].appendChild(spacer);
      }


      createNode(node, depth);

      createNodeChildren(node.contestant1, depth + 1);

      createNodeChildren(node.contestant2, depth + 1);
    }
  }

  var createNode = function (node, depth) {

    function createPlayerTag(node, score, isTop, depth) {
      var tag = document.createElement("li");
      tag.className = isTop ? "game game-top" : "game game-bottom";
      var displayNode = getPlayerName(node)
      tag.innerText = displayNode.name;
      if (displayNode.id) {
        tag.className += " player-" + displayNode.id;
      }
      structure.players[depth].push(displayNode);

      var tagScore = document.createElement("span");
      tagScore.innerText = score;

      tag.appendChild(tagScore);

      structure.rounds[depth].appendChild(tag);
    }

    /*<li class="game game-top winner">Lousville <span>79</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom ">NC A&T <span>48</span></li>*/
    createPlayerTag(node.contestant1, node.score1, true, depth);

    var spacer = document.createElement("li");
    spacer.innerHtml = "&nbsp";

    structure.rounds[depth].appendChild(spacer);

    createPlayerTag(node.contestant2, node.score2, false, depth);
  }

  return {
    init: init
  }
}();
