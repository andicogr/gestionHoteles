
var aoColumns = [ 
                 { "width": "1px" },
                 { sClass: "center"},
                 { sClass: "center"}
             ];

cargarConfiguracionDataTable("dataTableLista", "mantenimiento/privilegio/listaJson", aoColumns, "mantenimiento/privilegio/ver?id=");

//$("#paginacionDiv").append($(".dataTables_paginate"));

$("#btnCrearRegistro").click(function() {
	cargarDivContenidoPrincipal("mantenimiento/privilegio/ver");
});

function btnEliminarRegistro(){
	eliminarRegistrosSeleccionadosDeDataTable("dataTableLista", "mantenimiento/privilegio/eliminar?ids=", "mantenimiento/privilegio/listar");
}

$("#btnImprimirRegistro").click(function(){
    var selectedIds = obtenerCheckBoxSeleccionadosDeDataTable();
    console.log(selectedIds);
});



