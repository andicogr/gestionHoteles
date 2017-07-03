baseURL = $("#baseURL").val();

function cargarContenidoMenu(idMenu, urlContenido){
	$("#" + idMenu).click(function() {
		$("#contenidoPrincipal").html("Cargando . . .");
		$.get(baseURL + urlContenido, function(respuesta) {
			$("#contenidoPrincipal").html(respuesta);

		}).fail(function() {
			$("#contenidoPrincipal").html("No se ha podido visualizar esta pagina");
		});
	});
}
/**
 * Autor: Andres Gonzales - 29-06-2017
 * cargarContenido realiza una peticion al controlador y carga la pagina en el div contenidoPrincipal 
 * @param url - Url que devuelve una pagina.
 */
function cargarContenido(url){

	$("#contenidoPrincipal").html("Cargando . . .");
	$.get(baseURL + url, function(respuesta) {
		$("#contenidoPrincipal").html(respuesta);
	}).fail(function() {
		$("#contenidoPrincipal").html("No se ha podido visualizar esta pagina");
	});
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * alerta muestra mensaje de alerta, en caso de usar un estilo diferente de alert solo se modifica
 * este metodo y el cambio se realizara en todo el proyecto.
 * @param mensaje
 */
function alerta(mensaje){
	alert(mensaje);
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * confirmar muestra mensaje de confirmacion, en caso de usar un estilo diferente de confirm solo se modifica
 * este metodo y el cambio se realizara en todo el proyecto.
 * @param mensaje
 * @returns valor true/false del mensaje de confirmacion
 */
function confirmar(mensaje){
	return confirm(mensaje);
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * capturarFormOriginal obtiene el formulario cada vez que se carga una pagina, para crear o editar registros,
 * para luego compararla con la captura del fomulario en otro momento y verificar si existen cambios o no.
 */
function capturarFormOriginal(){
	$form = $('form');
	origForm = $form.serialize();
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * validarCambiosForm compara el formulario en el momento que se activa el metodo con el formulario capturado en el metodo
 * capturarFormOriginal() y verifica si se realiza alguna cambio.
 * @returns {Boolean}
 */
function validarCambiosForm(){
	return ($form.serialize() !== origForm);
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * capturarValores captura los valores en todos los inputs del form al momento de cargar una pagina, para luego
 * restaurarlos en caso sea necesario.
 */
function capturarValores(){
	$form.find(':input').each(function(i, elem) {
      $(this).data("previous-value", $(this).val());
    });
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * restaurarValores restaura los valores capturados con el metodo capturarValores() en el mismo formulario y
 * luego activa la validacion del formulario.
 */
function restaurarValores() {

	$form.find(':input').each(function(i, elem) {
        $(this).val($(this).data("previous-value"));
    });
	$form.parsley().validate();
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * notificacionNingunCambioFormulario muestra notificacion con mensaje prederterminado
 * Se usa cuando se intenta guardar/actualizar un formulario y no se han registrado cambios 
 */
function notificacionNingunCambioFormulario(){
	new PNotify({
	    title: 'Informacion',
	    text: 'No se realizo ningun cambio en el formulario',
	    type: 'info',
	    styling: 'bootstrap3'
	});
	/*new PNotify({
	    title: 'Regular Success',
	    text: 'That thing that you were trying to do worked!',
	    type: 'success',
	    styling: 'bootstrap3'
	});*/
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * cargarUrlConValidacionCambiosFormulario abre una URL consultado si desea descartar
 * los cambios encontrados en el formulario.
 * @param url
 */
function cargarUrlConValidacionCambiosFormulario(url){
	
	var cargar = true;
	if(validarCambiosForm()){
		if(!confirmar("Desea descartar los cambios?")){
			cargar = false;
		}
	}

	if(cargar){
		cargarContenido(url);
	}
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * eliminarUrlCargarContenidoUrl elimina una registro y luego carga otra pagina, recibe una 
 * notificacion del controlador y un estado de la eliminacion del registro
 * @param urlEliminar - url del registro al eliminar con el parametro ids y su valor en la url < ?ids=[25] >
 * @param urlContenido - url del contenido que se desea cargar
 */
function eliminarUrlCargarContenidoUrl(urlEliminar, urlContenido){
    $.post(baseURL + urlEliminar,function(retorno) {

		if(retorno['notificacion'] != null){
			new PNotify(retorno['notificacion']);
		}

		if(eval(retorno['estado']) == true){
			cargarContenido(urlContenido)
		}

    }).fail(function() {
  		$("#contenidoPrincipal").html("No se ha podido visualizar esta pagina");
  	});
}
/**
 * Autor: Andres Gonzales - 29-06-2017
 * cargarConfiguracionDataTable convierte una tabla en DataTable JS, activa el atributo click de cada fila para abrir
 * un formulario con el contenido de cada fila, y desactiva este atributo para la primera columna, donde estan los
 * checkbox para seleccionar una o mas filas y eliminar o realizar otras acciones en los registros.
 * @param dataTableId - Id de la tabla en la vista
 * @param urlData - Url para obtener los datos Json del controlar y cargarlos en la tabla
 * @param aoColumns - Colummnas de la tabla 
 * @param urlVerRegistro - Url para abrir el conteindo de la fila al hacer clic en ella, con el parametro id < ?id= >
 */
function cargarConfiguracionDataTable(dataTableId, urlData, aoColumns, urlVerRegistro){
	
	$('#' + dataTableId).DataTable({
	    columnDefs: [ {
	        orderable: false,
	        className: 'select-checkbox',
	        targets:   0
	    } ],
	    select: {
	        style:    'os',
	        selector: 'td:first-child'
	    },
	    "order": [[ 1, "asc" ]],
	    "bProcessing": true,
	    "bServerSide": true,
	    "sAjaxSource": baseURL + urlData,
	    "bFilter" : false,
	    "aoColumns" : aoColumns,

	    "fnDrawCallback": function(){
	    	$('#' + dataTableId + ' tr td:not(:first-child)').click(function () {

	    	    var id = $(this).closest('tr').find('td:eq(0) input').val();
	    	    cargarContenido(urlVerRegistro + id)

	    	});
	    	
	    	$( "input[name=checkBoxRow]" ).on( "click", function(){
	    		if($('#' + dataTableId + ' :checked').length > 0){
	    	    	$("#botoneraCentro").show();
	    	    }else{
	    	    	$("#botoneraCentro").hide();
	    	    }
	    	});
	    	
	    	$( "#dataTableLista_length" ).on('change', function() {
	    		$("#checkBoxAll").prop('checked',false);
	    	});
	    },

		"sPaginationType" : "full_numbers",
		"iDisplayLength": 30,
		"language" : {
			"paginate" : {
				"sPrevious" : "<",
				"sNext" : ">",
				"sLast" : ">|",
				"sFirst" : "|<"
			},

			"lengthMenu" : 'Mostrar <select name="dataTableLista_length" id="dataTableLista_length" aria-controls="dataTableLista" class="form-control input-md dataTableLengthMenu">'
				+ '<option value="30">30</option>'
				+ '<option value="80">80</option>'
				+ '<option value="200">200</option>'
				+ '<option value="500">500</option>'
				+ '<option value="-1">Todos</option>'
				+ '</select> registros',

			"info" : "Mostrando _START_ a _END_ (Total: _TOTAL_ resultados)",

			"infoFiltered" : " - filtrados de _MAX_ registros",

			"infoEmpty" : "No hay resultados de busqueda",

			"zeroRecords" : "No hay registros a mostrar",

			"processing" : "Espere, por favor...",

			"search" : "Buscar:"
		}
	});	

}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * seleccionarAllCheckBox selecciona todos los checkbox de las filas del dataTable cada vez que se
 * cambia el estado al checkbox en la cabecera de la tabla
 */
function seleccionarAllCheckBox(){
	if($("#checkBoxAll").is(":checked")){
		$(":checkbox[name='checkBoxRow']").prop('checked', true);
		$("#botoneraCentro").show();
	}else{
		$(":checkbox[name='checkBoxRow']").prop('checked', false);
		$("#botoneraCentro").hide();
	}
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * obtenerCheckBoxDataTableSeleccionados obtiene el ID de los registros de cada fila seleccionada por su respectivo checkbox
 * @returns {Array}
 */
function obtenerCheckBoxDataTableSeleccionados(){
	var selectedIds = [];

    $('input[name=checkBoxRow]:checked').each(function(){
    	selectedIds.push(parseInt(this.value));
    });
    
    return selectedIds;
}

/**
 * Autor: Andres Gonzales - 29-06-2017
 * eliminarRegistroDataTable elimina los registros seleccionados de las filas del dataTable
 * @param dataTableId - Id de la tabla en la vista
 * @param urlEliminar - url del registro al eliminar con el parametro ids esperando un valor en la url < ?ids= >
 * @param urlContenido - url del contenido a abrir luego de eliminar el registro de la tabla
 */
function eliminarRegistroDataTable(dataTableId, urlEliminar, urlContenido){
	var mensaje = "Esta seguro que quiere eliminar este registro?";
	if($('#' + dataTableId + ' :checked').length > 1){
		mensaje = "Esta seguro que quiere eliminar estos registros?";
	}
	if(confirmar(mensaje)){
		var selectedIds = obtenerCheckBoxDataTableSeleccionados();

	    eliminarUrlCargarContenidoUrl(urlEliminar + selectedIds, urlContenido);
	}
}

/**
 * Autor: Andres Gonzales - 02-07-2017
 * aplicarValidacionesFormulario Valida formularios con las reglas indicadas y muestras los mensajes indicados en
 * diccionarios de datos
 * @param reglasValidacion
 * @param mensajesValidacion
 */
function aplicarValidacionesFormulario(reglasValidacion, mensajesValidacion){
	$form.validate({
		onkeyup: function (element) {
	         this.element(element);
		},
		rules : reglasValidacion,
		messages : mensajesValidacion
	});
}
