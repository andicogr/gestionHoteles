<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>            	
            	<form id="enviarFormulario">


						<label for="compania.id">
							Rol:
                        </label>

							<select name="compania.id" class="form-control">
								<c:forEach var="c" items="${listaCompanias}">
									<option value="${c.id}">
										${c.razonSocial}
									</option>
								</c:forEach>
							</select>


						<label for="compania.id">
							Compa&ntilde;ia:
                        </label>

							<select name="compania.id" class="form-control">
								<c:forEach var="c" items="${listaCompanias}">
									<option value="${c.id}">
										${c.razonSocial}
									</option>
								</c:forEach>
							</select>

						<label for="compania.id">
							Idioma:
                        </label>
							<select name="compania.id" class="form-control">
								<c:forEach var="c" items="${listaCompanias}">
									<option value="${c.id}">
										${c.razonSocial}
									</option>
								</c:forEach>
							</select>



                          <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat" checked="checked"> Por Defecto
                            </label>
                          </div>

				</form>