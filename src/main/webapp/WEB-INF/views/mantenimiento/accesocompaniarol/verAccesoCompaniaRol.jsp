<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="page-title" id="contenidoTitulo">
	<div class="title_left">
		<c:if test="${empty nombreMostrar}">
			<h3 id="tituloPagina">Compa&ntilde;ia</h3>
		</c:if>
		<c:if test="${not empty nombreMostrar}">
			<h3 id="tituloPagina">Compa&ntilde;ia / ${nombreMostrar}</h3>
		</c:if>
		
	</div>
</div>

<div class="row">

	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<div class="row rowTopBotonera">
					<div class="col-md-4 col-sm-4 col-xs-4" >
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_AGREGAR_COMPANIAS')">	
							<c:if test="${empty accesoCompaniaRol}">
								<button id="botonRegistrar" type="button" class="btn btn-success">
									Registrar
								</button>
							</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_ACTUALIZAR_COMPANIAS')">	
							<c:if test="${not empty accesoCompaniaRol}">
								<button id="botonActualizar" type="button" class="btn btn-success">
									Actualizar
								</button>
							</c:if>
						</sec:authorize>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-center">
						<c:if test="${not empty accesoCompaniaRol}">
							<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_ELIMINAR_COMPANIAS')">	
			                    <div class="btn-group botonOpcionesMantenimiento">
			                    	<button data-toggle="dropdown" class="btn btn-default dropdown-toggle " type="button" aria-expanded="false">
			                    		Opciones 
			                    		<span class="caret"></span>
			                    	</button>
			                    	<ul role="menu" class="dropdown-menu">
			                    		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_ROL_ELIMINAR_COMPANIAS')">	
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
            		<c:if test="${not empty accesoCompaniaRol}">
            			<input type="hidden" id="id" name="id" value="${accesoCompaniaRol.id}">
            		</c:if>

					<input type="hidden" id="idRol" name="rol.id" value="${idRol}">

					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="rol.id">
							Compa&ntilde;ia <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
							<select name="compania.id" class="form-control">
								<option value=""> --- Seleccionar ---</option>
								<c:forEach var="compania" items="${listaCompaniasActivas}">
									<option value="${compania.id}"
										<c:if test="${compania.id == accesoCompaniaRol.compania.id}">
											selected="selected"
										</c:if>
									>
										${compania.razonSocial}
									</option>
								</c:forEach>
							</select>
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
                              			<c:if test="${accesoCompaniaRol.activo == true}">
                              				checked="checked" 
                              			</c:if>
                              			<c:if test="${empty accesoCompaniaRol}">
                              				checked="checked" 
                              			</c:if>
                              		>
                            	</label>
                        	</div>
                        </div>
					</div>
				</form>
			</div>
		</div>
	</div>

</div>       

<!-- Parsley -->
<script src="resources/vendors/validate/jquery.validate.js"></script>
<script src="resources/vendors/validate/localization/messages_es_PE.js"></script>
<script src="resources/scripts/mantenimiento/accesocompaniarol/verAccesoCompaniaRol.js"></script>    