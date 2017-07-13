

function limpiarModal(sizeModal){
	$("#" + sizeModal + "ModalTitulo").html("");
	$("#" + sizeModal + "ModalContenido").html("");
}

$("#btnCloseSmallModal").click(function(){
	limpiarModal("small");
});

function abrirConfiguracionDeUsuarioEnModal(){
	$.get(baseURL + "configuracionUsiaro", function(respuesta) {
		$("#smallModalTitulo").html("Configracion de Usuario");
		$("#smallModalContenido").html(respuesta);
	})
	$("#smallModal").modal('show');
}