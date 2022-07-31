var userid;
var count = 0;
$.get( "/api/user/email/getid", function( data ) {
        userid = data;

		// Get user data
		var url2 = "/api/user/" + userid;
        $.get( url2, function( data ) {
            var name = data.name;
            var email = data.email;
            var info = data.info;
            var phone = data.phone;
            var city = data.city;
            var country = data.country;
            var postcode = data.postcode;

            // Show user data on HTML
            document.getElementById("idname").innerHTML = name;
            document.getElementById("email").innerHTML = email;
            document.getElementById("phone").innerHTML = phone;
            document.getElementById("city").innerHTML = city;
            document.getElementById("country").innerHTML = country;
            document.getElementById("postcode").innerHTML = postcode;

                // '/user/{userId}/listings'
                var userlistings = "/api/user/" + userid + "/listings";
                $.get( userlistings, function( data ) {
                    var count = 0;
                            $.each(data, function(index) {

                                // Add count
                                count = count + 1;
                                document.getElementById("listing_count").innerText = "Vehicles Listed: (" + count + ")";

                                // Row
                                //var newRow = document.getElementById("deletelistingid");

                                // id
//                                var o1 = document.createElement('option');
//                                o1.innerText = data[index].registration;
//                                newRow.appendChild(o1);
                        });
                    });

                // owner bookings

                var userBookings1 = "/api/owner/" + userid + "/bookings";
                $.get( userBookings1, function( data ) {
                            $.each(data, function(index) {
                                // Add count
                                count += 1;
                        });
                        addBookingCount();
                    });
                // customer bookings
                var userBookings2 = "/api/customer/" + userid + "/bookings";
                $.get( userBookings2, function( data ) {
                            $.each(data, function(index) {
                                // Add count
                                count += 1;
                        });
                        addBookingCount();
                    });
        });

	});

    function editReg(){
         sessionStorage.setItem("editreg",document.getElementById("editVehicleReg").value);
        }

    function addBookingCount() {
        document.getElementById("booking_count").innerText = "Bookings: (" + count + ")";
    }


    // Delete user
    function deleteUserAction() {
        $.ajax({
            type: "DELETE",
            url: "/api/user/" + userid,
            success: function (response) {
                console.log("Successful delete request");
                window.location.href = "/";
            },
            error: function (jqXHR, exception) {
                console.log(exception);
            }
        });
    }

    // Delete booking add to view booking
//    // TODO alert user of ajax request instead of console.log
//    function deleteBookingAction() {
//        $.ajax({
//            type: "DELETE",
//            url: "/api/user/" + userid + "/booking/" + document.getElementById("deletebookingid").value,
//            success: function (response) {
//                console.log("Successful delete request");
//                alert("Successfully deleted vehicle with id: " + document.getElementById("deletebookingid").value);
//                location.reload();
////                document.getElementById('deletelistingid').value=null;
//            },
//            error: function (jqXHR, exception) {
//                alert("Sorry, could not delete vehicle");
//                console.log("Delete request failed");
//                location.reload();
//            }
//        });
//    }