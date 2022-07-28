$ (".btn").on("click",function(ev){
    ev.preventDefault();
    $ ("#petContainer").scrollIntoView();

});

$( document ).ready(function() {
    $("#manipulation").on('change', function(){
        $ (".block").hide();
        $ ("#block-" + $(this).val()).show();
    }).trigger('change');
});