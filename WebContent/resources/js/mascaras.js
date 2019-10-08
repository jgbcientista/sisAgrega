$(document).ready(function() {
	$("#telefone").mask("(99) 9999-9999");
	$("#cpf").mask("999.999.999-99");
	$("input.classCpf").mask("999.999.999-99");
	$("input.classCnpj").mask("99.999.999/9999-99");
	$("#cnpj").mask("99.999.999/9999-99");
	$("#cep").mask("99999-999");
	$("#data").mask("99/99/9999");
	$(".cep").mask("99999-999");
	$(".numero").mask("99999-999");
	
	var textoCpfCnpjOptions = {
		onKeyPress : function(valor) {
			var masks = [ '', '999.999.999-99', '99.999.999/9999-99' ];
			mask = '';

			if (!isNaN(valor)) {

				alert('N');
				mask = (valor.length > 11) ? masks[2] : masks[1];
			} else {

				alert('T');
				mask = masks[0];
			}
		}
	};
	$(".textoCpfCnpj").mask('', textoCpfCnpjOptions);
});

$(document).ready(function() {
	$('#valor-deposito').priceFormat({
		prefix : '',
		centsSeparator : ',',
		thousandsSeparator : '.',
		limit : 10
	});
});

function formataCpf(campo, teclapres) {
	var tecla = teclapres.keyCode;
	var vr = new String(campo.value);
	vr = vr.replace(".", "");
	vr = vr.replace("/", "");
	vr = vr.replace("-", "");
	tam = vr.length + 1;

	if (isNaN(vr)) {
		campo.value = "";
	}

	if (tecla != 14) {
		if (tam == 4)
			campo.value = vr.substr(0, 3) + '.';
		if (tam == 7)
			campo.value = vr.substr(0, 3) + '.' + vr.substr(3, 6) + '.';
		if (tam == 11)
			campo.value = vr.substr(0, 3) + '.' + vr.substr(3, 3) + '.'
					+ vr.substr(7, 3) + '-' + vr.substr(11, 2);
	}
}

function formataCnpj(campo, teclapres) {
	var tecla = teclapres.keyCode;
	var vr = new String(campo.value);
	vr = vr.replace(".", "");
	vr = vr.replace("/", "");
	vr = vr.replace("-", "");
	tam = vr.length + 1;

	if (tecla != 14) {
		if (tam == 3)
			campo.value = vr.substr(0, 2) + '.';
		if (tam == 6)
			campo.value = vr.substr(0, 2) + '.' + vr.substr(2, 5) + '.';
		if (tam == 10)
			campo.value = vr.substr(0, 2) + '.' + vr.substr(2, 3) + '.'
					+ vr.substr(6, 3) + '/';
		if (tam == 15)
			campo.value = vr.substr(0, 2) + '.' + vr.substr(2, 3) + '.'
					+ vr.substr(6, 3) + '/' + vr.substr(9, 4) + '-'
					+ vr.substr(13, 2);
	}
}

function informe() {
	alert('TESTE');
}

function formataTelefone(objeto) {

	if (objeto.value.length == 0)
		objeto.value = '(' + objeto.value;

	if (objeto.value.length == 3)
		objeto.value = objeto.value + ') ';

	if (objeto.value.length == 9)
		objeto.value = objeto.value + '-';
}

function setMask_cpf_cnpj_Dinamico(form, camp) {

	var campo = document.getElementById(form + ':' + camp);

	var valor = jQuery(campo).val();
	valor = valor.replace("/\D/g", ""); // Remove tudo o que não é dígito

	if (valor.length == 11) {
		jQuery(campo).mask('999.999.999-99');
	} else if (valor.length == 14) {
		jQuery(campo).mask('99.999.999/9999-99');

	}
}


function mascaraCelularFixo(campo) {
	if (campo.value) {
		var numero = campo.value.replace(/\D/g, '');
		element = $(campo);  
	    element.unmask();  
	    if (numero.length > 8) {  
	        element.mask("99999-999?9");  
	    } else {  
	        element.mask("9999-9999?9");  
	    }
	}
}

jQuery("input.mascaraCelularFixo").mask("9999-9999?9")
	.focusout(function (event) {  
	    var target, phone, element;  
	    target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
	    phone = target.value.replace(/\D/g, '');
	    element = $(target);  
	    element.unmask();  
	    if (phone.length > 8) {  
	        element.mask("99999-999?9");  
	    } else {  
	        element.mask("9999-9999?9");  
	    }
});

jQuery("input.mascaraCelularFixo").on('mask-it', function(event) {
	var target, phone, element;
    target = (event.currentTarget) ? event.currentTarget : event.srcElement;
    phone = target.value.replace(/\D/g, '');
    element = $(target);
    if (phone.length > 8) {  
        element.mask("99999-999?9");  
    } else {  
        element.mask("9999-9999?9");  
    }
}).trigger('mask-it');