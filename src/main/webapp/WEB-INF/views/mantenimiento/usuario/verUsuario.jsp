<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="page-title" id="contenidoTitulo">
	<div class="title_left">
		<c:if test="${empty nombreMostrar}">
			<h3 id="tituloPagina">Usuarios</h3>
		</c:if>
		<c:if test="${not empty nombreMostrar}">
			<h3 id="tituloPagina">Usuarios / ${nombreMostrar}</h3>
		</c:if>
		
	</div>
</div>

<div class="row">

	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<div class="row rowTopBotonera">
					<div class="col-md-4 col-sm-4 col-xs-4" >
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_USUARIO_CREAR')">
							<c:if test="${empty usuario}">
								<button id="botonRegistrar" type="button" class="btn btn-success">
									Registrar
								</button>
							</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_USUARIO_EDITAR')">
							<c:if test="${not empty usuario}">
								<button id="botonActualizar" type="button" class="btn btn-success">
									Actualizar
								</button>
							</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_USUARIO_CREAR')">
							<c:if test="${not empty usuario}">
								<button class="btn btn-default" id="btnCrearRegistro">Crear</button>
							</c:if>
						</sec:authorize>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-center">
						<c:if test="${not empty usuario}">
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
						</c:if>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<button id="botonAtras" type="button" class="btn btn-success">Atras</button>
					</div>
				</div>
			</div>

            <div class="x_content">
            	<form id="formmularioMantenimiento" class="form-horizontal form-label-left">
            		<c:if test="${not empty usuario}">
            			<input type="hidden" id="id" name="id" value="${usuario.id}">
            		</c:if>

					<div class="item form-group" 
						<c:if test="${multiCompania == false}">
						 	style="display: none"
						</c:if>
					>
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="compania.id">
							Compa&ntilde;ia <span class="required">*</span>
                        </label>
                        <div class="col-md-4 col-sm-4 col-xs-4">
							<select name="compania.id" class="form-control">
								<c:forEach var="c" items="${listaDeCompanias}">
									<option 
										<c:if test="${usuario.compania.id == c.id }">
											selected="selected"
										</c:if>
									value="${c.id}">
										${c.razonSocial}
									</option>
								</c:forEach>
							</select>
                        </div>
					</div>
					
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="usuario">
							Usuario <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-3 col-xs-12">
                        	<input type="text" id="username" name="username" class="form-control col-md-7 col-xs-12" 
                        		value="${usuario.username}">
                        </div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="clave">
							Clave <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-3 col-xs-3">
							<input type="password" id="password" name="password" class="form-control col-md-7 col-xs-12"
								value="${usuario.password}">
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
                              			<c:if test="${usuario.activo == true}">
                              				checked="checked" 
                              			</c:if>
                              			<c:if test="${empty usuario}">
                              				checked="checked" 
                              			</c:if>
                              		>
                            	</label>
                        	</div>
                        </div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
							Bloqueado
                        </label>
                        <div class="col-md-3 col-sm-3 col-xs-3">
                        	<div class="checkbox">
                            	<label>
                              		<input type="checkbox" class="flat" 
                              			<c:if test="${usuario.bloqueado == true}">
                              				checked="checked" 
                              			</c:if>
                              		disabled="disabled">
                            	</label>
                        	</div>
                        </div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
							¿Expirar Usuario?
                        </label>
                        <div class="col-md-1 col-sm-1 col-xs-1">
                        	<div class="checkbox">
                            	<label>
                              		<input type="checkbox" class="flat" 
                              			<c:if test="${usuario.expirarUsuario == true}">
                              				checked="checked"
                              			</c:if>
                              		name="expirarUsuario" id="expirarUsuario">
                            	</label>
                        	</div>
                        </div>
                        <div id="divFechaExpiracionUsuario">
	                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="activo">
								Fecha de Expiracion
	                        </label>
	                        <div class="col-md-3 col-sm-3 col-xs-3">
	                        	<div class="control-group">
	                            	<div class="controls">
	                              		<div class="col-md-11 xdisplay_inputx form-group has-feedback">
	                                		<input type="text" class="form-control has-feedback-left" id="fechaExpiracionUsuario" 
	                                		name="fechaExpiracionUsuario" placeholder="--/--/----" aria-describedby="inputSuccess2Status2"
	                                		value="${usuario.getFechaExpiracionUsuarioConFormato()}">
	                                		<span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
	                                		<span id="inputSuccess2Status2" class="sr-only">(success)</span>
	                              		</div>
	                            	</div>
	                          	</div>
	                    	</div>
                        </div>
					</div>

					<br>

					<c:if test="${not empty usuario}">
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_USUARIO_VER_ROLES')">
		                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
		                    	<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_USUARIO_VER_ROLES')">
			                    	<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
			                        	<li role="presentation" class="active">
			                        		<a href="#tab_content1" id="tab-roles" role="tab" data-toggle="tab" aria-expanded="true">
			                        			Roles
			                        		</a>
			                        	</li>
			                      	</ul>
			                      	<div id="myTabContent" class="tab-content">
			                        	<div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="tab-roles">
			                          		<div class="panel-body">
			                          			<div class="row">
			                          				<div class="col-md-12 col-sm-12 col-xs-12">
			                          					<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_USUARIO_AGREGAR_ROLES')">
															<button type="button" class="btn btn-primary" 
																onclick="abrirFormularioAgregarUsuarioRol(${usuario.id})">
																Agregar
															</button>
														</sec:authorize>
							                            <table id="tablaListaUsuarioRol" class="table table-bordered tableSubDetalle">
							                              	<thead>
							                                	<tr>
							                                  		<th>Nombre Rol</th>
							                                  		<th>Estado</th>
							                                  		<th width="1 px"></th>
							                                	</tr>
							                              	</thead>
							                              	<tbody>
							                              		<c:forEach items="${usuario.roles}" var="usuarioRol">
								                             		<tr>
								                                		<td>
								                                			${usuarioRol.getNombreRol()}
								                                			<input type="hidden" value="${usuarioRol.id}">
								                                		</td>
								                                		<td>${usuarioRol.getEstadoUsuarioRol()}</td>
								                                		<td>
								                                			<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_USUARIO_ELIMINAR_ROLES')">
									                                			<a class="close-link eliminar-subRegistro"
									                                				href="#" onclick="btnEliminarUsuarioRol(${usuarioRol.id}, ${usuario.id})"
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
			                      	</div>
		                      	</sec:authorize>
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
<script src="resources/scripts/mantenimiento/usuario/verUsuario.js"></script>    