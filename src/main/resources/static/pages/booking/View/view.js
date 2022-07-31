var userid;
$(document).ready(function(){
    $.get( "/api/user/email/getid", function( data ) {
        userid = data;

        $.ajax({
          type: "GET",
          url: "/api/customer/"+userid+"/bookings",
          success: function(data){
                    $.each(data, function(index) {
                        // Row
                        var newRow = document.createElement('tr');

                        // bookingref
                        var dextra = document.createElement('td');
                        dextra.innerText = data[index].id;
                        newRow.appendChild(dextra);
                        // registration
                        var d1 = document.createElement('td');
                        d1.innerText = data[index].vehicleListing.registration;
                        newRow.appendChild(d1);
                        // Pickdate
                        var d2 = document.createElement('td');
                        d2.innerText = data[index].pickup_date;
                        newRow.appendChild(d2);
                        // dropoff
                        var d3 = document.createElement('td');
                        d3.innerText = data[index].drop_off_date;
                        newRow.appendChild(d3);
                        // time
                        var d4 = document.createElement('td');
                        d4.innerText = data[index].pickup_time;
                        newRow.appendChild(d4);
                        // days
                        var d5 = document.createElement('td');
                        d5.innerText = data[index].days;
                        newRow.appendChild(d5);
                        // price
                        var d6 = document.createElement('td');
                        d6.innerText = data[index].price;
                        newRow.appendChild(d6);
                        // customer
                        var d7 = document.createElement('td');
                        d7.innerText = data[index].owner.name;
                        newRow.appendChild(d7);
                        // customer email
                        var d8 = document.createElement('td');
                        d8.innerText = data[index].owner.email;
                        newRow.appendChild(d8);

                        // delete button
                        var dbutton = document.createElement('button');
                        dbutton.addEventListener("click", getRow, false);
                        dbutton.className = "btn btn-danger";
                        dbutton.setAttribute("data-toggle", "modal");
                        dbutton.setAttribute("type", "button");
                        dbutton.setAttribute("data-target", "#deleteBooking");
                        dbutton.innerHTML = "Cancel";
                        newRow.appendChild(dbutton);

                        document.getElementById('reservedLIST').appendChild(newRow);

                        // Count rows and add to heading
                        var tbodyRowCount = document.getElementById('reservedTable').tBodies[0].rows.length;
                        document.getElementById('reservedNum').innerText = "Reserved(" + tbodyRowCount + ")";
                    })
                 }
          });

        $.ajax({
          type: "GET",
          url: "/api/owner/"+userid+"/bookings",
          success: function(data){
                    $.each(data, function(index) {
                        // Row
                        var newRow = document.createElement('tr');

                        // bookingref
                        var dextra = document.createElement('td');
                        dextra.innerText = data[index].id;
                        newRow.appendChild(dextra);
                        // registration
                        var d1 = document.createElement('td');
                        d1.innerText = data[index].vehicleListing.registration;
                        newRow.appendChild(d1);
                        // Pickdate
                        var d2 = document.createElement('td');
                        d2.innerText = data[index].pickup_date;
                        newRow.appendChild(d2);
                        // dropoff
                        var d3 = document.createElement('td');
                        d3.innerText = data[index].drop_off_date;
                        newRow.appendChild(d3);
                        // time
                        var d4 = document.createElement('td');
                        d4.innerText = data[index].pickup_time;
                        newRow.appendChild(d4);
                        // days
                        var d5 = document.createElement('td');
                        d5.innerText = data[index].days;
                        newRow.appendChild(d5);
                        // price
                        var d6 = document.createElement('td');
                        d6.innerText = data[index].price;
                        newRow.appendChild(d6);
                        // customer
                        var d7 = document.createElement('td');
                        d7.innerText = data[index].customer.name;
                        newRow.appendChild(d7);
                        // customer email
                        var d8 = document.createElement('td');
                        d8.innerText = data[index].customer.email;
                        newRow.appendChild(d8);

                        // delete button
                        var dbutton = document.createElement('button');
                        dbutton.addEventListener("click", getRow, false);
                        dbutton.className = "btn btn-danger";
                        dbutton.setAttribute("data-toggle", "modal");
                        dbutton.setAttribute("type", "button");
                        dbutton.setAttribute("data-target", "#deleteBooking");
                        dbutton.innerHTML = "Cancel";
                        newRow.appendChild(dbutton);

                        document.getElementById('hostedLIST').appendChild(newRow);

                        // Count rows and add to heading
                        var tbodyRowCount = document.getElementById('hostedTable').tBodies[0].rows.length;
                        document.getElementById('hostedNum').innerText = "Hosted(" + tbodyRowCount + ")";

                        })
                 }
          });
    });
});

var bookingid;
// Clicked Vehicle
function getRow(e) {
        bookingid = this.parentElement.firstElementChild.textContent;
          }

// Delete booking
function deleteBookingAction() {
            $.ajax({
                type: "DELETE",
                url: "/api/user/"+userid+"/booking/"+bookingid,
                success: function (response) {
                    console.log("Successful delete request");
                    window.location.reload();
                },
                error: function (jqXHR, exception) {
                    alert("Can not cancel booking, please contact admin")
                }
            });
}