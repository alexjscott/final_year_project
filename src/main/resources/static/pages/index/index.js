// If login failed
$(document).ready(function(){
    $.ajax({
      type: "GET",
      url: "/api/user/loginAttempt",
      success: function(result){
        console.log(result);
        if (result == "loginFailed"){
            var div1 = document.createElement('br');
            var div2 = document.createElement('div');
            div2.className = 'font-weight-bold text-danger';
            div2.innerHTML = "Invalid login credentials!";
            document.getElementById("login").appendChild(div1);
            document.getElementById("login").appendChild(div2);
        }
       }
    });
});


 // Get users details
	function getUserDetails(){
		var url = "/api/user/validate/" + document.getElementById("email").value + "/" + document.getElementById("password").value;
    	document.getElementById('login').action = url;
	};
