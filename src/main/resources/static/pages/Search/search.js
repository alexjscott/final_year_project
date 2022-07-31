
var selText = "vehicle";
$(function () {
   $(".dropdown-menu li a").click(function () {
       selText = $(this).text();
       $("#selection").text(selText);
   });
});
var url;
function changeSEARCH(){
        url = "/api/vehicle/search?type=" + selText + "&search=" + document.getElementById("searchString").value;
    }
function clearTable(){
        document.getElementById("searchLIST").innerHTML = "";
        // Count rows and add to heading
        var tbodyRowCount = document.getElementById("searchLIST").rows.length;
        document.getElementById('resultsNum').innerText = "Results(" + tbodyRowCount + ")";
    }
// Clicked Vehicle
function getRow(e) {
        // Get clicked vehicle reg
        var vehicleReg = this.firstElementChild.textContent;
        // Set vehicle session id
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "/api/setvehicleSession/"+vehicleReg, true);
        xhttp.send();
        // Show selected vehicle
        var row_url = "/api/show/vehiclelisting"
        location.href=row_url;
    }

function searchDB(){
    var url = "/api/vehicle/search_listings?type=" + selText + "&search=" + document.getElementById("searchString").value;
    $.getJSON(url, function(data) {
                $.each(data, function(index) {

                        // Don't add vehicle if already in table
                        var target = data[index].registration;
                        var hasRegistration = false;

                        var table = document.getElementById("searchTable");
                        for (var i = 0, row; row = table.rows[i]; i++) {
                           if (row.cells[0].innerHTML == target) {
                                hasRegistration = true;
                           }
                        }

                        // Add vehicle row if not already in table
                        if (hasRegistration == false) {

                              // Row
                              var newRow = document.createElement('tr');
                              newRow.addEventListener("click", getRow, false);

                              // registration
                              var d1 = document.createElement('td');
                              d1.innerText = data[index].registration;
                              newRow.appendChild(d1);
                              // type
                              var d2 = document.createElement('td');
                              d2.innerText = data[index].vehicletype;
                              newRow.appendChild(d2);
                              // price
                              var d3 = document.createElement('td');
                              d3.innerText = data[index].price;
                              newRow.appendChild(d3);
                              // make
                              var d4 = document.createElement('td');
                              d4.innerText = data[index].make;
                              newRow.appendChild(d4);
                              // model
                              var d5 = document.createElement('td');
                              d5.innerText = data[index].model;
                              newRow.appendChild(d5);
                              // year
                              var d6 = document.createElement('td');
                              d6.innerText = data[index].year;
                              newRow.appendChild(d6);
                              // City
                              var d7 = document.createElement('td');
                              d7.innerText = data[index].city;
                              newRow.appendChild(d7);
                              // Country
                              var d10 = document.createElement('td');
                              d10.innerText = data[index].country;
                              newRow.appendChild(d10);
                              // doors
                              var d8 = document.createElement('td');
                              d8.innerText = data[index].doors;
                              newRow.appendChild(d8);
                              // colour
                              var d9 = document.createElement('td');
                              d9.innerText = data[index].colour;
                              newRow.appendChild(d9);

                              document.getElementById('searchLIST').appendChild(newRow);

                              // Count rows and add to heading
                              var tbodyRowCount = table.tBodies[0].rows.length;
                              document.getElementById('resultsNum').innerText = "Results(" + tbodyRowCount + ")";
                              }
                    });
                });
}




function hosted(){
    var userid;
    $.get( "/api/user/email/getid", function( data ) {
        userid = data;
        var url = "/api/user/"+userid+"/listings";
        $.getJSON(url, function(data) {
                    $.each(data, function(index) {

                            // Don't add vehicle if already in table
                            var target = data[index].registration;
                            var hasRegistration = false;

                            var table = document.getElementById("searchTable");
                            for (var i = 0, row; row = table.rows[i]; i++) {
                               if (row.cells[0].innerHTML == target) {
                                    hasRegistration = true;
                               }
                            }

                            // Add vehicle row if not already in table
                            if (hasRegistration == false) {

                                  // Row
                                  var newRow = document.createElement('tr');
                                  newRow.addEventListener("click", getRow, false);

                                  // registration
                                  var d1 = document.createElement('td');
                                  d1.innerText = data[index].registration;
                                  newRow.appendChild(d1);
                                  // type
                                  var d2 = document.createElement('td');
                                  d2.innerText = data[index].vehicletype;
                                  newRow.appendChild(d2);
                                  // price
                                  var d3 = document.createElement('td');
                                  d3.innerText = data[index].price;
                                  newRow.appendChild(d3);
                                  // make
                                  var d4 = document.createElement('td');
                                  d4.innerText = data[index].make;
                                  newRow.appendChild(d4);
                                  // model
                                  var d5 = document.createElement('td');
                                  d5.innerText = data[index].model;
                                  newRow.appendChild(d5);
                                  // year
                                  var d6 = document.createElement('td');
                                  d6.innerText = data[index].year;
                                  newRow.appendChild(d6);
                                  // City
                                  var d7 = document.createElement('td');
                                  d7.innerText = data[index].city;
                                  newRow.appendChild(d7);
                                  // Country
                                  var d10 = document.createElement('td');
                                  d10.innerText = data[index].country;
                                  newRow.appendChild(d10);
                                  // doors
                                  var d8 = document.createElement('td');
                                  d8.innerText = data[index].doors;
                                  newRow.appendChild(d8);
                                  // colour
                                  var d9 = document.createElement('td');
                                  d9.innerText = data[index].colour;
                                  newRow.appendChild(d9);

                                  document.getElementById('searchLIST').appendChild(newRow);

                                  // Count rows and add to heading
                                  var tbodyRowCount = table.tBodies[0].rows.length;
                                  document.getElementById('resultsNum').innerText = "Results(" + tbodyRowCount + ")";
                                  }
                        });
                    });
               });
}

function recommend(){

        var url = "/api/vehicle/search_listings/recommend";
        $.getJSON(url, function(data) {
                    $.each(data, function(index) {

                            // Don't add vehicle if already in table
                            var target = data[index].registration;
                            var hasRegistration = false;

                            var table = document.getElementById("searchTable");
                            for (var i = 0, row; row = table.rows[i]; i++) {
                               if (row.cells[0].innerHTML == target) {
                                    hasRegistration = true;
                               }
                            }

                            // Add vehicle row if not already in table
                            if (hasRegistration == false) {

                                  // Row
                                  var newRow = document.createElement('tr');
                                  newRow.addEventListener("click", getRow, false);

                                  // registration
                                  var d1 = document.createElement('td');
                                  d1.innerText = data[index].registration;
                                  newRow.appendChild(d1);
                                  // type
                                  var d2 = document.createElement('td');
                                  d2.innerText = data[index].vehicletype;
                                  newRow.appendChild(d2);
                                  // price
                                  var d3 = document.createElement('td');
                                  d3.innerText = data[index].price;
                                  newRow.appendChild(d3);
                                  // make
                                  var d4 = document.createElement('td');
                                  d4.innerText = data[index].make;
                                  newRow.appendChild(d4);
                                  // model
                                  var d5 = document.createElement('td');
                                  d5.innerText = data[index].model;
                                  newRow.appendChild(d5);
                                  // year
                                  var d6 = document.createElement('td');
                                  d6.innerText = data[index].year;
                                  newRow.appendChild(d6);
                                  // City
                                  var d7 = document.createElement('td');
                                  d7.innerText = data[index].city;
                                  newRow.appendChild(d7);
                                  // Country
                                  var d10 = document.createElement('td');
                                  d10.innerText = data[index].country;
                                  newRow.appendChild(d10);
                                  // doors
                                  var d8 = document.createElement('td');
                                  d8.innerText = data[index].doors;
                                  newRow.appendChild(d8);
                                  // colour
                                  var d9 = document.createElement('td');
                                  d9.innerText = data[index].colour;
                                  newRow.appendChild(d9);

                                  document.getElementById('searchLIST').appendChild(newRow);

                                  // Count rows and add to heading
                                  var tbodyRowCount = table.tBodies[0].rows.length;
                                  document.getElementById('resultsNum').innerText = "Results(" + tbodyRowCount + ")";
                                  }
                        });
                    });
}