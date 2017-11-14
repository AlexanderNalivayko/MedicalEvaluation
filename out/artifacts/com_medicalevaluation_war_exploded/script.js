var map;
var marker;
function initMap() {
    navigator.geolocation.getCurrentPosition(function (position) {
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;


        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: lat, lng: lng},
            zoom: 8
        });

        marker = new google.maps.Marker({
            position: {lat: lat, lng: lng},
            map: map,
            title: 'ваша позиция'
        });
    })
}

//buttons
jQuery('document').ready(function () {
    jQuery('button').on('click', function () {
        var colVal = jQuery(this).val();
        var afterTitle = jQuery('input[id^="'+colVal+'"]').val();
        var beforeTitle = jQuery('input[id^="'+colVal+'"]').attr('placeholder');
        var btnName = jQuery(this).attr('name');
        var newColumnName = jQuery('#columnName').val();
        var newColumnTitle = jQuery('#columnTitle').val();

        if (btnName == 'delete') {
            jQuery.post("adm", {toDelete: colVal}, function () {
                jQuery('.results').html("Повідомлення: поле: " + colVal + " було успішно видалено з бази");
                setTimeout(function() {window.location.reload();}, 1000);
            });
        }else if (btnName == 'update'){
            if (afterTitle!= '') {
                jQuery.post("adm", {before: beforeTitle, after: afterTitle}, function () {
                    jQuery('.results').html("Повідомлення: Питання: \"" + colVal + "\" було успішно змінене на: \"" + afterTitle + "\"");
                    setTimeout(function() {window.location.reload();}, 1000);
                });
            }else {
                jQuery('.results').html("Повідомлення: Будь ласка введіть інформацію в поле.");
            }
        }else if (btnName == 'addNew'){
            if (newColumnName != '' & newColumnName != 'undefined' & newColumnTitle != '' & newColumnTitle != 'undefined') {
                jQuery.post("adm", {newColName: newColumnName, newColTitle: newColumnTitle}, function () {
                    jQuery('.results').html("Повідомлення: В базу додана нова колонка: \"" + newColumnName + "\", з питанням: \"" + newColumnTitle + "\"");
                    setTimeout(function() {window.location.reload();}, 1000);
                });
            }else {
                jQuery('.results').html("Повідомлення: Одне з полів порожнє, заповніть спочатку поля");
            }
        }
    });

    return false;
});


