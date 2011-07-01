
function updateListComptesVirement(){
	$.getJSON("jsoncall.html", { idCompte: $("#compteSource").val() }, function(data){
		 $("#compteCible").empty();
		 $.each(data, function(key, val) {
			 $("#compteCible").append("<option value=\""+val.idCompte+"\">"+val.libelle+" / "+val.numero+" / "+val.montant+"&nbsp;&euro;</option>");
		 });
	});
}

$(document).ready(function() {
	
	updateListComptesVirement();

	$("#compteSource").change(function(){
		updateListComptesVirement();
	});
});