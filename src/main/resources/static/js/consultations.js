function getQueryParam(name) {
    let urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

$(document).ready(function() {

    console.log(getQueryParam("id"));
});