
var aoColumns = [ 
                 { "width": "1px" },
                 { sClass: "center"},
                 { sClass: "center"},
             ];

cargarConfiguracionDataTable("dataTableLista", "mantenimiento/rol/listaJson", aoColumns, "mantenimiento/rol/ver?id=");

//$("#paginacionDiv").append($(".dataTables_paginate"));

$("#btnCrearRegistro").click(function() {
	cargarDivContenidoPrincipal("mantenimiento/rol/ver");
});

function btnEliminarRegistro(){
	eliminarRegistrosSeleccionadosDeDataTable("dataTableLista", "mantenimiento/rol/eliminar?ids=", "mantenimiento/rol/listar");
}

$("#btnImprimirRegistro").click(function(){
    var selectedIds = obtenerCheckBoxSeleccionadosDeDataTable();
    console.log(selectedIds);
});



