<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
           
<div class="menu_section">
	<h3>General</h3>
	<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','PRIVILEGIO_USER')">
	  	<ul class="nav side-menu">
	  		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','MENU_USUARIOS')">
		    	<li>
		    		<a>
		    			<i class="fa fa-cogs"></i> Usuarios <span class="fa fa-chevron-down"></span>
		    		</a>
		    		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','MENU_USUARIOS')">
			      		<ul class="nav child_menu">
			      			<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','MENU_USUARIOS_SUB_MENU_USUARIO')">
			      				<li><a href="#" id="usuarioMenuListar">Usuario</a></li>
			      			</sec:authorize>
			        		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','MENU_USUARIOS_SUB_MENU_ROL')">
					        	<li><a href="#" id="rolMenuListar">Rol</a></li>
			        		</sec:authorize>
			      		</ul>
			      	</sec:authorize>
		    	</li>
		    </sec:authorize>
		    <sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','MENU_CONFIGURACION')">
			<li>
				<a>
					<i class="fa fa-cogs"></i> Configuraci&oacute;n <span class="fa fa-chevron-down"></span>
				</a>
				<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','MENU_CONFIGURACION')">
		      		<ul class="nav child_menu">
		      			<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN','MENU_CONFIGURACION_SUB_MENU_COMPANIA')">
		        			<li><a href="#" id="companiaMenuListar">Compañia</a></li>
		        		</sec:authorize>
		        		<sec:authorize access="hasAnyRole('PRIVILEGIO_ADMIN')">
		        			<li><a href="#" id="ajustesDeConfiguracionMenu">Ajustes de Configuraci&oacute;n</a></li>
		        		</sec:authorize>
		      		</ul>
		      	</sec:authorize>
	   		</li>
	   		</sec:authorize>
	  	</ul>
  	</sec:authorize>
</div>