// Get User ID
var userid;
var userlistings;
$(document).ready(function(){
    $.ajax({
      type: "GET",
      url: "/api/user/email/getid",
      success: function(result){
        userid = result;
        userlistings = "/api/user/" + userid + "/listings";

        // '/user/{userId}/listings'
        $.get( userlistings, function( data ) {
        var count = 0;
            $.each(data, function(index) {
            // Row
            var newRow = document.getElementById("deletelistingid");
            // id
            var o1 = document.createElement('option');
            o1.innerText = data[index].registration;
            newRow.appendChild(o1);
            });
        });
        next();
       }
    });
});

function next(){
    // Get user vehicles listings
    $.get( userlistings, function( data ) {
                $.each(data, function(index) {
                    // Select element to add options
                    var element = document.getElementById("editlistingid");
                    // Add option to element
                    var option = document.createElement('option');
                    option.innerText = data[index].registration;
                    element.appendChild(option);
            });
            // Call to get initial vehicle info
            changereg();
        });
}
function changereg(){
    // Get url
    var url1 = "/api/listing/" + $('#editlistingid').find(":selected").text();
    // Get vehicle info with get request
    $.get( url1, function( data ) {
        // Because parseInt doesnt like a leading 0
        var number1 = 0;
        if(data.doors[0] == "0"){number1 = `${0}${parseInt(data.doors)}`;}
        else{number1 = parseInt(data.doors);}
        // Because parseInt doesnt like a leading 0
        var number2 = 0;
        if(data.price[0] == "0"){number2 = `${0}${parseInt(data.price)}`;}
        else{number2 = parseInt(data.price);}

        document.getElementById("vehicletype").value= data.vehicletype;
        document.getElementById("doors").value= number1;
        document.getElementById("colour").value= data.colour;
        document.getElementById("make").value= data.make;
        document.getElementById("model").value= data.model;
        document.getElementById("year").value= data.year;
        document.getElementById("price").value= number2;
        document.getElementById("info").value= data.info;
        document.getElementById("city").value= data.city;
        document.getElementById("country").value= data.country;
        document.getElementById("mileage").value= data.mileage;
    });

    // Get listing id to edit
    url = "/api/listing/edit/" + $('#editlistingid').find(":selected").text();
}

    // Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
  'use strict';

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation');

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms).forEach((form) => {
    form.addEventListener('submit', (event) => {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
        editBookingAction();
      }
      form.classList.add('was-validated');
    }, false);
  });
})();

                // Edit listing
                function editBookingAction() {

                var vehicletype = $( "#vehicletype" ).val();
                var doors = $( "#doors" ).val();
                var colour = $( "#colour" ).val();
                var make = $( "#make" ).val();
                var model = $( "#model" ).val();
                var year = $( "#year" ).val();
                var price = $( "#price" ).val();
                var info = $( "#info" ).val();
                var city = $( "#city" ).val();
                var country = $( "#country" ).val();
                var mileage = $( "#mileage" ).val();
                $.ajax({
                    url: url,
                    method: "PUT",
                    datatype: "json",
                    data: {
                        vehicletype: vehicletype,
                        doors: doors,
                        colour: colour,
                        make: make,
                        model: model,
                        year: year,
                        price: price,
                        info: info,
                        city: city,
                        country: country,
                        mileage: mileage
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

    // Delete listing
    function deleteVehicleAction() {
        $.ajax({
            type: "DELETE",
            url: "/api/user/" + userid + "/listing/" + document.getElementById("deletelistingid").value,
            success: function (response) {
                console.log("Successful delete request");
                alert("Successfully deleted vehicle with id: " + document.getElementById("deletelistingid").value);
                location.reload();
//                document.getElementById('deletelistingid').value=null;
            },
            error: function (jqXHR, exception) {
                alert("Sorry, could not delete vehicle");
                console.log("Delete request failed");
                location.reload();
            }
        });
    }

