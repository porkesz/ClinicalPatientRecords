$( document ).ready(function() {
	  var url = new URL(window.location.href);
	  var c = url.searchParams.get("error");
	  if ( c !== null ) {
 		 document.getElementById("login-alert").classList.remove("hide-error");
  	  }
});

function emailFilter() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("filter");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("tbody");
	  tr = table.getElementsByTagName("tr");
	
	  for (i = 0; i < tr.length; i++) {
	     td = tr[i].getElementsByTagName("td")[3];
	     if (td) {
	        if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	          tr[i].style.display = "";
	        } else {
	          tr[i].style.display = "none";
	        }
	     } 
	  }
}