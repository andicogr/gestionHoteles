
//TODO con el <c:if> poner un flag de multicompania en el JSP y luego optner ese valor
//aca para no pintar la primera columna, tambien pasar el valor si es MultiCompania en el metoido del Servicio
// listaJson para que ya no devuelva esa columna en la estrcutura,

var isMultiCompaniaActivado = eval($("#isMultiCompaniaActivado").val());

var aoColumns = [ 
                 { "width": "1px" },
                 { sClass: "center" },
                 { sClass: "center" },
                 { sClass: "center" },
             ];

if(!isMultiCompaniaActivado){
	aoColumns.splice(1, 1);
}

cargarConfiguracionDataTable("dataTableLista", "mantenimiento/usuario/listaJson", aoColumns, "mantenimiento/usuario/ver?id=");

//$("#paginacionDiv").append($(".dataTables_paginate"));

$("#btnCrearRegistro").click(function() {
	cargarDivContenidoPrincipal("mantenimiento/usuario/ver");
});

function btnEliminarRegistro(){
	eliminarRegistrosSeleccionadosDeDataTable("dataTableLista", "mantenimiento/usuario/eliminar?ids=", "mantenimiento/usuario/listar");
}

$("#btnImprimirRegistro").click(function(){
    var selectedIds = obtenerCheckBoxSeleccionadosDeDataTable();
    console.log(selectedIds);
});
