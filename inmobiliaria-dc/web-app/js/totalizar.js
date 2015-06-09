function totalizar(check) {
	var totalInput = $("#total");
	var check = $(check);
	var parent = check.parent().parent();
	var input = parent.find("input[type='number']");
	var total = parseFloat(totalInput.val());
	if (check[0].checked) {
		totalInput.val(total + parseFloat(input.val()));
	} else {
		totalInput.val(total - parseFloat(input.val()));
	}
}