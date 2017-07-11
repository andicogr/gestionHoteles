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
							<button class="btn btn-danger" id="btnEliminarRegistro">Eliminar</button>
							<button class="btn btn-default" id="btnImprimirRegistro">Imprimir</button>
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
					<div class="item form-group">
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
                        	<input type="text" id="usuario" name="usuario" class="form-control col-md-7 col-xs-12" 
                        		value="${usuario.usuario}">
                        </div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="clave">
							Clave <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-3 col-xs-3">
							<input type="password" id="clave" name="clave" class="form-control col-md-7 col-xs-12"
								value="${usuario.clave}">
                        </div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="clave">
							Estado
                        </label>
                        <div class="col-md-3 col-sm-3 col-xs-3">
							<select name="estado" class="form-control">
								<c:forEach var="e" items="${estadosDeUsuario}">
									<option 
										<c:if test="${usuario.estado == e.key }">
											selected="selected"
										</c:if> 
									value="${e.key}">
										${e.value}
									</option>
								</c:forEach>
							</select>
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
                            <table class="table table-bordered">
                              <thead>
                                <tr>
                                  <th>#</th>
                                  <th>Nombre Rol</th>
                                  <th>Last Name</th>
                                  <th>Username</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr>
                                  <th scope="row">1</th>
                                  <td>Mark</td>
                                  <td>Otto</td>
                                  <td>@mdo</td>
                                </tr>
                                <tr>
                                  <th scope="row">2</th>
                                  <td>Jacob</td>
                                  <td>Thornton</td>
                                  <td>@fat</td>
                                </tr>
                                <tr>
                                  <th scope="row">3</th>
                                  <td>Larry</td>
                                  <td>the Bird</td>
                                  <td>@twitter</td>
                                </tr>
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
<script src="resources/build/js/custom2.js"></script>