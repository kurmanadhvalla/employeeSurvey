
var $star_rating = $('.star-rating .fa');

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

$star_rating.on('click', function() {
  var $star_Selected = $(this).parent();

  return SetRatingStar($star_Selected, $(this).data('rating'));
});

// SetRatingStar();
$(document).ready(function() {

});


jQuery(function($) {
    var panelList = $('#draggablePanelList');

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

$(document).ready(function () {
  //can also be wrapped with:
  //1. $(function () {...});
  //2. $(window).load(function () {...});
  //3. Or your own custom named function block.
  //It's better to wrap it.

  //Tooltip, activated by hover event
  $("body").tooltip({
    selector: "[data-toggle='tooltip']",
    container: "body"
  })
    //Popover, activated by clicking
    .popover({
    selector: "[data-toggle='popover']",
    container: "body",
    html: true
  });
  //They can be chained like the example above (when using the same selector).

});
