<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="resources/vendors/bootstrap-imageupload/dist/css/bootstrap-imageupload.min.css" rel="stylesheet">

<div class="page-title" id="contenidoTitulo">
	<div class="title_left">
		<c:if test="${empty nombreMostrar}">
			<h3 id="tituloPagina">Compa&ntilde;ias</h3>
		</c:if>
		<c:if test="${not empty nombreMostrar}">
			<h3 id="tituloPagina">Compa&ntilde;ias / ${nombreMostrar}</h3>
		</c:if>
		
	</div>
</div>


<div class="row">

	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<div class="row rowTopBotonera">
					<div class="col-md-4 col-sm-4 col-xs-4" >
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_COMPANIA_CREAR')">
							<c:if test="${empty compania}">
								<button id="botonRegistrar" type="button" class="btn btn-success">
									Registrar
								</button>
							</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_COMPANIA_EDITAR')">
							<c:if test="${not empty compania}">
								<button id="botonActualizar" type="button" class="btn btn-success">
									Actualizar
								</button>
							</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_COMPANIA_CREAR')">
							<c:if test="${not empty compania}">
								<button class="btn btn-default" id="btnCrearRegistro">Crear</button>
							</c:if>
						</sec:authorize>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-center">
						<c:if test="${not empty compania}">
							<button class="btn btn-default" id="btnImprimirRegistro">Imprimir</button>
							<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_COMPANIA_ELIMINAR')">
			                    <div class="btn-group botonOpcionesMantenimiento">
			                    	<button data-toggle="dropdown" class="btn btn-default dropdown-toggle " type="button" aria-expanded="false">
			                    		Opciones 
			                    		<span class="caret"></span>
			                    	</button>
			                    	<ul role="menu" class="dropdown-menu">
				                      	<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN', 'SUB_MENU_COMPANIA_ELIMINAR')">
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
            		<c:if test="${not empty compania}">
            			<input type="hidden" id="id" name="id" value="${compania.id}">
            		</c:if>

					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="logo">
							Logo
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
							<div class="profile_img">
								<c:if test="${empty compania.archivo}">
									<img name="imgLogo" class="img-responsive avatar-view img-thumbnail" src="<c:url value="${pathImg}"/>"
						    		width="187px" height="187px">
								</c:if>
								<c:if test="${not empty compania.archivo}">
									<img name="imgLogo" class="img-responsive avatar-view img-thumbnail" src="<c:url value="/imagenes/getImage/${compania.archivo.id}"/>"
						    		width="187px" height="187px">
								</c:if>
						    	
						    	<!-- div class="botonImagen">
							    	<button type="button" class="closeImg eliminarImg" onclick="alert('wsw');">
							    		<span>×</span>
                 						</button>
						    	</div -->
						    	<input type="hidden" name="archivo.id" value="${compania.archivo.id}"/>
						    	<input type="checkbox" style="display: none" id="guardarImagen" name="guardarImagen"/>
						    	<input style="display: none" name="logo" type="file" onchange="readURL(this);" />
						    	<button type="button" id="eliminarImagen">EliminarImagen</button>
							</div>
                        </div>
					</div>

					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="razonSocial">
							Razon Social <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                        	<input type="text" id="razonSocial" name="razonSocial" class="form-control col-md-7 col-xs-12" 
                        		value="${compania.razonSocial}">
                        </div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="ruc">
							RUC <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-3 col-xs-3">
							<input type="text" id="ruc" name="ruc" class="number form-control col-md-7 col-xs-12"
								value="${compania.ruc}">
                        </div>
					</div>
					<div class="item form-group">
                    	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="direccion">
                    		Direccion <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                        	<input type="text" id="direccion" name="direccion" class="form-control col-md-7 col-xs-12"
                        		value="${compania.direccion}">
                        </div>
                   	</div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telefono">
                        	Telefono
                        </label>
                        <div class="col-md-2 col-sm-2 col-xs-4">
                        	<input type="text" id="telefono" name="telefono" class="form-control col-md-7 col-xs-12"
                        		value="${compania.telefono}">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="correoContacto">
                        	Correo Contacto
                        </label>
                        <div class="col-md-3 col-sm-3 col-xs-6">
                        	<input type="email" id="correoContacto" name="correoContacto" class="form-control col-md-7 col-xs-12"
                        		value="${compania.correoContacto}">
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
<script src="resources/scripts/mantenimiento/compania/verCompania.js"></script>    
<script src="resources/build/js/custom2.js"></script>