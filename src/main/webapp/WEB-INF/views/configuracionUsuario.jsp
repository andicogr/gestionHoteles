<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>            	


<div class="modal-dialog modal-sm">
	<div class="modal-content">
		<form id="formularioConfiguracionUsuario" method="post" action="<c:url value = "/"/>">
			<div class="modal-header">
				<button type="button" id="btnCloseSmallModal" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
	      		</button>
	      		<h4 class="modal-title" id="smallModalTitulo">
	      			Configracion de Usuario
				</h4>
			</div>

			<div class="modal-body" id="smallModalContenido">

				<label for="idRol">
					Rol:
				</label>
				<input type="hidden" id="companiaPorDefectoId" value="${usuarioSession.getCompaniaPorDefectoId()}"/>
				<select name="idRol" id="idRol" class="form-control" onchange="cambiarRol()">
					<c:forEach var="rol" items="${listaRolesActivos}">
						
						<option value="${rol.id}" <c:if test="${usuarioSession.getRolPorDefectoId() == rol.id}">selected="selected"</c:if> >
							${rol.nombre}
						</option>
					</c:forEach>
				</select>

				<c:if test="${multiCompania eq true}">
					<label for="idCompania">
						Compa&ntilde;ia:
					</label>

					<select name="idCompania" id="idCompania" class="form-control">
					</select>

					<div class="checkbox">
						<label>
							<input type="checkbox" name="porDefecto" class="flat"
								<c:if test="${usuarioSession.getFlagPorDefecto() eq true}">
                              		checked="checked" 
                              	</c:if>
							> Por Defecto
						</label>
					</div>
				</c:if>

			</div>

			<div class="modal-footer">
	      		<button type="button" id="btnCloseSmallModal" class="btn btn-default" data-dismiss="modal">Cerrar</button>
	      		<button type="submit" class="btn btn-primary">Guardar</button>
	    	</div>
		</form>
	</div>
</div>
















<script src="resources/scripts/configuracionUsuario.js"></script>