function table2(){
	
	var table= document.getElementById('table2');
		for (var i = 0; i < 5; i++) {
			var tr = document.createElement('TR');
			table.appendChild(tr);
			var td =document.createElement('TD');
			tr.appendChild(td);
			td.innerHTML= 'try now'	;		
		}
}
