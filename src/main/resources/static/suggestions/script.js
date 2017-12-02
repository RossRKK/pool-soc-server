Suggest = function () {
  function init() {
    $("#submit-suggestion").on("click", function (e) {
      var subject = $("#subject").val();
      //var body = document.getElementById("suggestion").value;
      var body = $("#suggestion").val();

      $.ajax({
        url: "/api/suggestion",
        method: "POST",
        data: { subject: subject, body: body}
      }).success(function() {
        $("#subject").val("");
        $("#suggestion").val("");

        alert("Thank you for your suggestion");
      }).fail(function () {
        alert("Sorry, your suggestion failed to be submited");
      })
    });
  }

  return {
    init: init
  }
}();
