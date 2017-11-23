$( document ).ready(function() {
	var url = new URL(window.location.href);
	var c = url.searchParams.get("error");
	if ( c !== null ) {
		document.getElementById("login-alert").classList.remove("hide-error");
	}
});