

function limpiarModal(sizeModal){
	$("#" + sizeModal + "ModalTitulo").html("");
	$("#" + sizeModal + "ModalContenido").html("");
}

$("#btnCloseSmallModal").click(function(){
	limpiarModal("small");
});

function abrirConfiguracionDeUsuarioEnModal(){
	$.get(baseURL + "configuracionUsuario/ver", function(respuesta) {
		$("#smallModal").html(respuesta);
	});
	$("#smallModal").modal('show');
}