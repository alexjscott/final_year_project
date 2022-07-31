// Delete listing
// TODO alert user of ajax request instead of console.log
function deleteAction() {
    $.ajax({
        type: "DELETE",
        url: "/api/user/" + sessionStorage.getItem("userid") + "/listing/" + document.getElementById("deletelistingid").value,
        success: function (response) {
            console.log("Successful delete request");
            alert("Successfully deleted vehicle with id: " + document.getElementById("deletelistingid").value);
            document.getElementById('deletelistingid').value=null;
        },
        error: function (jqXHR, exception) {
            alert("Could not delete vehicle with id: " + document.getElementById("deletelistingid").value + ", Please try again.");
            console.log("Delete request failed");
            document.getElementById('deletelistingid').value=null;
        }
    });
}