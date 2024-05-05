const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');

document.getElementById("updateAccountForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    var email = document.getElementById("email").value;
    var address = document.getElementById("address").value;
    var description = document.getElementById("description").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/update-account", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.setRequestHeader('X-XSRF-TOKEN', csrfToken);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var responseMessage = document.getElementById("responseMessage");
            if (xhr.status === 200) {
                responseMessage.textContent = xhr.responseText;
            } else {
                responseMessage.textContent = "Si è verificato un errore durante la modifica dell'account.";
            }
        }
    };
    xhr.send("email=" + encodeURIComponent(email) + "&address=" + encodeURIComponent(address)
    + "&description=" + encodeURIComponent(description));
});
