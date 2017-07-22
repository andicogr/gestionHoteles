<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="resources/vendors/bootstrap-imageupload/dist/css/bootstrap-imageupload.min.css" rel="stylesheet">

<div class="page-title" id="contenidoTitulo">
	<div class="title_left">
		<c:if test="${empty nombreMostrar}">
			<h3 id="tituloPagina">Privilegios</h3>
		</c:if>
		<c:if test="${not empty nombreMostrar}">
			<h3 id="tituloPagina">Privilegios / ${nombreMostrar}</h3>
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
							<c:if test="${empty privilegio}">
								Registrar
							</c:if>
							<c:if test="${not empty privilegio}">
								Actualizar
							</c:if>
						</button>
						<c:if test="${not empty privilegio}">
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
            	<form id="enviarFormulario" class="form-horizontal form-label-left">
            		<c:if test="${not empty privilegio}">
            			<input type="hidden" id="id" name="id" value="${privilegio.id}">
            		</c:if>

					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="nombre">
							Nombre <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                        	<input type="text" id="nombre" name="nombre" class="form-control col-md-7 col-xs-12" 
                        		value="${privilegio.nombre}">
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
                              			<c:if test="${privilegio.activo == true}">
                              				checked="checked" 
                              			</c:if>
                              			<c:if test="${empty privilegio}">
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
<script src="resources/scripts/mantenimiento/privilegio/verPrivilegio.js"></script>    
<script src="resources/build/js/custom2.js"></script>