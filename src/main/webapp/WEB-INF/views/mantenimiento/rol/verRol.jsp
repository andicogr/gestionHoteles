<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="resources/vendors/gijgo/gijgo.css" rel="stylesheet" type="text/css" />

<div class="page-title" id="contenidoTitulo">
	<div class="title_left">
		<c:if test="${empty nombreMostrar}">
			<h3 id="tituloPagina">Roles</h3>
		</c:if>
		<c:if test="${not empty nombreMostrar}">
			<h3 id="tituloPagina">Roles / ${nombreMostrar}</h3>
		</c:if>
		
	</div>
</div>


<div class="row">

	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<div class="row rowTopBotonera">
					<div class="col-md-4 col-sm-4 col-xs-4" >
						<button id="botonRegistrar" type="button" class="btn btn-success">
							<c:if test="${empty rol}">
								Registrar
							</c:if>
							<c:if test="${not empty rol}">
								Actualizar
							</c:if>
						</button>
						<c:if test="${not empty rol}">
							<button class="btn btn-default" id="btnCrearRegistro">Crear</button>
						</c:if>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-center">
						<c:if test="${not empty rol}">
							<button class="btn btn-default" id="btnImprimirRegistro">Imprimir</button>
		                    <div class="btn-group botonOpcionesMantenimiento">
		                    	<button data-toggle="dropdown" class="btn btn-default dropdown-toggle " type="button" aria-expanded="false">
		                    		Opciones 
		                    		<span class="caret"></span>
		                    	</button>
		                    	<ul role="menu" class="dropdown-menu">
		                      		<li>
		                      			<a href="#">Desbloquear Usuario</a>
		                      		</li>
		                      		<li class="divider"></li>
		                      		<li>
		                      			<a href="javascript:;" onclick="btnEliminarRegistro()">Eliminar</a>
		                      		</li>
		                    	</ul>
		                    </div>
						</c:if>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<button id="botonAtras" type="button" class="btn btn-success">Atras</button>
					</div>
				</div>
			</div>

            <div class="x_content">
            	<form id="formmularioMantenimiento" class="form-horizontal form-label-left">
            		<c:if test="${not empty rol}">
            			<c:set var="existeRol" value="true"/> 
            			<input type="hidden" id="id" name="id" value="${rol.id}">
            		</c:if>
            		<c:if test="${empty rol}">
            			<c:set var="existeRol" value="false"/> 
            			<input type="hidden" id="id" name="id" value="${rol.id}">
            		</c:if>

					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="nombre">
							Nombre <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                        	<input type="text" id="nombre" name="nombre" class="form-control col-md-7 col-xs-12" 
                        		value="${rol.nombre}">
                        </div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="activo">
							Activo
                        </label>
                        <div class="col-md-3 col-sm-3 col-xs-3">
                        	<div class="checkbox">
                            	<label>
                              		<input type="checkbox" id="activo" name="activo" class="flat"
                              			<c:if test="${rol.activo == true}">
                              				checked="checked" 
                              			</c:if>
                              			<c:if test="${empty rol}">
                              				checked="checked" 
                              			</c:if>
                              		>
                            	</label>
                        	</div>
                        </div>
					</div>
					
					<c:if test="${not empty rol}">
	                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
	                    	<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
	                        	<li role="presentation" class="active">
	                        		<a href="#tab_content1" id="tab-roles" role="tab" data-toggle="tab" aria-expanded="true">
	                        			Privilegios
	                        		</a>
	                        	</li>
	                        	<li role="presentation" class="">
	                        		<a href="#tab_content2" id="tab-companias" role="tab" data-toggle="tab" aria-expanded="true">
	                        			Compa&ntilde;ias
	                        		</a>
	                        	</li>
	                      	</ul>
	                      	<div id="myTabContent" class="tab-content">
	                        	<div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="tab-roles">
	                          		<div class="panel-body">
	                          			<div class="row">
	                          				<div class="col-md-12 col-sm-12 col-xs-12">
										            <button type="button" id="btnActualizarPrivilegios" class="btn btn-primary">
										            	Actualizar Privilegios
										            </button>
										            <div id="treeListaPrivilegios">
										            </div>
	                            			</div>
	                            		</div>
	                          		</div>
	                        	</div>

	                        	<div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="tab-companias">
	                          		<div class="panel-body">
	                          			<div class="row">
	                          				<div class="col-md-12 col-sm-12 col-xs-12">
												<button type="button" class="btn btn-primary" 
													onclick="abrirFormularioAgregarCompaniaPorRol(${rol.id})">
													Agregar
												</button>
					                            <table id="tablaListaRolCompania" class="table table-bordered">
					                              	<thead>
					                                	<tr>
					                                  		<th>Compa&ntilde;ia</th>
					                                  		<th>Estado</th>
					                                  		<th width="1 px"></th>
					                                	</tr>
					                              	</thead>
					                              	<tbody>
					                              		<c:forEach items="${rol.companias}" var="accesoCompaniaRol">
						                             		<tr>
						                                		<td>
						                                			${accesoCompaniaRol.getNombreCompania()}
						                                			<input type="hidden" value="${accesoCompaniaRol.id}">
						                                		</td>
						                                		<td>${accesoCompaniaRol.getEstadoAccesoCompaniaRol()}</td>
						                                		<td>
						                                			<a class="close-link eliminar-subRegistro"
						                                				href="#" onclick="btnEliminarAccesoCompaniaRol(${accesoCompaniaRol.id}, ${rol.id})"
						                                			>
						                                				<i class="fa fa-trash"></i>
						                                			</a>
						                                		</td>
						                              		</tr>
					                              		</c:forEach>
					                              	</tbody>
					                            </table>
	                            			</div>
	                            		</div>
	                          		</div>
	                        	</div>
	                      	</div>
	                    </div>
					</c:if>
				</form>
			</div>
		</div>
	</div>

</div>       

<!-- Parsley -->
<script src="resources/vendors/validate/jquery.validate.js"></script>
<script src="resources/vendors/validate/localization/messages_es_PE.js"></script>
<script src="resources/scripts/mantenimiento/rol/verRol.js"></script>   
<script src="resources/vendors/gijgo/gijgo.js" type="text/javascript"></script> 

<script type="text/javascript">
	var existeRol = '<c:out value="${existeRol}"/>';
	if(eval(existeRol)){
        var tree = $('#treeListaPrivilegios').tree({
            primaryKey: 'id',
            uiLibrary: 'bootstrap',
			dataSource: baseURL + 'mantenimiento/privilegio/listaPrivilegiosPadre?idRol=' + $("#id").val(),
            checkboxes: true
        });
	}

</script>