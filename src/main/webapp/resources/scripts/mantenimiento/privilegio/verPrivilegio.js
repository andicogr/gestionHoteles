
capturarDatosInicialesDelFormulario();

var reglasValidacion = {
				'nombre': {required: true, maxlength: 50},
			};

var mensajesValidacion = {
		'nombre': {
				//required: "Este valor es requerido",
				maxlength: $.validator.format("Please, at least {0} characters are necessary")
		}
};

aplicarReglasDeValidacionFormulario(reglasValidacion, mensajesValidacion);


$("#botonRegistrar").click(function() {

	if(seRealizaronCambiosEnElFormulario()){

		if($form.valid()){
			$.ajax({
				type: "POST",
				url: baseURL + "mantenimiento/privilegio/guardar",
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
						cargarDivContenidoPrincipal("mantenimiento/privilegio/ver?id=" + retorno['id'])
					}
				}
			})
		}


	}else{
		mostrarNotificacionNingunCambioFormulario();
	}
});

$("#btnCrearRegistro").click(function() {
	cargarUrlEnDivContenidoPrincipalConValidacionCambiosFormulario("mantenimiento/privilegio/ver");
});

$("#botonAtras").click(function() {
	cargarUrlEnDivContenidoPrincipalConValidacionCambiosFormulario("mantenimiento/privilegio/listar");
});	

function btnEliminarRegistro(){
	if(mensajeDeConfirmacion("Esta seguro que  quiere eliminar este registro?")){
	    eliminarRegistros("mantenimiento/privilegio/eliminar?ids="+ [$("#id").val()], 
	    		"mantenimiento/privilegio/listar");
	}
}

$("#btnImprimirRegistro").click(function(){
	console.log($("#id").val());
});	



init_validator();
