// Get User ID
var userid;
$(document).ready(function(){
    $.ajax({
      type: "GET",
      url: "/api/user/email/getid",
      success: function(result){
        userid = result;
        afterUserId();
       }
    });
});

// Add current data
function afterUserId(){
    var url1 = "/api/user/" + userid;
    url = "/api/user/edit/" + userid;
    $.get( url1, function( data ) {

        // Because parseInt doesnt like a leading 0
        var number1 = 0;
        if(data.phone[0] == "0"){number1 = `${0}${parseInt(data.phone)}`;}
        else{number1 = parseInt(data.phone);}

        document.getElementById("title").value= data.title;
        document.getElementById("name").value= data.name;
        document.getElementById("phone").value= number1;
        document.getElementById("city").value= data.city;
        document.getElementById("country").value= data.country;
        document.getElementById("postcode").value= data.postcode;
        document.getElementById("email").value= data.email;
    });
}
// Edit listing
var url;
    function editUserAction() {
    var title = $( "#title" ).val();
    var name = $( "#name" ).val();
    var phone = $( "#phone" ).val();
    var city = $( "#city" ).val();
    var country = $( "#country" ).val();
    var postcode = $( "#postcode" ).val();
    var email = $( "#email" ).val();
    var password = $( "#password" ).val();;
    $.ajax({
        url: url,
        method: "PUT",
        datatype: "json",
        data: {
            title: title,
            name: name,
            phone: phone,
            city: city,
            country: country,
            postcode: postcode,
            email: email,
            password: password
        },
        success: function(response) {
            console.log("Successful put request");
            window.location.href = "/api/user/profile";

        },
        error: function(xhr) {
            console.log("PUT Request failed");
        }
    });
    }