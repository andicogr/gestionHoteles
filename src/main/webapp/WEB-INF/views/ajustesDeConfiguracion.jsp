<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="resources/vendors/bootstrap-imageupload/dist/css/bootstrap-imageupload.min.css" rel="stylesheet">

<div class="page-title" id="contenidoTitulo">
	<div class="title_left">
		<h3 id="tituloPagina">Ajustes de Configuraci&oacute;n</h3>
	</div>
</div>


<div class="row">

	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<div class="row rowTopBotonera">
					<div class="col-md-4 col-sm-4 col-xs-4" >
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_AJUSTES_CONFIG_GUARDAR')">
							<c:if test="${empty ajustesConfig}">
								<button id="botonRegistrar" type="button" class="btn btn-success">
									Guardar
								</button>
							</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','SUB_MENU_AJUSTES_CONFIG_GUARDAR')">
							<c:if test="${not empty ajustesConfig}">
								<button id="botonActualizar" type="button" class="btn btn-success">
									Guardar
								</button>
							</c:if>
						</sec:authorize>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-center">
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
					</div>
				</div>
			</div>

            <div class="x_content">
            	<form id="formmularioMantenimiento" class="form-horizontal form-label-left">
            		<c:if test="${not empty ajustesConfig}">
            			<input type="hidden" id="id" name="id" value="${ajustesConfig.id}">
            		</c:if>

					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
							Opciones
                        </label>

                        <div class="col-md-3 col-sm-3 col-xs-3">
                        	<div class="checkbox">
                            	<label>
                              		<input type="checkbox" id="activarMultiCompania" name="activarMultiCompania" class="flat"
                              			<c:if test="${ajustesConfig.activarMultiCompania == true}">
                              				checked="checked" 
                              			</c:if>
                              		> Administrar multiples compa&ntilde;ias
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
<script src="resources/scripts/ajustesDeConfiguracion.js"></script>