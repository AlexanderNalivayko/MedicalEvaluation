jQuery('document').ready(function () {
    jQuery('button').on('click', function () {
        var colVal = jQuery(this).val();

        var text = jQuery('input[id^="'+colVal+'"]').val();


        var colName = jQuery(this).attr('name');
        if (colName == 'delete') {
            jQuery.post("adm", {toDelete: colVal}, function () {
                jQuery('.results').html("поле: " + colVal + " було успішно видалено з бази")
            });
        }else if (colName == 'update'){
            if (text!= '') {
                jQuery.post("adm", {before: colVal}, function () {});
                jQuery.post("adm", {after: text}, function () {
                    jQuery('.results').html("Питання: \"" + colVal + "\" було успішно змінене на: \"" + text + "\"")
                });
            }else {
                jQuery('.results').html("Будь ласка введіть інформацію в поле.");
            }
        }
    });

    return false;
});


