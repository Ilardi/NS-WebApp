
document.getElementById("transferForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    var destinationUsername = document.getElementById("destinationUsername").value;
    var amount = document.getElementById("amount").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/transfer", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var responseMessage = document.getElementById("responseMessage");
            if (xhr.status === 200) {
                responseMessage.textContent = xhr.responseText;
            } else {
                responseMessage.textContent = "Si è verificato un errore durante il trasferimento.";
            }
        }
    };
    xhr.send("destinationUsername=" + encodeURIComponent(destinationUsername) + "&amount=" + encodeURIComponent(amount));
});
