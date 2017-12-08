
var template = '<li  id ="surveyaspect"  class="list-group-item" draggable="false" style="cursor:pointer;background-color:whitesmoke">'+
'    <div class="panel-heading">'+
'        <div class="col-md-12">'+
//'          <h4 class= "aspectRanker" id ="rankingof"  > rankings.</h4>'+
'        <h4 class= "aspect" id ="aspectNameis"  data-aspectID ="" ><span class= "aspectRanker" id ="rankingof" style="font-weight:300;color:black" > rankings.</span> surveyaspect</h4>'+
'          <div class="star-rating">'+
'            <span class="fa fa-star-o " data-rating="1"></span>'+
'            <span class="fa fa-star-o " data-rating="2"></span>'+
'            <span class="fa fa-star-o" data-rating="3"></span>'+
'            <span class="fa fa-star-o" data-rating="4"></span>'+
'            <span class="fa fa-star-o " data-rating="5"></span>'+
'                  <span class="fa fa-star-o " data-rating="6"></span>'+
'                  <span class="fa fa-star-o " data-rating="7"></span>'+
'                  <span class="fa fa-star-o " data-rating="8"></span>'+
'                  <span class="fa fa-star-o" data-rating="9"></span>'+
'                  <span class="fa fa-star-o " data-rating="10"></span>'+
'            <input  data-aspectID="" type="hidden" name="wvood" class="rating-value" value="2">'+
'          </div>'+
'        </div>'+
'      <div class="row justify-content-center">'+
'         <div  class="col-8">'+
'             <div class="md-form">'+

'             <div>'+
'        <i class="fa fa-pencil prefix black-text"></i>'+
'                 <textarea data-aspectID =""  id = "reason" class="md-textarea" placeholder="yourcomments" style ="height:50px;"  ></textarea>'+

'             </div>'+
'             </div>'+
'         </div>'+
'     </div>'+
'  </div>'+
'  </li>';


var buttonelement = '<button type="button" class="btn btn-lg btn-block" onclick =" SetSubmissionDate()"  style="background-color:121C25" >submit your  response</button>';
var employeename;
$.ajax({
    url: '../survey/getName',
    success: function(data) {
        employeename = data;
        if (employeename!="Valla Rao")
        $('#username')[0].innerText=employeename;
        else
         $('#username')[0].innerText="Kurmanadh Valla";
    }
});
var empID = getEmployeeId(employeename);


var record = '<div class="panel-group" id="accordionid" role="tablist" aria-multiselectable="true">'+
'                        <div class="panel panel-default">'+
'                          <div class="panel-heading" role="tab" id="headingOne">'+
'                            <h4 class="panel-title">'+
'                              <a class="collapsed" role="button" onclick="showPreviousSurveys(\'empid\',\'EmployeeName\',\'levelid\');"  data-toggle="collapse" data-parent="#accordionid" href="#collapseid" aria-expanded="true" aria-controls="collapseOne">'+
'																																				<span class="accordian-arrow">'+
'																																					<i class="fa fa-minus-square" aria-hidden="true"></i>'+
'																																					<i class="fa fa-plus-square" aria-hidden="true"></i>'+
'																																				</span>'+
'                               EmployeeName'+
'                              </a>'+
'                            </h4>'+
'                          </div>'+
'                          <div id="collapseid" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">'+
'                            <div class="panel-body">'+
'                              <ul class="list-unstyled">'+
'                               <div id = "levelid" ></div>'+
'                              <li id ="listid"> </li>'+
'                              </ul>'+
'                            </div>'+
'                          </div>'+
'                        </div>'+
'                      </div>';


var index =-1;
function  reportPrinter(empName)
{
 $('#currentform').hide();
 index =-1;
  var $container = $('#realContainer');
    $container.html("");
  var  employee = record.replace(/EmployeeName/g,empName).replace(/accordionid/g, "accordion-"+index).replace(/collapseid/g, "collapse-"+index).replace(/listid/g, "list-"+index).replace(/levelid/g, "level-"+index).replace(/empid/g, getEmployeeId(empName));

   $container.append($(employee));

   heirarchyprinter(empName,-1);

}

function  getEmployeeId(employee)
{

var ID=null;
 ID =     $.get({

                url:"../survey/empID?searchfield=CN"+"&searchvalue="+employee,

                      async: false
              }).responseText;
 return  ID;
}


function  heirarchyprinter(empName,placeholder)
{
           index++;
           var data = null;
            data = $.ajax({
                           url:"../survey/directReports?searchfield=CN"+"&searchvalue="+empName,
                                      dataType: "json",
                                        async: false
                       }).responseJSON;
             if (data == null || data == undefined)
                  {return;}
         else
         {
           for (var i=0;i<data.length;i++)
           {
             var id =  getEmployeeId(data[i]);
             var emp  = record.replace(/EmployeeName/g,data[i]).replace(/accordionid/g, "accordion-"+index).replace(/collapseid/g, "collapse-"+index).replace(/listid/g, "list-"+index).replace(/levelid/g, "level-"+index).replace(/empid/g,id );
                $('#list-'+placeholder).append($(emp));
                heirarchyprinter(data[i],index);
           }
         }
}




//$(document).ready(function(){
////  reportPrinter("Prasad Kunte");
//  setNewForm();
//});
 function setNewForm(){
//      $('.collapse').collapse('hide');
          $.get({

            url:"../survey/getSurvey?empID="+empID,
                 success: templateprinter,
                  async: false
          });
        }

var a = document.getElementById('linkid'); //or grab it by tagname etc
a.href = "../survey/getDataofindividualmanager?cn="+employeename;
$('#reportbutton').hide();
var b = document.getElementById('reportlink'); //or grab it by tagname etc
b.href = "../survey/getData";
if (employeename == "Prashant Ks")
$('#reportbutton').show();

function initializeStarRating(){
     var $star_rating = $('.star-rating .fa');
      $($star_rating).addClass('myClass');
      $star_rating.on('click', function() {

          var $star_Selected = $(this).parent();

         SetRatingStar($star_Selected, $(this).data('rating'));
          var rating = $(this).data('rating');
          var aspectID = $star_Selected.data('aspectID');

           var surveyID = $('#surveyID').val();
           var reason  = $("#reason"+aspectID).val();
          $.get("../survey/updateRating?empID="+empID+"&surveyID="+surveyID+"&aspectID="+aspectID+ "&rating="+rating);
      });
//       new Sortable(document.getElementsByClassName('sortable')[0]);
}

function initializeReason(){


    var $reasons = $('.justify-content-center textarea.md-textarea');

          $reasons.focusout(function() {

              var $reason_selected = $(this);
              var aspectID = $reason_selected.data('aspectID');
               var surveyID = $('#surveyID').val();

               var reason  = $reason_selected.val();
//                $("#reason"+aspectID).hide();
              $.get("../survey/updateReason?empID="+empID+"&surveyID="+surveyID+"&aspectID="+aspectID+ "&reason="+reason);
          });

}

// function showText(el){
//
//     $("#reason"+el.id).show();
//
//}

  function showPreviousSurveys(empid,empName, buttonContainer){
        var empID = empid;

        $.get({
              url:"../survey/getAllSurveys?empID="+empID,
               success: function( data ) {


                  var   $container = $('#'+buttonContainer);
                  $container.html("");
                            if (data==null || data == undefined )
                            {
                              var labels = '<label>no previous surveys </label>';
                              $container.append(labels);
                              }
                           else
                           {
                            for (var i = 0; i < data.length; i++) {
                                                             var a =data[i];
                                              var myvar = '<a id ="'+a[0].surveyID+'" href="javascript:void(0)" data-surveys="" class="btn  btn-lg btn-block previousbutton"style="background-color:121C25;color:white" > buttonName</a>';

                                                           var button_element = myvar.replace(/buttonName/g, "view  Survey of "+empName+" submitted on " +data[i][1].submissionDate);
                                                           $('#currentform').show();
                                                           $('#SurveyContainer').empty();
                                                           $('#Instructions').hide();
                                                           $container.append(button_element);
                                                           $container.find('#'+a[0].surveyID).data('surveys', a).click(function(){
                                                               templateprinter($(this).data('surveys'),false);
                                                           });

                                                       }
                           }
                },
                async: false
            });
         }

  function templateprinter(Data,status){

        var $container = $('#SurveyContainer');
             $container.empty();
             console.log('Data', Data);
             $('#surveyID').val(Data[1].surveyID);

              for (var i = 0; i < Data.length; i++) {
                       var aspectObj = Data[i];
                       var aspectTag = template.replace(/rankingof/g, "rankingof"+aspectObj.aspectID).replace(/rankings./g, aspectObj.ranking+".").replace(/aspectNameis/g,"aspectNameis"+ aspectObj.aspectName).replace(/surveyaspect/g, aspectObj.aspectName).replace(/dbid/g, "dbid"+aspectObj.aspectID).replace(/reason/g,"reason" + aspectObj.aspectID).replace(/yourcomments/g, aspectObj.aspectDescription);

                       $container.append($(aspectTag));
//                       $("#reason"+aspectObj.aspectID).hide();
//                     var $button = $("#button_"+aspectObj.aspectID);
                        var $reason = $("#reason"+aspectObj.aspectID);
                        var $aspect = $("#aspectNameis"+aspectObj.aspectName);
                       $reason.val(aspectObj.ratingreason);
//                       $button.data('aspectID', aspectObj.aspectID);
                       $aspect.data('aspectID', aspectObj.aspectID);
                       $reason.data('aspectID', aspectObj.aspectID);
                       var $starRating = $container.children('#'+aspectObj.aspectName).find('div.star-rating');
                       $starRating.data('aspectID', aspectObj.aspectID);
                       SetRatingStar($starRating, aspectObj.rating);


                 }


         if (status)
         {initializeStarRating();
           initializeReason();
             initializeSortable();
          $container.append(buttonelement);
              }

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
  setNewForm();
    }



function initializeSortable() {

new Sortable(document.getElementById('SurveyContainer'),
                                    {
                                            disabled: false,

                                            onEnd: function (/**Event*/evt) {

                                            		 // element's new index within new parent
                                            		 var Data = $('.aspect');
                                            		 var surveyID = $('#surveyID').val();
                                            		 for(var i=0;i<Data.length;i++)
                                            		 {
                                            		   var aspectid= $('#'+Data[i].id).data('aspectID');
                                            		   var ranking =i+1;
                                                       $('#rankingof'+aspectid)[0].innerHTML =ranking+'.';
                                            		   $.get("../survey/updateRanking?empID="+empID+"&surveyID="+surveyID+"&aspectID="+aspectid+ "&ranking="+ranking);
                                            		 }

//
                                            	}

                                     });
//    var panelList = $('#SurveyContainer');
//    new Sortable(panelList);
//     panelList.sortable({
//         disabled: false,
//     });
}

