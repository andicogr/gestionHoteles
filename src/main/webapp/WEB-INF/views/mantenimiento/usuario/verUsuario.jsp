<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="resources/vendors/bootstrap-imageupload/dist/css/bootstrap-imageupload.min.css" rel="stylesheet">

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
				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4" >
						<button id="botonRegistrar" type="button" class="btn btn-success">
							<c:if test="${empty usuario}">
								Registrar
							</c:if>
							<c:if test="${not empty usuario}">
								Actualizar
							</c:if>
						</button>
						<c:if test="${not empty usuario}">
							<button class="btn btn-default" id="btnCrearRegistro">Crear</button>
						</c:if>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-center">
						<c:if test="${not empty usuario}">
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
            	<form id="enviarFormulario" class="form-horizontal form-label-left">
            		<c:if test="${not empty usuario}">
            			<input type="hidden" id="id" name="id" value="${usuario.id}">
            		</c:if>
            		
					<div class="item form-group" 
						<c:if test="${isMultiCompaniaActivado == false}">
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
	                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
	                    	<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
	                        	<li role="presentation" class="active"><a href="#tab_content1" id="tab-roles" role="tab" data-toggle="tab" aria-expanded="true">Roles</a>
	                        	</li>
	                      	</ul>
	                      	<div id="myTabContent" class="tab-content">
	                        	<div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="tab-roles">
                          		<div class="panel-body">
                          			<div class="row">
                          				<div class="col-md-12 col-sm-12 col-xs-12">
                          				
                              	
                              <c:if test="${empty usuario.roles}">
                              Nada
                              </c:if>
                              <c:if test="${not empty usuario.roles}">
                              Con Registros
                              </c:if>
                            <table class="table table-bordered">
                              <thead>
                                <tr>
                                  <th>Nombre Rol</th>
                                  <th>Estado</th>
                                </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${usuario.roles}" var="usuarioRol">
	                              <tr>
	                                <td>${usuarioRol.obtenerNombreRol()}</td>
	                                <td>${usuarioRol.obtenerEstadoUsuarioRol()}</td>
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
<script src="resources/scripts/mantenimiento/usuario/verUsuario.js"></script>    