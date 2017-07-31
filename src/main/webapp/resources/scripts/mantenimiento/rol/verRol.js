
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
				url: baseURL + "mantenimiento/rol/guardar",
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
						cargarDivContenidoPrincipal("mantenimiento/rol/ver?id=" + retorno['id'])
					}
				}
			})
		}


	}else{
		mostrarNotificacionNingunCambioFormulario();
	}
});

$("#btnCrearRegistro").click(function() {
	cargarUrlEnDivContenidoPrincipalConValidacionCambiosFormulario("mantenimiento/rol/ver");
});

$("#botonAtras").click(function() {
	cargarUrlEnDivContenidoPrincipalConValidacionCambiosFormulario("mantenimiento/rol/listar");
});	

function btnEliminarRegistro(){
	if(mensajeDeConfirmacion("Esta seguro que  quiere eliminar este registro?")){
	    eliminarRegistros("mantenimiento/rol/eliminar?ids="+ [$("#id").val()], 
	    		"mantenimiento/rol/listar");
	}
}

$("#btnImprimirRegistro").click(function(){
	console.log($("#id").val());
});

function abrirFormularioAgregarPrivilegio(idUsuario){
	cargarDivContenidoPrincipal("mantenimiento/usuariorol/ver?idUsuario=" + idUsuario);
}

function btnEliminaRolPrivilegio(idPrivilegio, idRol){
	if(mensajeDeConfirmacion("Esta seguro que quiere eliminar este rol?")){
	    eliminarRegistros("mantenimiento/usuariorol/eliminar?ids=" + [idUsuarioRol], 
	    		"mantenimiento/usuario/ver?id=" + idUsuario);
	}
}

function btnEliminarRolCompania(idUsuarioRol, idUsuario){
	if(mensajeDeConfirmacion("Esta seguro que quiere eliminar este rol?")){
	    eliminarRegistros("mantenimiento/usuariorol/eliminar?ids=" + [idUsuarioRol], 
	    		"mantenimiento/usuario/ver?id=" + idUsuario);
	}
}


$('#btnActualizarPrivilegios').on('click', function () {
    var checkedIds = tree.getCheckedAndIndeterminateNodes();
	$.get('mantenimiento/rol/actualizarPrivilegios?idRol=' + $("#id").val() + "&idPrvivilegios=" + checkedIds, function(retorno){
		if(retorno['notificacion'] != null){
			new PNotify(retorno['notificacion']);
		}
		if(eval(retorno['estado']) == true){
			cargarDivContenidoPrincipal("mantenimiento/rol/ver?id=" + retorno['id'])
		}
	});

});

function btnEliminarAccesoCompaniaRol(idAccesoCompaniaRol, idRol){
	if(mensajeDeConfirmacion("Esta seguro que quiere eliminar esta compañia?")){
	    eliminarRegistros("mantenimiento/accesocompaniarol/eliminar?ids=" + [idAccesoCompaniaRol], 
	    		"mantenimiento/rol/ver?id=" + idRol);
	}
}

function abrirFormularioAgregarAccesoCompaniaRol(idRol){
	cargarDivContenidoPrincipal("mantenimiento/accesocompaniarol/ver?idRol=" + idRol);
}

$('#tablaListaRolCompania tr td:not(:last-child)').click(function () {

    var idAccesoCompaniaRol = $(this).closest('tr').find('td:eq(0) input').val();
    cargarDivContenidoPrincipal("mantenimiento/accesocompaniarol/ver?id=" + idAccesoCompaniaRol + "&idRol=" + $("#id").val());

});

