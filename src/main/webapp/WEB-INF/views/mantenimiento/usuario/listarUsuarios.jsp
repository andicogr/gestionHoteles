<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- Datatables CSS-->
<link href="resources/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="resources/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
<!-- Datatables CSS-->

<input type="hidden" id="multiCompania" value="${multiCompania}">

<div class="page-title" id="contenidoTitulo">
	<div class="title_left">
		<h3 id="tituloPagina">Usuarios</h3>
	</div>
</div>

<div class="row">

	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<div class="row rowTopBotonera">
					<div class="col-sm-8" >
						<div class="row">
							<div class="col-md-6 col-sm-6 col-xs-6" >
								<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_USUARIO_CREAR')">
									<button class="btn btn-success" id="btnCrearRegistro">Crear</button>
								</sec:authorize>
							</div>
							<div class="col-md-6 col-sm-6 col-xs-6 text-center">
								<div id="botoneraCentro" style="display: none">
									<button class="btn btn-default" data-toggle="confirmation" id="btnImprimirRegistro">Imprimir</button>
									<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_USUARIO_DESBLOQUEAR', 'SUB_MENU_USUARIO_ELIMINAR')">
					                    <div class="btn-group botonOpcionesMantenimiento">
					                    	<button data-toggle="dropdown" class="btn btn-default dropdown-toggle " type="button" aria-expanded="false">
					                    		Opciones 
					                    		<span class="caret"></span>
					                    	</button>
					                    	<ul role="menu" class="dropdown-menu">
					                    		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_USUARIO_DESBLOQUEAR')">
						                      		<li>
						                      			<a href="#">Desbloquear Usuario</a>
						                      		</li>
						                      	</sec:authorize>
						                      	<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_USUARIO_ELIMINAR')">
						                      		<li class="divider"></li>
						                      		<li>
						                      			<a href="javascript:;" onclick="btnEliminarRegistro()">Eliminar</a>
						                      		</li>
						                      	</sec:authorize>
					                    	</ul>
					                    </div>
									</sec:authorize>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-4 text-right" id="paginacionDiv">

					</div>
				</div>
			</div>
			<div class="x_content">
				<table id="dataTableLista" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" id="checkBoxAll" onclick="seleccionarTodosLosCehckBoxDeDataTable();" class="flat"/></th>
							<c:if test="${multiCompania == true}">
								<th>Compa&ntilde;ia</th>
							</c:if>
							<th>Username</th>
							<th>Estado</th>
                        </tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>

<!-- Data Tables JS-->
<script src="resources/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="resources/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- Data Tables JS-->

<script src="resources/scripts/mantenimiento/usuario/listarUsuarios.js"></script>    