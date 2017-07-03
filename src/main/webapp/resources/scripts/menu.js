
function abrir(ruta) {
		window.open(ruta,"abrir","scrollbars=yes,status=yes,toolbar=no,menubar=no,location=no,directories=no,resizable=yes,top=60,left=100");
	}

$(function(){

	var baseURL;
	baseURL = $("#baseURL").val();

	cargarContenidoMenu("companiaMenuListar", "mantenimiento/compania/listar");

});