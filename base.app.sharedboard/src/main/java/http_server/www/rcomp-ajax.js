
// IMPORTANT: notice the next request is scheduled only after the
//            previous request is fully processed either successfully
//	      or not.

function refreshTable() {
	var request = new XMLHttpRequest();
        var vBoard=document.getElementById("table-container");
        request.onload = function() {
            vBoard.innerHTML = this.responseText;
            vBoard.style.color="black";
            setTimeout(refreshTable, 2000);
            };
            
        request.ontimeout = function() {
            vBoard.innerHTML = "Server timeout, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshTable, 100);
        };
        
        request.onerror = function() { 
            vBoard.innerHTML = "No server reply, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshTable, 5000);
        };
        
  	request.open("GET", "/votes", true);
	request.timeout = 5000;
  	request.send();
	}


function voteFor(option) {
	var request = new XMLHttpRequest();
  	request.open("PUT", "/votes/" + option , true);
  	request.send();
        var vBoard=document.getElementById("votes");
        vBoard.innerHTML = vBoard.innerHTML + "<p>Casting your vote ... Please wait.";

	}
	

