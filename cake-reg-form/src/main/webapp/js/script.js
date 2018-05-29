toggleActive();
promptModalSignup();
closeModal(document.getElementById("closeModal"));
closeModal(document.getElementById("cancelSignupBtn"));

function toggleActive() {
	var bar = document.getElementById("navbar");
	var entries = bar.getElementsByClassName("entry");
	for (var i = 0; i < entries.length; i++) {
		entries[i].addEventListener("click", function() {
		var current = document.getElementsByClassName("active");
		current[0].className = current[0].className.replace(" active", "");
		this.className += " active";
		});
	}
}

function promptModalSignup() {
	document.getElementById("prompt-modal").addEventListener("click", function() {
		var modal = document.getElementById('id01');
		modal.style.display = "block";
	});
}

function closeModal(elem) {
	/*var closeBtn = document.getElementById("closeModal");*/
	elem.addEventListener("click", function() {
		document.getElementById("id01").style.display = "none";
	});
}


