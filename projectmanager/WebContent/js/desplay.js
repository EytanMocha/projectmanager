
var xml= new XMLHttpRequest();

function clinte(){
	var homepage= document.getElementById("homepage")
					.setAttribute('style','display:none');
					
	var table= document.getElementById("customers")
					.setAttribute('style','display:block');
}

function project(){
	var homepage= document.getElementById("homepage")
					.setAttribute('style','display:none');
					
	var projects= document.getElementById("projects")
				.setAttribute('style','display:block');
	
	xml.addEventListener('load', getProject);
	xml.open('GET', '/projectmanager/rest/project/getAllProjects');
	xml.send();									
}
function getProject(){
	var response = xml.responseText;
	var json =JSON.parse(respons);
	document.body.appendChild(json);
}

function report(){
	var homepage= document.getElementById("homepage")
					.setAttribute('style','display:none');
					
	var table= document.getElementById("hourreport")
				.setAttribute('style','display:block');
}
			