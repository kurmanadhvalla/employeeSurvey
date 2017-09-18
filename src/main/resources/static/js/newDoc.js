var empID = 1200;
var myvar = '<a id ="demo" href="survey.html" class="btn btn-secondary btn-lg btn-block" >click here to view previous response</a>';
$.get({
  url:"../survey/getAllSurveys?empID="+empID,
   success: function( data ) {
      var   $container = $('#buttonContainer');

                for (var i = 0; i < data.length; i++) {

                $container.append(myvar);
              //  $("#demo").on("click", templateprinter(data[i]));


      }
    },
    async: false
});
