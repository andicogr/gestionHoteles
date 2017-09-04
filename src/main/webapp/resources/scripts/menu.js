
function abrir(ruta) {
		window.open(ruta,"abrir","scrollbars=yes,status=yes,toolbar=no,menubar=no,location=no,directories=no,resizable=yes,top=60,left=100");
}

$(function(){

	var baseURL = $("#baseURL").val();

	abrirMenuEnDivContenidoPrincipal("companiaMenuListar", "mantenimiento/compania/listar");

	abrirMenuEnDivContenidoPrincipal("usuarioMenuListar", "mantenimiento/usuario/listar");

	abrirMenuEnDivContenidoPrincipal("rolMenuListar", "mantenimiento/rol/listar");

	abrirMenuEnDivContenidoPrincipal("ajustesDeConfiguracionMenu", "ajustesConfiguracion/ver");

});