capturarDatosInicialesDelFormulario();

var reglasValidacion = {
				'usuario': {required: true, maxlength: 20, minlength: 5},
				'clave': {required: true, maxlength: 20, minlength: 5}
			};

var mensajesValidacion = {
		'usuario': {
				//required: "Este valor es requerido",
				maxlength: $.validator.format("Please, at least {0} characters are necessary"),
				minlength: $.validator.format("Please, at least {0} characters are necessary")
		},
		'clave': {
				//required: "Este valor es requerido",
				maxlength: $.validator.format("Please, at least {0} characters are necessary"),
				minlength: $.validator.format("Please, at least {0} characters are necessary")
		}
};

aplicarValidacionesFormulario(reglasValidacion, mensajesValidacion);


$("#botonRegistrar").click(function() {

	if(cambiaronLosDatosDelFormularioInicial()){

		if($form.valid()){
			$.ajax({
				type: "POST",
				url: baseURL + "mantenimiento/usuario/guardar",
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
						cargarUrlEnDivContenidoPrincipal("mantenimiento/usuario/ver?id=" + retorno['id'])
					}
				}
			})
		}


	}else{
		mostrarNotificacionNingunCambioFormulario();
	}
});

$("#btnCrearRegistro").click(function() {
	cargarUrlEnDivContenidoPrincipalConValidacionCambiosFormulario("mantenimiento/usuario/ver");
});

$("#botonAtras").click(function() {
	cargarUrlEnDivContenidoPrincipalConValidacionCambiosFormulario("mantenimiento/usuario/listar");
});

$("#btnEliminarRegistro").click(function(){
	if(mensajeDeConfirmacionPersonalizado("Esta seguro que  quiere eliminar este registro?")){
	    eliminarPorUrlYCargarUrlEnDivContenidoPrincipal("mantenimiento/usuario/eliminar?ids="+ [$("#id").val()], 
	    		"mantenimiento/usuario/listar");
	}
});	

$("#btnImprimirRegistro").click(function(){
	console.log($("#id").val());
});	



init_validator();
