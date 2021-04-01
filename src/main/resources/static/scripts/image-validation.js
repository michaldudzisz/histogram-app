function ImageValidation() {
    const file = document.getElementById('image-input').files[0];
    const fsize = Math.round(file.size / 1024);
    if (fsize >= 10240) {
        alert("File is too Big. Please select a file less than 10 MB");
        document.getElementById('image-input').value = null;
    }

}

function AuthorValidation() {
    const author = document.getElementById('author-input').value;
    if (author.length > 50) {
        alert("Author value too long! Should not be longer than 50 characters.");
        document.getElementById('author-input').value = null;
    }
}

function DescriptionValidation() {
    const description = document.getElementById('description-input').value;
    if (description.length > 500) {
        alert("Description value too long! Should not be longer than 500 characters.");
        document.getElementById('description-input').value = null;
    }
}

function sendDeleteImage(theUrl) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "DELETE", theUrl, false );
    xmlHttp.send( null );
    window.location.reload(true);
}