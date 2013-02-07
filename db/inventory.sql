--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: amphur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE amphur (
    amphur_id integer NOT NULL,
    amphur_name character varying(255),
    province_id integer
);


ALTER TABLE public.amphur OWNER TO postgres;

--
-- Name: amphur_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE amphur_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.amphur_seq OWNER TO postgres;

--
-- Name: amphur_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('amphur_seq', 1, false);


--
-- Name: inventory; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE inventory (
    inventory_id bigint NOT NULL,
    available bigint,
    balance bigint,
    product_code character varying(255)
);


ALTER TABLE public.inventory OWNER TO postgres;

--
-- Name: inventory_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE inventory_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inventory_seq OWNER TO postgres;

--
-- Name: inventory_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_seq', 1, true);


--
-- Name: order; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "order" (
    order_code bigint NOT NULL,
    create_by character varying(50),
    create_date timestamp without time zone,
    update_by character varying(50),
    update_date timestamp without time zone,
    customer_name character varying(255),
    ems_price integer,
    order_date timestamp without time zone,
    sender_address character varying(255),
    sender_name character varying(255),
    ship_address character varying(255),
    ship_name character varying(255),
    status character varying(255),
    tracking_number character varying(255)
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- Name: order_detail; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE order_detail (
    order_detail_id bigint NOT NULL,
    create_by character varying(50),
    create_date timestamp without time zone,
    update_by character varying(50),
    update_date timestamp without time zone,
    discount_amount double precision,
    sell_price double precision,
    status character varying(255),
    order_code bigint,
    product_code character varying(255)
);


ALTER TABLE public.order_detail OWNER TO postgres;

--
-- Name: order_detail_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE order_detail_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_detail_seq OWNER TO postgres;

--
-- Name: order_detail_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('order_detail_seq', 1, false);


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE product (
    product_code character varying(255) NOT NULL,
    create_by character varying(50),
    create_date timestamp without time zone,
    update_by character varying(50),
    update_date timestamp without time zone,
    color character varying(255),
    cost_price double precision,
    detail character varying(255),
    last_sale_date timestamp without time zone,
    product_name character varying(255),
    sell_price double precision,
    status character varying(255)
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_attach; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE product_attach (
    attach_id bigint NOT NULL,
    create_by character varying(50),
    create_date timestamp without time zone,
    update_by character varying(50),
    update_date timestamp without time zone,
    attach_date timestamp without time zone,
    attach_type character varying(50),
    content_type character varying(50),
    description character varying(255),
    file_size_bytes double precision,
    order_no integer,
    origin_filename character varying(100),
    refer_filename character varying(100),
    product_code character varying(255)
);


ALTER TABLE public.product_attach OWNER TO postgres;

--
-- Name: product_attach_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE product_attach_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_attach_seq OWNER TO postgres;

--
-- Name: product_attach_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('product_attach_seq', 1, false);


--
-- Name: province; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE province (
    province_id integer NOT NULL,
    province_name character varying(255)
);


ALTER TABLE public.province OWNER TO postgres;

--
-- Name: province_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE province_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.province_seq OWNER TO postgres;

--
-- Name: province_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('province_seq', 1, false);


--
-- Name: tambon; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tambon (
    tambon_id integer NOT NULL,
    tambon_name character varying(255),
    amphur_id integer
);


ALTER TABLE public.tambon OWNER TO postgres;

--
-- Name: tambon_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tambon_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tambon_seq OWNER TO postgres;

--
-- Name: tambon_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tambon_seq', 1, false);


--
-- Data for Name: amphur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY amphur (amphur_id, amphur_name, province_id) FROM stdin;
\.


--
-- Data for Name: inventory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY inventory (inventory_id, available, balance, product_code) FROM stdin;
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "order" (order_code, create_by, create_date, update_by, update_date, customer_name, ems_price, order_date, sender_address, sender_name, ship_address, ship_name, status, tracking_number) FROM stdin;
\.


--
-- Data for Name: order_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY order_detail (order_detail_id, create_by, create_date, update_by, update_date, discount_amount, sell_price, status, order_code, product_code) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product (product_code, create_by, create_date, update_by, update_date, color, cost_price, detail, last_sale_date, product_name, sell_price, status) FROM stdin;
\.


--
-- Data for Name: product_attach; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_attach (attach_id, create_by, create_date, update_by, update_date, attach_date, attach_type, content_type, description, file_size_bytes, order_no, origin_filename, refer_filename, product_code) FROM stdin;
\.


--
-- Data for Name: province; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY province (province_id, province_name) FROM stdin;
\.


--
-- Data for Name: tambon; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tambon (tambon_id, tambon_name, amphur_id) FROM stdin;
\.


--
-- Name: amphur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY amphur
    ADD CONSTRAINT amphur_pkey PRIMARY KEY (amphur_id);


--
-- Name: inventory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY inventory
    ADD CONSTRAINT inventory_pkey PRIMARY KEY (inventory_id);


--
-- Name: order_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY order_detail
    ADD CONSTRAINT order_detail_pkey PRIMARY KEY (order_detail_id);


--
-- Name: order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (order_code);


--
-- Name: product_attach_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY product_attach
    ADD CONSTRAINT product_attach_pkey PRIMARY KEY (attach_id);


--
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_code);


--
-- Name: province_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY province
    ADD CONSTRAINT province_pkey PRIMARY KEY (province_id);


--
-- Name: tambon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tambon
    ADD CONSTRAINT tambon_pkey PRIMARY KEY (tambon_id);


--
-- Name: fk23ae5a628a8f541e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_detail
    ADD CONSTRAINT fk23ae5a628a8f541e FOREIGN KEY (product_code) REFERENCES product(product_code);


--
-- Name: fk23ae5a62adbf4bfe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_detail
    ADD CONSTRAINT fk23ae5a62adbf4bfe FOREIGN KEY (order_code) REFERENCES "order"(order_code);


--
-- Name: fk8790195c8a8f541e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inventory
    ADD CONSTRAINT fk8790195c8a8f541e FOREIGN KEY (product_code) REFERENCES product(product_code);


--
-- Name: fkabba94c12993208; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY amphur
    ADD CONSTRAINT fkabba94c12993208 FOREIGN KEY (province_id) REFERENCES province(province_id);


--
-- Name: fkcb7c1a21c2f1b7e8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tambon
    ADD CONSTRAINT fkcb7c1a21c2f1b7e8 FOREIGN KEY (amphur_id) REFERENCES amphur(amphur_id);


--
-- Name: fke25c83958a8f541e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_attach
    ADD CONSTRAINT fke25c83958a8f541e FOREIGN KEY (product_code) REFERENCES product(product_code);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

