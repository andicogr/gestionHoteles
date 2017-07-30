--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2017-07-30 17:05:23

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 191 (class 1259 OID 1686921)
-- Name: privilegio; Type: TABLE; Schema: public; Owner: openerp; Tablespace: 
--

CREATE TABLE privilegio (
    id integer NOT NULL,
    uid_creacion integer,
    fecha_creacion timestamp without time zone,
    fecha_actualizacion timestamp without time zone,
    uid_actualizacion integer,
    nombre character varying(50) NOT NULL,
    descripcion character varying(250),
    activo boolean DEFAULT true,
    padre_id integer,
    padre boolean DEFAULT false,
    orden integer
);


ALTER TABLE public.privilegio OWNER TO openerp;

--
-- TOC entry 190 (class 1259 OID 1686919)
-- Name: privilegio_id_seq; Type: SEQUENCE; Schema: public; Owner: openerp
--

CREATE SEQUENCE privilegio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.privilegio_id_seq OWNER TO openerp;

--
-- TOC entry 2015 (class 0 OID 0)
-- Dependencies: 190
-- Name: privilegio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: openerp
--

ALTER SEQUENCE privilegio_id_seq OWNED BY privilegio.id;


--
-- TOC entry 2003 (class 2604 OID 1686924)
-- Name: id; Type: DEFAULT; Schema: public; Owner: openerp
--

ALTER TABLE ONLY privilegio ALTER COLUMN id SET DEFAULT nextval('privilegio_id_seq'::regclass);


--
-- TOC entry 2010 (class 0 OID 1686921)
-- Dependencies: 191
-- Data for Name: privilegio; Type: TABLE DATA; Schema: public; Owner: openerp
--

INSERT INTO privilegio VALUES (5, NULL, NULL, NULL, NULL, 'CREAR', 'Crear Usuarios', true, 4, false, NULL);
INSERT INTO privilegio VALUES (8, NULL, NULL, NULL, NULL, 'VER', 'Ver Usuarios', true, 4, false, NULL);
INSERT INTO privilegio VALUES (7, NULL, NULL, NULL, NULL, 'ELIMINAR', 'Eliminar Usuarios', true, 4, false, NULL);
INSERT INTO privilegio VALUES (6, NULL, NULL, NULL, NULL, 'EDITAR', 'Editar Usuarios', true, 4, false, NULL);
INSERT INTO privilegio VALUES (9, NULL, NULL, NULL, NULL, 'DESBLOQUEAR', 'Desbloquear Usuarios', true, 4, false, NULL);
INSERT INTO privilegio VALUES (10, NULL, NULL, NULL, NULL, 'VER_ROLES', 'Ver Roles de Usuario', true, 4, false, NULL);
INSERT INTO privilegio VALUES (11, NULL, NULL, NULL, NULL, 'AGREGAR_ROLES', 'Agregar Roles de Usuario', true, 4, false, NULL);
INSERT INTO privilegio VALUES (12, NULL, NULL, NULL, NULL, 'ELIMINAR_ROLES', 'Eliminar Roles de Usuario', true, 4, false, NULL);
INSERT INTO privilegio VALUES (13, NULL, NULL, NULL, NULL, 'EDITAR_ROLES', 'Editar Roles de Usuario', true, 4, false, NULL);
INSERT INTO privilegio VALUES (15, NULL, NULL, NULL, NULL, 'CREAR', 'Crear Roles', true, 14, false, NULL);
INSERT INTO privilegio VALUES (16, NULL, NULL, NULL, NULL, 'EDITAR', 'Edtiar Roles', true, 14, false, NULL);
INSERT INTO privilegio VALUES (17, NULL, NULL, NULL, NULL, 'ELIMINAR', 'Eliminar Roles', true, 14, false, NULL);
INSERT INTO privilegio VALUES (18, NULL, NULL, NULL, NULL, 'VER', 'Ver Roles', true, 14, false, NULL);
INSERT INTO privilegio VALUES (19, NULL, NULL, NULL, NULL, 'AGREGAR_PRIVILEGIOS', 'Agregar Privilegios a Rol', true, 14, false, NULL);
INSERT INTO privilegio VALUES (20, NULL, NULL, NULL, NULL, 'VER_PRIVILEGIOS', 'Ver Privilegios de Rol', true, 14, false, NULL);
INSERT INTO privilegio VALUES (21, NULL, NULL, NULL, NULL, 'ELIMINAR_PRIVILEGIOS', 'Eliminar Privilegios de Rol', true, 14, false, NULL);
INSERT INTO privilegio VALUES (22, NULL, NULL, NULL, NULL, 'VER_COMPANIAS', 'Ver Compañias de Rol', true, 14, false, NULL);
INSERT INTO privilegio VALUES (23, NULL, NULL, NULL, NULL, 'AGREGAR_COMPANIAS', 'Agregar Companias de Rol', true, 14, false, NULL);
INSERT INTO privilegio VALUES (24, NULL, NULL, NULL, NULL, 'ELIMINAR_COMPANIAS', 'Eliminar Companias de Rol', true, 14, false, NULL);
INSERT INTO privilegio VALUES (4, NULL, NULL, NULL, NULL, 'SUB_MENU_USUARIO', 'Usuario', true, 3, true, 10);
INSERT INTO privilegio VALUES (14, NULL, NULL, NULL, NULL, 'SUB_MENU_ROL', 'Rol', true, 3, true, 20);
INSERT INTO privilegio VALUES (3, NULL, NULL, NULL, NULL, 'MENU_USUARIOS', 'Menu Usuarios', true, NULL, true, 20);
INSERT INTO privilegio VALUES (1, NULL, NULL, NULL, NULL, 'PRIVILEGIO_ADMIN', 'Administrador del Sistema', true, NULL, false, 10);
INSERT INTO privilegio VALUES (2, NULL, NULL, NULL, NULL, 'PRIVILEGIO_USER', 'Privilegio de Usuario', true, NULL, false, 11);


--
-- TOC entry 2016 (class 0 OID 0)
-- Dependencies: 190
-- Name: privilegio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: openerp
--

SELECT pg_catalog.setval('privilegio_id_seq', 5, true);


--
-- TOC entry 2007 (class 2606 OID 1686926)
-- Name: privilegio_id_pk; Type: CONSTRAINT; Schema: public; Owner: openerp; Tablespace: 
--

ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_id_pk PRIMARY KEY (id);


--
-- TOC entry 2008 (class 2606 OID 1687377)
-- Name: privilegio_privilegio_fk; Type: FK CONSTRAINT; Schema: public; Owner: openerp
--

ALTER TABLE ONLY privilegio
    ADD CONSTRAINT privilegio_privilegio_fk FOREIGN KEY (padre_id) REFERENCES privilegio(id);


-- Completed on 2017-07-30 17:05:23

--
-- PostgreSQL database dump complete
--

