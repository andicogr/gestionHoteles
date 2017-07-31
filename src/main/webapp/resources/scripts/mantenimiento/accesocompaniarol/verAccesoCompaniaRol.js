
capturarDatosInicialesDelFormulario();

var reglasValidacion = {
				'compania.id': {required: true},
			};

var mensajesValidacion = {
		'compania.id': {
				//required: "Este valor es requerido",
		}
};

aplicarReglasDeValidacionFormulario(reglasValidacion, mensajesValidacion);


$("#botonRegistrar").click(function() {
	enviarFormulario("mantenimiento/usuariorol/guardar")
});

$("#botonActualizar").click(function() {
	enviarFormulario("mantenimiento/usuariorol/actualizar")
});

function enviarFormulario(url){
	if(seRealizaronCambiosEnElFormulario()){

		if($form.valid()){
			$.ajax({
				type: "POST",
				url: baseURL + url,
				data: new FormData($form[0]),
				async: false,
				cache: false,
				contentType: false,
				processData: false,
				success: function(retorno){

					if(retorno['notificacion'] != null){
						new PNotify(retorno['notificacion']);
					}
						
					if(eval(retorno['estado']) == true){
						cargarDivContenidoPrincipal("mantenimiento/rol/ver?id=" + retorno['idRol'])
					}
				}
			})
		}

	}else{
		mostrarNotificacionNingunCambioFormulario();
	}
}

$("#botonAtras").click(function() {
	cargarUrlEnDivContenidoPrincipalConValidacionCambiosFormulario("mantenimiento/rol/ver?id=" + $("#idRol").val());
});

function btnEliminarRegistro(){
	if(mensajeDeConfirmacion("Esta seguro que quiere eliminar este registro?")){
	    eliminarRegistros("mantenimiento/accesocompaniarol/eliminar?ids="+ [$("#id").val()], 
	    		"mantenimiento/rol/ver?id=" + $("#idRol").val());
	}
}
