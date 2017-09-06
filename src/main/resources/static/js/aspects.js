
var template = '<div id="aspectName" class="multiple_elements">'+
						'<div class="btn-group">'+
							'<button type="button" class="btn btn-primary elo"'+
								'data-toggle="popover" data-placement="right"'+
								'data-original-title="Description"'+
								'data-content="descriptionContent">aspectName</button>'+
						'</div>'+
					'</div> ';









$(document).ready(function () {
  $.get( "../survey/allAspects",
		 function( data ) {
	

var $container = $('#aspectContainer');
        for (var i = 0; i < data.length; i++) {
          console.log(aspectObj);
          var aspectObj = data[i];
          var aspectTag = template.replace(/aspectName/g, aspectObj.aspect).replace(/descriptionContent/g, aspectObj.description);
          $container.append($(aspectTag));
        }
		}
  );

});
