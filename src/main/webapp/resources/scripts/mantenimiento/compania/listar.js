
var aoColumns = [ 
                 {},
                 { sClass: "center"},
                 { sClass: "center"},
                 { sClass: "center"},
                 { sClass: "center"},
             ];

cargarConfiguracionDataTable("dataTableLista", "mantenimiento/compania/listaJson", aoColumns, "mantenimiento/compania/ver?id=");

//$("#paginacionDiv").append($(".dataTables_paginate"));

$("#btnCrearRegistro").click(function() {
	cargarContenido("mantenimiento/compania/ver");
});

$("#btnEliminarRegistro").click(function(){
	eliminarRegistroDataTable("dataTableLista", "mantenimiento/compania/eliminar?ids=", "mantenimiento/compania/listar");
});

$("#btnImprimirRegistro").click(function(){
    var selectedIds = obtenerCheckBoxDataTableSeleccionados();
    console.log(selectedIds);
});



