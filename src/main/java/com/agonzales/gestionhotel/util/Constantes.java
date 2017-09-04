package com.agonzales.gestionhotel.util;

public class Constantes {
	
	public final static Integer SUPER_UID = 1;
	public final static Integer SUPER_ROL_ID = 1;
	public final static Integer SUPER_USUARIO_ROL_ID = 1;
	public final static Integer MI_EMPRESA_ID = 2;
	public final static Integer TODAS_EMPRESAS_ID = 1;

	public final static Integer INTENTOS_FALLIDOS_MAXIMOS = 3;
	public final static String UID = "uid";
	public final static String USUARIO_SESION = "usuarioSesion";
	public final static String FORMATO_FECHA_DDMMYYYY = "dd/MM/yyyy";
	
	public final static String ESTADO_ACTIVO = "activo";
	public final static String ESTADO_ACTIVO_ETIQUETA = "Activo";
	public final static String ESTADO_INACTIVO = "inactivo";
	public final static String ESTADO_INACTIVO_ETIQUETA = "Inactivo";
	public final static String ESTADO_BLOQUEADO= "bloqueado";
	public final static String ESTADO_BLOQUEADO_ETIQUETA= "Bloqueado";
	public final static String ESTADO_TODOS = "todos";
	
	public final static String MENSAJE_REGISTRO_CORRECTO = "Los datos se registraron correctamente.";
	public final static String MENSAJE_ACTUALIZACION_CORRECTA = "Los datos se actualizaron correctamente.";
	public final static String MENSAJE_REGISTRO_ELIMINADO = "El registro se elimino correctamente";
	public final static String MENSAJE_REGISTROS_ELIMINADOS = "Los registros se eliminaron correctamente";
	public final static String MENSAJE_PRIVILEGIOS_ACTUALIZADOS_CORRECTAMENTE = "Los privilegios se actualizaron correctamente.";	
	
	public final static String PROPERTIES_IMAGENES_PATH = "imagenes.path";
	public final static String PREFIJO_IMG_LOGO = "imgLogo-";
	public final static String PATH_DEFAULT_IMAGEN = "resources/images/default_image.png";
	
	//MENSAJES DE SELECT - INSERT - DELETE - UPDATE
	public final static String LISTA_VACIA = "No se encontraron resultados para los criterios de busqueda";
	public final static String MENSAJE_ERROR_GUARDAR = "No se pudo guardar la informacion, comuniquese con el administrador del sistema";
	public final static String GUARDAR_OK = "Los datos se guardaron correctamente";
	
	//VARIABLES DE SESION
	public final static String MULTICOMPANIA_ACTIVADO = "multiCompaniaActivado";
	public final static String LISTA_COMPANIAS_ACTIVAS = "listaCompaniasActivas";
	public final static String USUARIO_SESSION_ID = "idUsuario";
	public final static String COMPANIA_SESSION_ID = "companiaActual";
	public final static String ROL_SESSION_ID = "rolActual";

}
