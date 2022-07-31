var userid;
var total;
$.get( "/api/user/email/getid", function( data ) {
        userid = data;

		// Get user data
		var url2 = "/api/user/" + userid;
        $.get( url2, function( data ) {
            //var id = data.id;
            var name = data.name;
            var title = data.title;
            var email = data.email;
            var info = data.info;
            var phone = data.phone;
            var city = data.city;
            var country = data.country;
            var postcode = data.postcode;

            // Show user data on HTML
            document.getElementById("id").innerHTML = "******";
            document.getElementById("title").innerHTML = title;
            document.getElementById("idname").innerHTML = name;
            document.getElementById("email").innerHTML = email;
            document.getElementById("phone").innerHTML = phone;
            document.getElementById("city").innerHTML = city;
            document.getElementById("country").innerHTML = country;
            document.getElementById("postcode").innerHTML = postcode;

        });
	});


// Get Vehicle Reg
var vehicleReg;
var days;
$(document).ready(function(){

    $.ajax({
      type: "GET",
      url: "/api/getvehicleSession/",
      success: function(result){
        vehicleReg = result;

        // Get vehicle information
        $.ajax({
              type: "GET",
              url: "/api/listing/"+vehicleReg,
              success: function(result){

                // Add dates and time for booking
                document.getElementById("pickup").innerText = sessionStorage.getItem('pickup');
                document.getElementById("dropoff").innerText = sessionStorage.getItem('dropoff');
                document.getElementById("pickuptime").innerText = sessionStorage.getItem('pickuptime');

                // To set two dates to two variables
                var date1 = new Date(sessionStorage.getItem('pickup'));
                var date2 = new Date(sessionStorage.getItem('dropoff'));

                // To calculate the time difference of two dates
                var Difference_In_Time = date2.getTime() - date1.getTime();
                var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
                days = Difference_In_Days;

                // calculate price and vat
                var pricefordates = Difference_In_Days * result.price;
                var vat = (0.2 * pricefordates);
                total = pricefordates + vat;
                total = total.toFixed(2);

                // Add calculations to page
                document.getElementById("reg").innerText = vehicleReg;
                document.getElementById("vat").innerText += vat;
                document.getElementById("price").innerText += total;
               }
            });

       }
    });
});

function bookVehicle() {
console.log("Started");
        // Get vehicle information
        $.ajax({
              type: "POST",
              datatype: "json",
              data: {
                    pickup_date: sessionStorage.getItem('dropoff'),
                    drop_off_date: sessionStorage.getItem('pickup'),
                    pickup_time: sessionStorage.getItem('pickuptime'),
                    days: days,
                    price: total
              },
              url: "/api/user/"+userid+"/vehicle/"+vehicleReg+"/booking",
              success: function(result){
                        location.href="/api/user/profile";
               }
            });

}