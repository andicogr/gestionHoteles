<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_ROL_CREAR')">
							<c:if test="${empty rol}">
								<button id="botonRegistrar" type="button" class="btn btn-success">
									Registrar
								</button>
							</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_ROL_EDITAR')">
							<c:if test="${not empty rol}">
								<button id="botonActualizar" type="button" class="btn btn-success">
									Actualizar
								</button>
							</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_ROL_CREAR')">
							<c:if test="${not empty rol}">
								<button class="btn btn-default" id="btnCrearRegistro">Crear</button>
							</c:if>
						</sec:authorize>
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
		                      		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_ROL_ELIMINAR')">
			                      		<li>
			                      			<a href="javascript:;" onclick="btnEliminarRegistro()">Eliminar</a>
			                      		</li>
			                      	</sec:authorize>
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
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_VER_PRIVILEGIOS', 'SUB_MENU_ROL_VER_COMPANIAS')">	
		                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
		                    	<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
		                    		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_VER_PRIVILEGIOS')">	
			                        	<li role="presentation" <c:if test="${tab eq 'privilegios'}">class="active"</c:if>>
			                        		<a href="#tab_content1" id="tab-privilegios" role="tab" data-toggle="tab" aria-expanded="true">
			                        			Privilegios
			                        		</a>
			                        	</li>
			                        </sec:authorize>
			                        <sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_VER_COMPANIAS')">	
			                        	<li role="presentation" <c:if test="${tab eq 'companias'}">class="active"</c:if>>
			                        		<a href="#tab_content2" id="tab-companias" role="tab" data-toggle="tab" aria-expanded="true">
			                        			Compa&ntilde;ias
			                        		</a>
			                        	</li>
		                        	</sec:authorize>
		                      	</ul>
		                      	<div id="myTabContent" class="tab-content">
		                      		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_VER_PRIVILEGIOS')">	
			                        	<div role="tabpanel" class="tab-pane fade <c:if test="${tab eq 'privilegios'}">active in</c:if>" id="tab_content1" aria-labelledby="tab-privilegios">
			                          		<div class="panel-body">
			                          			<div class="row">
			                          				<div class="col-md-12 col-sm-12 col-xs-12">
			                          					<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_ACTUALIZAR_PRIVILEGIOS')">	
												            <a id="btnActualizarPrivilegios" class="btn btn-primary">
												            	Actualizar Privilegios
												            </a>
												        </sec:authorize>
											            <div id="treeListaPrivilegios">
											            </div>
			                            			</div>
			                            		</div>
			                          		</div>
			                        	</div>
			                        </sec:authorize>

									<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_VER_COMPANIAS')">	
			                        	<div role="tabpanel" class="tab-pane fade <c:if test="${tab eq 'companias'}">active in</c:if>" id="tab_content2" aria-labelledby="tab-companias">
			                          		<div class="panel-body">
			                          			<div class="row">
			                          				<div class="col-md-12 col-sm-12 col-xs-12">
			                          					<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_AGREGAR_COMPANIAS')">	
															<a class="btn btn-primary" 
																onclick="abrirFormularioAgregarAccesoCompaniaRol(${rol.id})">
																Agregar
															</a>
														</sec:authorize>
							                            <table id="tablaListaRolCompania" class="table table-bordered tableSubDetalle">
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
								                                			<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_ELIMINAR_COMPANIAS')">
									                                			<a class="close-link eliminar-subRegistro"
									                                				href="#" onclick="btnEliminarAccesoCompaniaRol(${accesoCompaniaRol.id}, ${rol.id})"
									                                			>
									                                				<i class="fa fa-trash"></i>
									                                			</a>
									                                		</sec:authorize>
								                                		</td>
								                              		</tr>
							                              		</c:forEach>
							                              	</tbody>
							                            </table>
			                            			</div>
			                            		</div>
			                          		</div>
			                        	</div>
			                        </sec:authorize>
		                      	</div>
		                    </div>
						</sec:authorize>
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