// Not allow before todays date
$(document).ready(function(){
    // not allow dates before today
    var today = new Date().toISOString().split('T')[0];
    document.getElementById("pickup").setAttribute('min', today);
});

// not allow dates before pickup
function notPickupDate(){
   var day = document.getElementById("pickup").value;
   document.getElementById("dropoff").setAttribute('min', day);
}

// array of unavalible dates of the vehicle
var dates = [];

// Get Vehicle Reg
var vehicleReg;
$(document).ready(function(){
    $.ajax({
      type: "GET",
      url: "/api/getvehicleSession/",
      success: function(result){
        vehicleReg = result;
        document.getElementById("vehicleHeading").innerHTML = result;

        // Add vehicle info
        $.ajax({
              type: "GET",
              url: "/api/listing/"+vehicleReg,
              success: function(result){
                document.getElementById("host").innerText += result.user.name;
                document.getElementById("type").innerText += result.vehicletype;
                document.getElementById("mileage").innerText += result.mileage;
                document.getElementById("price").innerText += result.price;
                document.getElementById("make").innerText += result.make;
                document.getElementById("model").innerText += result.model;
                document.getElementById("year").innerText += result.year;
                document.getElementById("colour").innerText += result.colour;
                document.getElementById("doors").innerText += result.doors;
                document.getElementById("city").innerText += result.city;
                document.getElementById("country").innerText += result.country;
                document.getElementById("info").innerText += result.info;
               }
            });

        // Add images
        $.ajax({
              type: "GET",
              url: "/api/getVehicleImages/"+vehicleReg,
              success: function(result){
                    $.each(result, function(index) {
                            var div = document.createElement('div');
                            div.className = 'carousel-item';
                            var img = document.createElement('img');
                            img.className = 'd-block w-100';
                            //img.style.marginTop = "-250px";
                            img.src = '/images/'+result[index];
                            if(index==0){div.className = 'carousel-item active';}
                            div.appendChild(img);
                            document.getElementById('vehImages').appendChild(div);
                            });
               }
            });

        // Get vehicle bookings
        $.ajax({
              type: "GET",
              url: "/api/vehicle/"+vehicleReg+"/bookings",
              success: function(result){
                    $.each(result, function(index) {

                            // function that all dates between 2 dates to array
                            function getDatesInRange(startDate, endDate) {
                              var date = new Date(startDate);
                              var end = new Date(endDate);
                              while (date <= end) {
                                dates.push(date.toString());
                                date.setDate(date.getDate() + 1);
                              }
                              return dates;
                            }

                            // add dates from each booking
                            var d1 = result[index].pickup_date;
                            var d2 = result[index].drop_off_date;
                            getDatesInRange(d1, d2);
                            });
               }
            });

       }
    });
});

function bookVehicle() {

        var chosenDates = [];
        var intersection = [];

        // function that all dates between 2 dates to array
        function getDatesInRange(startDate, endDate) {
             var date = new Date(startDate);
             var end = new Date(endDate);
             while (date <= end) {
                   chosenDates.push(date.toString());
                   date.setDate(date.getDate() + 1);
                   }
             // find any dates that clash with exsiting bookings
             intersection = dates.filter(function (obj) {
                return chosenDates.indexOf(obj) !== -1;
             });

            if (intersection != ""){
                // create a warning element
                var div = document.createElement('div');
                div.className = 'alert alert-danger alert-dismissible fade show';
                div.setAttribute("role", "alert");
                div.innerHTML = "This vehicle is booked: " + intersection;

                // button to remove element
                var button = document.createElement('button');
                button.className = 'btn-close';
                button.setAttribute("data-dismiss", "alert");
                button.setAttribute("aria-label", "Close");
                div.appendChild(button);

                document.getElementById('addBookingDates').appendChild(div);
            }
            return chosenDates;
        }

        // dates chosen by the user
        var d1 = document.getElementById("pickup").value;
        var d2 = document.getElementById("dropoff").value;
        getDatesInRange(d1, d2);

        // only confirm if all inputs are provided and no intersections
        if(document.getElementById("pickup").value != ""
        && document.getElementById("dropoff").value != ""
        && document.getElementById("pickuptime").value != ""
        && intersection == ""){
                sessionStorage.setItem('pickup', document.getElementById("pickup").value);
                sessionStorage.setItem('dropoff', document.getElementById("dropoff").value);
                sessionStorage.setItem('pickuptime', document.getElementById("pickuptime").value);
                location.href="/api/booking/details";
        }
}