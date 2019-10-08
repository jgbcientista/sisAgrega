function confirmDelete(msgConfirmaExclusao, entidade) {
	return confirm(msgConfirmaExclusao + " " + entidade + " ?");

}

var countSubmit = false;
function carregando(botao) {
	if (countSubmit) {
		jQuery(botao).click(function() {
			return false;
		});
	} else {
		countSubmit = true;
	}
}

jQuery(function() {
	jQuery('input').keypress(function(e) {
		var code = null;
		code = (e.keyCode ? e.keyCode : e.which);
		return (code == 13) ? false : true;
	});
});

function limitTextArea(element, limit) {

	if (element.value.length > limit) {
		element.value = element.value.substring(0, limit);
	}

}

function abrirJanela(link) {
	window.open(link, '');
}

function clearErrorMessages(pnlMsgErro) {
	var errorSpan = document.getElementById(pnlMsgErro);
	errorSpan.style.display = "none";
	errorSpan.innerHTML = "";

	focusSetted = false;
}

function validaPopupFuncionalidade(form, msg1, cboSistema, pnlMsgErro) {
	clearErrorMessages(form + ":" + pnlMsgErro);
	return validateRequiredField(form + ":" + cboSistema, msg1, form + ":"
			+ pnlMsgErro);
}

function validateRequiredField(fieldId, errorMsg, pnlMsgErro) {

	var field = document.getElementById(fieldId);

	scroll(0, 0);

	if (field && trim(field.value) == "") {
		showErrorMessage(errorMsg, pnlMsgErro);
		setFocusField(field);
		return false;
	}
	return true;
}

function verifyRequiredField(fieldId) {

	var field = document.getElementById(fieldId);

	scroll(0, 0);

	if (field && trim(field.value) == "") {
		return false;
	}
	return true;
}

function validateSumFields(fieldId1, fieldId2, fieldId3, errorMsg, pnlMsgErro) {

	if (!(document.getElementById(fieldId1) && trim(document
			.getElementById(fieldId1).value) == "")
			|| !(document.getElementById(fieldId2) && trim(document
					.getElementById(fieldId2).value) == "")) {

		var field1 = new Number(document.getElementById(fieldId1).value
				.replace(/\./g, "").replace(",", "."));
		var field2 = new Number(document.getElementById(fieldId2).value
				.replace(/\./g, "").replace(",", "."));
		var field3 = new Number(document.getElementById(fieldId3).value
				.replace(/\./g, "").replace(",", "."));

		if ((field1 + field2) != field3) {
			showErrorMessage(errorMsg, pnlMsgErro);
			setFocusField(document.getElementById(fieldId3));
			return false;
		}

	}
	return true;
}

function trim(str) {
	if (str != null) {
		return str.replace(/^\s+|\s+$/, "");
	}
	return "";
}

function showErrorMessage(errorMsg, errorSpanId) {
	var errorSpan = document.getElementById(errorSpanId);

	errorSpan.innerHTML += errorMsg;

	errorSpan.style.display = "inline";
}

function validaFormPerfil(form, msg1) {
	clearErrorMessages(form + ":pnlMsgErroNome");
	clearErrorMessages(form + ":pnlMsgErroDescricao");
	clearErrorMessages(form + ":pnlMsgErroSistema");

	var retornoNome = validateRequiredField(form + ":nome", msg1, form
			+ ":pnlMsgErroNome");
	var retornoDescricao = validateRequiredField(form + ":descricao", msg1,
			form + ":pnlMsgErroDescricao");
	var retornoSistema = validateRequiredField(form + ":cboSistema", msg1, form
			+ ":pnlMsgErroSistema");

	return retornoNome && retornoDescricao && retornoSistema;
}

/*
 * Inviabiliza a digita��o de digitos numericos. onkeypress="return
 * noDigits(event)"
 */
function noDigits(evt) {
	var tecla = (evt.which) ? evt.which : event.keyCode;
	if ((tecla > 47 && tecla < 58)) {
		evt.returnValue = false;
		return false;
	} else {
		return true;
	}

}

function somente_txt(tecla) {
	var var_tecla = tecla.keyCode ? tecla.keyCode : tecla.which;
	/* Tecla Backspace */
	if (var_tecla == 8) {
		return true;
	}
	/* Tecla Space */
	if (var_tecla == 32) {
		return true;
	}
	/* Teclas a-z e A-Z */
	if ((var_tecla > 64 && var_tecla < 91)
			|| (var_tecla > 96 && var_tecla < 123)) {
		return true;
	}
	/* Teclas acentuadas e cidilha */
	if ((var_tecla > 191 && var_tecla < 221)
			|| (var_tecla > 223 && var_tecla < 253)) {
		return true;
	}
	tecla.returnValue = false;
	return false;
}

function desabilitaEnter(evt) {
	var e = evt || event;
	var key = e.keyCode || e.which;

	/* Tecla Tab */
	if (key == 9 || key != 13) {
		return true;
	}

	return false;
}

function desabilitaEnterCaracterEspeciais(evt) {
	var e = evt || event;
	var key = e.keyCode || e.which;
	var retorno = somente_txt(e);

	/* Tecla Tab */
	if (key == 9) {
		return true;
	}
	if (key != 13 && retorno) {
		return true;
	}

	return false;
}

function validateRadio(form, id, errorMsg, pnlMsgErro) {

	var radioSim = document.getElementById(form + ':' + id + ':0');
	var radioNao = document.getElementById(form + ':' + id + ':1');

	if (!(radioSim.checked || radioNao.checked)) {
		showErrorMessage(errorMsg, form + ":" + pnlMsgErro);
	} else {
		return true;
	}
	return false;
}

function setMask_cpf_cnpj_Dinamico(form, camp) {

	var campo = document.getElementById(form + ':' + camp);

	var valor = jQuery(campo).val();
	valor = valor.replace(/\D/g, ""); // Remove tudo o que n�o � d�gito
	alert(valor);
	if (valor.length < 12) {
		jQuery(campo).mask('999.999.999-99');
	} else {
		jQuery(campo).mask('99.999.999/9999-99');
	}
}

function removeMask(campo) {
	jQuery(campo).unmask();
}

/*
 * Inviabiliza a digita��o de caracteres. onkeypress="return
 * noCaracteres(event)"
 * 
 * update - � possivel press backspace
 */
function noCaracteres(evt) {
	var tecla = (evt.which) ? evt.which : evt.keyCode;
	if ((tecla > 47 && tecla < 58) || (tecla == 8) || (tecla == 9)) {
		return true;

	} else {
		evt.returnValue = false;
		return false;
	}
}

function mascaraCPFCNPJ(o, f) {
	v_obj = o;
	v_fun = f;
	setTimeout('execmascara()', 1);
}

function execmascara() {
	v_obj.value = v_fun(v_obj.value);
}

function cpfCnpj(v) {

	// Remove tudo o que n�o � d�gito
	v = v.replace(/\D/g, "");

	if (v.length < 14) { // <b style="color: black; background-color:
		// rgb(160, 255, 255);">CPF</b>
		// Coloca um ponto entre o terceiro e o quarto d�gitos
		v = v.replace(/(\d{3})(\d)/, "$1.$2");
		// Coloca um ponto entre o terceiro e o quarto d�gitos
		// de novo (para o segundo bloco de n�meros)
		v = v.replace(/(\d{3})(\d)/, "$1.$2");
		// Coloca um h�fen entre o terceiro e o quarto d�gitos
		v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

	} else { // <b style="color: black; background-color: rgb(153, 255,
		// 153);">CNPJ</b>
		// Coloca ponto entre o segundo e o terceiro d�gitos
		v = v.replace(/^(\d{2})(\d)/, "$1.$2");
		// Coloca ponto entre o quinto e o sexto d�gitos
		v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
		// Coloca uma barra entre o oitavo e o nono d�gitos
		v = v.replace(/\.(\d{3})(\d)/, ".$1/$2");
		// Coloca um h�fen depois do bloco de quatro d�gitos
		v = v.replace(/(\d{4})(\d)/, "$1-$2");
	}

	return v;

}

function setFocusField(a) {
	if (!focusSetted) {
		focusSetted = true;
		if (!a.disabled) {
			a.focus();
		}
	}
}

function findPos(obj) {
	var curtop = 0;
	if (obj.offsetParent) {
		do {
			curtop += obj.offsetTop;
		} while (obj = obj.offsetParent);
		return [ curtop ];
	}
}

function validaNumerico(formCampo, msg, formPnlMsg) {

	clearErrorMessages(formPnlMsg);

	var er = new RegExp(/^[0-9]{1,}$/);

	var numero = document.getElementById(formCampo);

	if (numero && trim(numero.value) != "") {
		if (!er.test(numero.value)) {
			showErrorMessage(msg, formPnlMsg);
			setFocusField(numero);
			return false;
		}
	}

	return true;
}

var focusSetted = false;

var prefix = '';
var suffix = '';
var centsSeparator = ',';
var thousandsSeparator = '.';
var limit = 20;
var centsLimit = 2;
var clearPrefix = false;
var clearSufix = false;
var allowNegative = true;
var is_number = /[0-9]/;

// format as price
function price_format(str) {
	// formatting settings
	var formatted = fill_with_zeroes(to_numbers(str));
	var thousandsFormatted = '';
	var thousandsCount = 0;

	// split integer from cents
	var centsVal = formatted.substr(formatted.length - centsLimit, centsLimit);
	var integerVal = formatted.substr(0, formatted.length - centsLimit);

	// apply cents pontuation
	formatted = integerVal + centsSeparator + centsVal;

	// apply thousands pontuation
	if (thousandsSeparator) {
		for ( var j = integerVal.length; j > 0; j--) {
			char_ = integerVal.substr(j - 1, 1);
			thousandsCount++;
			if (thousandsCount % 3 == 0)
				char_ = thousandsSeparator + char_;
			thousandsFormatted = char_ + thousandsFormatted;
		}
		if (thousandsFormatted.substr(0, 1) == thousandsSeparator)
			thousandsFormatted = thousandsFormatted.substring(1,
					thousandsFormatted.length);
		formatted = thousandsFormatted + centsSeparator + centsVal;
	}

	// if the string contains a dash, it is negative - add it to the begining
	// (except for zero)
	if (allowNegative && str.indexOf('-') != -1
			&& (integerVal != 0 || centsVal != 0))
		formatted = '-' + formatted;

	// apply the prefix
	if (prefix)
		formatted = prefix + formatted;

	// apply the suffix
	if (suffix)
		formatted = formatted + suffix;

	return formatted;
}

// format to fill with zeros to complete cents chars
function fill_with_zeroes(str) {
	while (str.length < (centsLimit + 1))
		str = '0' + str;
	return str;
}

// skip everything that isn't a number
// and also skip the left zeroes
function to_numbers(str) {
	var formatted = '';
	for ( var i = 0; i < (str.length); i++) {
		char_ = str.charAt(i);
		if (formatted.length == 0 && char_ == 0)
			char_ = false;

		if (char_ && char_.match(is_number)) {
			if (limit) {
				if (formatted.length < limit)
					formatted = formatted + char_;
			} else {
				formatted = formatted + char_;
			}
		}
	}

	return formatted;
}

// Funcao que valida os campos de data
function validateDateField(a) {
	if (trim(a.value) != "") {
		var b = new RegExp(
				/^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/(19|20)?\d{4}$/);
		if (!b.test(a.value)) {
			a.value = "";
		} else if (a.value.split("/")[1] == "02") {
			if (a.value.split("/")[2] % 4 == 0) {
				if (a.value.split("/")[0] > 29) {
					a.value = "";
				}
			} else {
				if (a.value.split("/")[0] > 28) {
					a.value = "";
				}
			}
		}
	}
}

function definirDataHoje(idCampoData) {

	var d = new Date();
	var TODAY;
	var monthname = new Array("01", "02", "03", "04", "05", "06", "07", "08",
			"09", "10", "11", "12");
	if (d.getDate() < 10) {
		TODAY = "0" + d.getDate() + "/" + monthname[d.getMonth()] + "/"
				+ d.getFullYear();
	} else {
		TODAY = d.getDate() + "/" + monthname[d.getMonth()] + "/"
				+ d.getFullYear();
	}

	var campoData = document.getElementById(idCampoData);
	campoData.value = TODAY;
}

// Método para calcular a diferença entre duas horas
function calcularDiferencaHora(str1, str2) {

	var retorno = 0;

	if (trim(str1.value) != "" && trim(str2.value) != "") {

		var parts1 = str1.split(':');
		var parts2 = str2.split(':');

		var horario1 = parts1[0] * 3600 + parts1[1] * 60;
		var horario2 = parts2[0] * 3600 + parts2[1] * 60;

		retorno = (horario2 - horario1) / (1000 * 60 * 60);
	}
	
	return retorno;
}

// Máscara para campo com letras maiúsculas
function toUpperCase(obj) {
	if (typeof obj != 'undefined') obj.value = obj.value.toUpperCase();
}
