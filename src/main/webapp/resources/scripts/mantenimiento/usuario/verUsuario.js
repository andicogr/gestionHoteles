$('#fechaExpiracionUsuario').daterangepicker({
  singleDatePicker: true,
  singleClasses: "picker_2",
  locale: {
		"format": 'DD/MM/YYYY',
		"daysOfWeek": ["Do","Lu","Ma","Mi","Ju","Vi","Sa"],
        "monthNames": [
           "Enero",
           "Febrero",
           "Marzo",
           "Abril",
           "Mayo",
           "Junio",
           "Julio",
           "Agosto",
           "Septiembre",
           "Octubre",
           "Noviembre",
           "Diciembre"
       ],
	  }
}, function(start, end, label) {
  console.log(start.toISOString(), end.toISOString(), label);
});

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

aplicarReglasDeValidacionFormulario(reglasValidacion, mensajesValidacion);


$("#botonRegistrar").click(function() {

	if(seRealizaronCambiosEnElFormulario()){

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
						cargarDivContenidoPrincipal("mantenimiento/usuario/ver?id=" + retorno['id'])
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

function btnEliminarRegistro(){
	if(mensajeDeConfirmacion("Esta seguro que  quiere eliminar este registro?")){
	    eliminarRegistros("mantenimiento/usuario/eliminar?ids="+ [$("#id").val()], 
	    		"mantenimiento/usuario/listar");
	}
}

$("#btnImprimirRegistro").click(function(){
	console.log($("#id").val());
});	

$('#divFechaExpiracionUsuario').hide();

$('#expirarUsuario').change(function () {
	if ($(this).is(':checked')) {
		$('#divFechaExpiracionUsuario').show();
	}else{
		$('#divFechaExpiracionUsuario').hide();
	}
 });


init_validator();
