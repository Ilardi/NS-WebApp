// Initialize an empty array to store comments data
window.commentsData = [];

// Fetch comments from the server and display them
fetchComments();

// Function to fetch comments from the server
function fetchComments() {
    fetch('/comments')
        .then(response => response.json())
        .then(data => {
            console.log(data)
            // Store comments as an array of objects
            window.commentsData = data;

            renderComments();
        })
        .catch(error => console.error('Error fetching comments:', error));
}

// Event listener for form submission to add a new comment
document.getElementById('comment-form').addEventListener('submit', function(event) {
    // Prevent default form submission
    event.preventDefault();
    const commentText = document.getElementById('comment-text').value.trim();
    if (commentText !== '') {
        addComment(commentText);
    }
});

// Function to add a new comment
function addComment(commentText) {
    fetch('/comment', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'text=' + encodeURIComponent(commentText),
    })
    .then(response => {
        if (response.ok) {
            // Refresh comments after successful addition
            fetchComments();
            // Clear input field
            document.getElementById('comment-text').value = '';
        } else {
            console.error('Failed to add comment:', response.statusText);
        }
    })
    .catch(error => console.error('Error adding comment:', error));
}

// Function to sort comments based on the selected field and order
function sortComments() {
    const sortField = document.getElementById('sort-field').value;
    const sortOrder = document.getElementById('sort-order').value;

    if (sortField  === 'id') {
        window.commentsData.sort((a, b) => (sortOrder === 'asc') ? a.id - b.id : b.id - a.id);
    } else if (sortField  === 'username') {
        window.commentsData.sort((a, b) => (sortOrder === 'asc') ? a.username.localeCompare(b.username) : b.username.localeCompare(a.username));
    }
    renderComments();
}

// Function to render comments
function renderComments() {
    const commentsList = document.getElementById('comments-list');
    // Clear previous comments
    commentsList.innerHTML = ''; 

    window.commentsData.forEach(comment => {
        const listItem = document.createElement('li');
        
        // textContent usato al posto di innerHTML 
        listItem.textContent = comment.username + ': ' + comment.comment;
        commentsList.appendChild(listItem);
    });
}
	           
function extractParams() {
    const params = new URLSearchParams(window.location.search);
    const sortField = params.get('sortField');

    // Check if sort field is provided
    if (sortField) {
        console.log("Retrieve sortField: ", sortField);

        // Check if the sort field exists in the dropdown options
        const dropdown = document.getElementById('sort-field');
        console.log("Old dropdown value: ", dropdown.value);

        const existingOption = Array.from(dropdown.options).find(option => option.value === sortField);

        if (!existingOption) {
            // If the sort field doesn't exist in the dropdown options, add it dynamically
            var option = document.createElement("option");
    		option.text = sortField;
    		option.value = sortField;
            dropdown.add(option);
        }

        // Set the sort field in the dropdown
        dropdown.value = sortField;
        console.log("New dropmenu value: ", dropdown.value);

        // Call the sort function to apply the sort
        sortComments();
    }
}
					           
					            
// Wait for the DOM content to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    
    // Listener per il pulsante di ordinamento
    document.getElementById("sortButton").addEventListener("click", sortComments);

    // Call the function to extract URL parameters and search for potential XSS payload
    extractParams();
});
