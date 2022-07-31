// Get User ID
var userid;
var url;
$(document).ready(function(){
    $.ajax({
      type: "GET",
      url: "/api/user/email/getid",
      success: function(result){
        userid = result;
        url = "/api/user/" + userid + "/listing";
        document.getElementById('createVehicle').action = url;
       }
    });
});

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
      }
      form.classList.add('was-validated');
    }, false);
  });
})();