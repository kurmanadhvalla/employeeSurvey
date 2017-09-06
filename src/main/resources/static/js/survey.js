var template = '<li id ="surveyaspect" class="list-group-item" draggable="true" style="">'+
'              <div class="panel-heading">'+
'            <div class="row">'+
'              <div class="col-lg-12">'+
'                <h4>surveyaspect</h4>'+
'                <div class="star-rating">'+
'                  <span class="fa fa-star-o" data-rating="1"></span>'+
'                  <span class="fa fa-star-o" data-rating="2"></span>'+
'                  <span class="fa fa-star-o" data-rating="3"></span>'+
'                  <span class="fa fa-star-o" data-rating="4"></span>'+
'                  <span class="fa fa-star-o" data-rating="5"></span>'+
'                  <input data-aspectID="" type="hidden" name="wvood" class="rating-value" value="3">'+
'                </div>'+
'              </div>'+
'            </div>'+
'                </div>'+
'          </li>';


var empID = "1005";

$(document).ready(function () {
  var SetRatingStar = function($star_Selected, rating) {
    $star_Selected.children('input.rating-value').val(rating);
    return $($star_Selected).children().each(function() {
      if (parseInt($star_Selected.children('input.rating-value').val()) >= parseInt($(this).data('rating'))) {
        return $(this).removeClass('fa-star-o').addClass('fa-star');
      } else {
        return $(this).removeClass('fa-star').addClass('fa-star-o');
      }
    });
  };

  $.get({
    url:"../survey/getSurvey?empID="+empID,
		 success: function( Data ) {
          var $container = $('#SurveyContainer');
                  for (var i = 0; i < Data.length; i++) {

                    // console.log(aspectObj);
                    var aspectObj = Data[i];
                    var aspectTag = template.replace(/surveyaspect/g, aspectObj.aspectName);
                    $container.append($(aspectTag));
                    var $starRating = $container.children('#'+aspectObj.aspectName).find('div.star-rating');
                    $starRating.data('aspectID', aspectObj.aspectID);
                     $starRating.data('surveyID', aspectObj.surveyID);
                    SetRatingStar($starRating, aspectObj.rating);
        }
      },
      async: false
  });

      var $star_rating = $('.star-rating .fa');
      $star_rating.on('click', function() {
          var $star_Selected = $(this).parent();
         SetRatingStar($star_Selected, $(this).data('rating'));
          var rating = $(this).data('rating');
          var aspectID = $star_Selected.data('aspectID');
           var surveyID = $star_Selected.data('surveyID');
          $.get("../survey/updateRating?empID="+empID+"&surveyID="+surveyID+"&aspectID="+aspectID+ "&rating="+rating);
      });
});






jQuery(function($) {
    var panelList = $('#SurveyContainer');

    panelList.sortable({
        // Only make the .panel-heading child elements support dragging.
        // Omit this to make then entire <li>...</li> draggable.
        handle: '.panel-heading',
        update: function() {
            $('.panel', panelList).each(function(index, elem) {
                 var $listItem = $(elem),
                     newIndex = $listItem.index();



            });
        }
    });
});
