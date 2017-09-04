deshabilitarKeyPress();

$(function(){
	cambiarRol();
});

function cambiarRol(){
	idRol = $("#idRol").val();
	companiaDefectoId = $("#companiaPorDefectoId").val();

	$.get(baseURL + "configuracionUsuario/obtenerCompaniasPorRol?idRol=" + idRol, function(listaCompanias){
		var opciones = "";
		for (i = 0; i < listaCompanias.length; i++) { 
		    compania = listaCompanias[i];
		    opciones += "<option value='" + compania.id + "' "; 
		    if(companiaDefectoId == compania.id){
		    	opciones += " selected='selected' ";
		    }
		    opciones += " >" + compania.razonSocial + "</option>";
		}

		$("#idCompania").html(opciones);
	});

}