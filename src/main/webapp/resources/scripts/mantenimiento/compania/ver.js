function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#logo').attr('src', e.target.result).width(177).height(177);
                
                $('#logo').attr('title', input.files[0].name)
            };

            reader.readAsDataURL(input.files[0]);
        }
    }

$('#logo').click(function(){
    $("#inputFile").click();
});




capturarFormOriginal();

var reglasValidacion = {
				'razonSocial': {required: true, maxlength: 200},
				'ruc': {required: true, maxlength: 11, minlength: 11},
				'direccion': {required: true, maxlength: 200},
				'telefono': {number: true, maxlength: 15},
				'correoContacto': {email: true}
			};

var mensajesValidacion = {
		'razonSocial': {
				//required: "Este valor es requerido",
				maxlength: $.validator.format("Please, at least {0} characters are necessary")
		},
		'ruc': {
				//required: "Este valor es requerido",
				maxlength: $.validator.format("Please, at least {0} characters are necessary"),
				minlength: $.validator.format("Please, at least {0} characters are necessary")
		},
		'direccion': {
				//required: "Este valor es requerido",
				maxlength: $.validator.format("Please, at least {0} characters are necessary")  
		 },
	  	'telefono': {
	  			//required: "Este valor es requerido",
	  			maxlength: $.validator.format("Please, at least {0} characters are necessary")
	  	}
};

aplicarValidacionesFormulario(reglasValidacion, mensajesValidacion);


$("#botonRegistrar").click(function() {

	if(validarCambiosForm()){

		if($form.valid()){
			$.ajax({
				type: "POST",
				url: baseURL + "mantenimiento/compania/guardar",
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
						cargarContenido("mantenimiento/compania/ver?id=" + retorno['id'])
					}
				}
			})
		}


	}else{
		notificacionNingunCambioFormulario();
	}
});

$("#btnCrearRegistro").click(function() {
	cargarUrlConValidacionCambiosFormulario("mantenimiento/compania/ver");
});

$("#botonAtras").click(function() {
	cargarUrlConValidacionCambiosFormulario("mantenimiento/compania/listar");
});

$("#btnEliminarRegistro").click(function(){
	if(confirmar("Esta seguro que  quiere eliminar este registro?")){
	    eliminarUrlCargarContenidoUrl("mantenimiento/compania/eliminar?ids="+ [$("#id").val()], 
	    		"mantenimiento/compania/listar");
	}
});	

$("#btnImprimirRegistro").click(function(){
	console.log($("#id").val());
});	



init_validator();
