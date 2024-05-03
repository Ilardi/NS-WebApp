document.addEventListener("DOMContentLoaded", function() {
    var infoButton = document.getElementById("infoButton");
    var popup;

    if (infoButton) {
        infoButton.addEventListener("mouseover", function() {
        	var popupText = document.getElementById("popupText").textContent;
            popup = document.createElement("div");
            popup.className = "popup";
            popup.innerHTML = popupText;
            popup.style.position = "absolute";

            // Ottieni le coordinate del bottone "Info"
            var buttonRect = infoButton.getBoundingClientRect();

            // Posiziona il popup sotto il bottone "Info"
            popup.style.top = (buttonRect.bottom + window.scrollY) + "px";
            popup.style.left = buttonRect.left + "px";

            document.body.appendChild(popup);
        });

        document.addEventListener("mouseout", function(event) {
            var e = event.toElement || event.relatedTarget;
            if (e && (e.parentNode == infoButton || e == popup)) {
                return;
            }
            if (popup && popup.parentNode) {
                popup.parentNode.removeChild(popup);
            }
        });
    }
});
