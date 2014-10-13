function myFunction() {
	var carname = "Volvo";
	document.getElementById("demo").innerHTML = carname;
}
function addRow() {
	var addElement = document.createElement("input");
	addElement.setAttribute("type", "file");
	addElement.setAttribute("name", "myfiles");
	var element=document.getElementById("pos");
	element.appendChild(addElement);
}

function mOver(obj) {
	obj.innerHTML = "谢谢"
}

function mOut(obj) {
	obj.innerHTML = "把鼠标移到上面"
}

