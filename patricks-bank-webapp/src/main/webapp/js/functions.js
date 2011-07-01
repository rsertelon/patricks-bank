function printMonthOptions(min, max){
	returnValue = '';
	for(i = min; i <= max; i++){
		returnValue += '<option value="'+i+'">'+valeurMois[i]+'</option>';
	}
	return returnValue;
}

$(document).ready(function() {
	
	$("#detailOperationsCarte").hide();
	
	$("#annees").change(function(){
		 
		currentDate = new Date();
		currentMonth = currentDate.getMonth();
		currentYear = currentDate.getFullYear();
		
		if($(this).val() == currentYear){
			$("#mois").empty();
			$("#mois").append(printMonthOptions(0, currentMonth));
		}else if($(this).val() == currentYear - 1 || $(this).val() == currentYear - 2){
			$("#mois").empty();
			$("#mois").append(printMonthOptions(0, 11));
		}else if($(this).val() == currentYear - 3){
			$("#mois").empty();
			$("#mois").append(printMonthOptions(currentMonth, 11));
		}
	});
	
	$('#deroulerOperationCarte').click(function() {
		$('#detailOperationsCarte').toggle(500);
	});
		
	$.validator.addMethod("regex", function(value, element, regexp) {
		var check = false;
		var re = new RegExp(regexp);
		if(value.length==0){
			return false;
		}else{
			return this.optional(element) || re.test(value);
		}
	}, "<span class=\"red\">"+errorMontant+"&nbsp;&nbsp;</span>");
	
	
	$("#confirmationVirement").validate({
		rules : {
			montant : {
				regex : "^([1-9]+[0-9]*([.,][0-9]{1,2})?|0[.,][0-9]{1,2})$"
			}
		},
		errorPlacement: function(error, element) {
			error.insertAfter("#montant");
			$('#soldeInsuffisant').hide();
		}
	});

});
