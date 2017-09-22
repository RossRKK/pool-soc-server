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
      results.forEach(id => tournaments[id] = { id: id });
      loadAll();
    });
  }

  var load = function (id) {
    Server.Tournament.get(id).done(function (result) {
      tournaments[id] = result;
      addToSelector(tournaments[id]);
    });
  }

  var loadAll = function () {
    Object.keys(tournaments).forEach(id => load(id));
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

    document.getElementById("tournament-selector").appendChild(nav);
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
        return node.name;
      }
    } else {
      return "TBC";
    }
  }

  var structure = {
    root: null,
    rounds: []
  }

  var loadBracket = function () {
    createNodeChildren(tournament.finalMatch, 0);

    var maxDepth = structure.rounds.length - 1;
    var expectedSize = Math.pow(2, maxDepth + 1);
    var actualSize = structure.rounds[maxDepth].children.length;
    if (actualSize < expectedSize) {
      var difference = expectedSize - actualSize;

      for (var i = 0; i < difference/2; i++) {
        //TODO for the love of god fix this
        var gameSpacerTop = document.createElement("li");
        gameSpacerTop.innerText = "Fake Player";
        gameSpacerTop.className = "game game-top";

        var gameSpacerBottom = document.createElement("li");
        gameSpacerBottom.innerText = "Fake Player"
        gameSpacerBottom.className = "game game-bottom";

        var spacer = document.createElement("li");

        structure.rounds[maxDepth].appendChild(gameSpacerTop);
        structure.rounds[maxDepth].appendChild(spacer);
        structure.rounds[maxDepth].appendChild(gameSpacerBottom);
      }
    }
  }

  var createNodeChildren = function (node, depth) {
    if (node.contestant1 && node.contestant2) {
      //create the new list
      if (!structure.rounds[depth]) {
        var list = document.createElement("ul");

        structure.root.insertBefore(list, structure.root.firstChild);
        structure.rounds[depth] = list;
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
    /*<li class="game game-top winner">Lousville <span>79</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom ">NC A&T <span>48</span></li>*/
    var top = document.createElement("li");
    top.className = "game game-top";
    top.innerText = getPlayerName(node.contestant1);

    var topScore = document.createElement("span");
    topScore.innerText = node.score1;

    top.appendChild(topScore);

    structure.rounds[depth].appendChild(top);

    var spacer = document.createElement("li");
    spacer.innerHtml = "&nbsp";

    structure.rounds[depth].appendChild(spacer);

    var bottom = document.createElement("li");
    bottom.className = "game game-bottom";
    bottom.innerText = getPlayerName(node.contestant2);

    var bottomScore = document.createElement("span");
    bottomScore.innerText = node.score2;

    bottom.appendChild(bottomScore);

    structure.rounds[depth].appendChild(bottom);
  }

  return {
    init: init,
    load: load
  }
}();
