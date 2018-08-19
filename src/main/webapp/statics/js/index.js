function SetCwinHeight() {
	var iframeid = document.getElementById("maincontent1"); // iframe id
	if (document.getElementById) {
		if (iframeid && !window.opera) {
			if (iframeid.contentDocument
					&& iframeid.contentDocument.body.offsetHeight) {
				iframeid.height = iframeid.contentDocument.body.offsetHeight;
			} else if (iframeid.Document && iframeid.Document.body.scrollHeight) {
				iframeid.height = iframeid.Document.body.scrollHeight;
			}
		}
	}
}
$(function() {

	$(".menu .menu-ul .menu-li>a").on("click",function(){
		alert("0")
	})
	

});