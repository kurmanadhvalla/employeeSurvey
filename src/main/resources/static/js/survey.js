
var template = '<li  id ="surveyaspect" class="list-group-item" draggable="false" >'+
'    <div class="panel-heading">'+
'        <div class="col-md-12">'+
'          <h4 >surveyaspect</h4>'+
'          <div class="star-rating">'+
'            <span class="fa fa-star-o image-responsive" data-rating="1"></span>'+
'            <span class="fa fa-star-o image-responsive" data-rating="2"></span>'+
'            <span class="fa fa-star-o image-responsive" data-rating="3"></span>'+
'            <span class="fa fa-star-o image-responsive" data-rating="4"></span>'+
'            <span class="fa fa-star-o image-responsive" data-rating="5"></span>'+
'                  <span class="fa fa-star-o image-responsive" data-rating="6"></span>'+
'                  <span class="fa fa-star-o image-responsive" data-rating="7"></span>'+
'                  <span class="fa fa-star-o image-responsive" data-rating="8"></span>'+
'                  <span class="fa fa-star-o image-responsive" data-rating="9"></span>'+
'                  <span class="fa fa-star-o image-responsive" data-rating="10"></span>'+
'            <input  data-aspectID="" type="hidden" name="wvood" class="rating-value" value="2">'+
'          </div>'+
'        </div>'+
'      <div class="row justify-content-center">'+
'         <div id ="dbid" class="col-8">'+
'             <div class="form-group">'+
'              <label>Justify your rating:</label><br />'+
'                  <div>'+
'                 <textarea class="form-control" rows="2" cols="50"></textarea>'+
'                 </div>'+
'             </div>'+
'         </div>'+
'     </div>'+
'  </div>'+
'  </li>';


var buttonelement = '<button type="button" class="btn btn-secondary btn-lg btn-block" onclick="SetSubmissionDate()">submit your  response</button>';

var empID = "1201";



$(document).ready(function () {

//
//if ($('.container').width() == 540px){
//    $('.star-rating').children().each(function(){
//
//    $("span").css({
//        fontSize: 10px
//    });
//
//    });
//}

});
 function setNewForm(){
          $.get({

            url:"../survey/getSurvey?empID="+empID,
                 success: templateprinter,
                  async: false
          });
        }

function initializeStarRating(){
     var $star_rating = $('.star-rating .fa');
      $star_rating.on('click', function() {

          var $star_Selected = $(this).parent();
         SetRatingStar($star_Selected, $(this).data('rating'));
          var rating = $(this).data('rating');
          var aspectID = $star_Selected.data('aspectID');
         $("#"+aspectID).show();
           var surveyID = $('#surveyID').val();
          $.get("../survey/updateRating?empID="+empID+"&surveyID="+surveyID+"&aspectID="+aspectID+ "&rating="+rating);
      });
       new Sortable(document.getElementsByClassName('sortable')[0]);
}

  function showPreviousSurveys(){
        $.get({
              url:"../survey/getAllSurveys?empID="+empID,
               success: function( data ) {
                debugger;
                  var   $container = $('#buttonContainer');
                  $container.html("");

                            for (var i = 0; i < data.length; i++) {
                                  var a =data[i];
                                var myvar = '<a id ="'+a[0].surveyID+'" href="javascript:void(0)" data-surveys="" class="btn btn-secondary btn-lg btn-block" >click here to view previous response</a>';
                                $container.append(myvar);
                                $container.find('#'+a[0].surveyID).data('surveys', a).click(function(){
                                    templateprinter($(this).data('surveys'));
                                });
                            }
                },
                async: false
            });

          $('#button1').hide();


          $('#button2').hide();
         }

  function templateprinter(Data){
      debugger;
        var $container = $('#SurveyContainer');
             $container.empty();
             console.log('Data', Data);
             $('#surveyID').val(Data[1].surveyID);

                     for (var i = 0; i < Data.length; i++) {


                       var aspectObj = Data[i];
                       var aspectTag = template.replace(/surveyaspect/g, aspectObj.aspectName).replace(/dbid/g, aspectObj.aspectID);
//                       var id  =  aspectTag.replace(/dbid/g, aspectObj.aspectID);
                       $container.append($(aspectTag));
                       $("#"+aspectObj.aspectID).hide();
                       var $starRating = $container.children('#'+aspectObj.aspectName).find('div.star-rating');
                       $starRating.data('aspectID', aspectObj.aspectID);
                       SetRatingStar($starRating, aspectObj.rating);


           }
           $container.append(buttonelement);
           initializeStarRating();
  }


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


function SetSubmissionDate() {
var surveyID = $('#surveyID').val();
    $.get("../survey/setTime?empID="+empID+"&surveyID="+surveyID);
    }



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
