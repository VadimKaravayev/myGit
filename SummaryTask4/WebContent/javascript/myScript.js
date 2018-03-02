function goToNewRequestPage() {
	window.location.href = 'Controller?command=NEW_REQUEST_CLICKED';
	return false;
}
function sortOutVehicles(requestId, routeId) {
	var param1 = "requestId";
	var param2 = "routeId";
	if (routeId == null) {
		window.location.href = 'Controller?command=SORT_OUT_VEHICLES&'+param1 + '=' + requestId + '&' + param2 + '=-1';
	} else {
		window.location.href = 'Controller?command=SORT_OUT_VEHICLES&'+param1 + '=' + requestId + '&' + param2 + '=' + routeId;
	}
	
	return false;
}

function goBack() {
	window.history.back();
}

function goToCompleteRoutePage() {
	window.location.href = 'Controller?command=COMPLETE_ROUTE';
	return false;
}

function currentDate() {
	Date.prototype.toDateInputValue = (function() {
		var local = new Date(this);
		local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
		return local.toJSON().slice(0, 10);
	});

	document.getElementById('datePicker').value = new Date()
			.toDateInputValue();
}

function disableButton() {
	
	document.getElementById("updateBtn").disabled = true;
	
	
}

function disableLink() {
	document.getElementById("completeLink").setAttribute("onclick","return false");
}

function checkPasswordMatch() {
	var param1 = document.getElementById("pass").value;
	var param2 = document.getElementById("pass2").value;
	var message = document.getElementById("msg");
	var button = document.getElementById("sub");
	if (param1 != param2) {
		message.setAttribute("style", "color:red");
		message.innerHTML = "No match!";
		button.disabled = true;
	} else {
		message.setAttribute("style", "color:LimeGreen");
		message.innerHTML = "Match!";
		button.disabled = false;
	}
}

function setActive() {
	var obj = document.getElementById('topnav').getElementsByTagName('a');
	for (var i = 0; i < obj.length; i++) {
		if (document.location.href.indexOf(obj[i].href) >= 0) {
			obj[i].className = 'active';
		}
	}
}

function doFilter() {
	var dropdown = document.getElementsByClassName("dropdown-btn");
	var i;

	for (i = 0; i < dropdown.length; i++) {
		dropdown[i].addEventListener("click", function() {
			this.classList.toggle("active");
			var dropdownContent = this.nextElementSibling;
			if (dropdownContent.style.display === "block") {
				dropdownContent.style.display = "none";
			} else {
				dropdownContent.style.display = "block";
			}
		});
	}
}
