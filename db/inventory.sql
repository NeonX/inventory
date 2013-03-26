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
    product_code character varying(255),
    detail text,
    color character varying,
    size character varying,
    available integer,
    balance integer
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

SELECT pg_catalog.setval('inventory_seq', 33, true);


--
-- Name: order; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "order" (
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
    tracking_number character varying(255),
    note text,
    order_code character varying(15) NOT NULL
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
    product_code character varying(255),
    quantity integer,
    size character varying,
    color character varying,
    order_code character varying(15) NOT NULL,
    payable_confirm boolean DEFAULT false,
    reserve_complete boolean DEFAULT false
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

SELECT pg_catalog.setval('order_detail_seq', 12, true);


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE product (
    product_code character varying(255) NOT NULL,
    create_by character varying(50),
    create_date timestamp without time zone,
    update_by character varying(50),
    update_date timestamp without time zone,
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
    origin_filename text,
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

SELECT pg_catalog.setval('product_attach_seq', 33, true);


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
-- Name: shop; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE shop (
    shop_id integer NOT NULL,
    shop_code character varying(10),
    shop_name character varying(255),
    create_by character varying(50),
    create_date timestamp without time zone,
    update_by character varying(50),
    update_date timestamp without time zone
);


ALTER TABLE public.shop OWNER TO postgres;

--
-- Name: shop_member; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE shop_member (
    username character varying(10) NOT NULL,
    password character varying(255),
    shop_id integer NOT NULL,
    create_by character varying(50),
    create_date timestamp without time zone,
    update_by character varying(50),
    update_date timestamp without time zone
);


ALTER TABLE public.shop_member OWNER TO postgres;

--
-- Name: shop_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE shop_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shop_seq OWNER TO postgres;

--
-- Name: shop_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('shop_seq', 1, false);


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

COPY inventory (inventory_id, product_code, detail, color, size, available, balance) FROM stdin;
33	P7110207	- เสื้อ\nอก: 36 นิ้ว\nยาว: 25 นิ้ว\n- กางเกงกระโปรงอัดพรีต\nเอว: ได้ถึง 32\nยาว : 14 นิ้ว	กรม	Free size	29	30
32	P2453408	อก 36" เอว 30" สะโพก - 40" ยาว 32" ความยาวแขน 19"	ดำ	M	9	10
31	P2453408	อก 34" เอว 27" สะโพก free - 40" ยาว 32" ความยาวแขน 19"	ดำ	S	9	10
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "order" (create_by, create_date, update_by, update_date, customer_name, ems_price, order_date, sender_address, sender_name, ship_address, ship_name, status, tracking_number, note, order_code) FROM stdin;
\N	2013-03-23 18:20:56.777	\N	\N	Nunthanat Klanchuen	30	2013-03-23 00:00:00					รอชำระ	\N		ORD-230313-2489
\N	2013-03-23 18:25:51.086	\N	\N	ooooo	30	2013-03-23 00:00:00					รอชำระ	\N		ORD-230313-1757
\N	2013-03-23 18:38:09.513	\N	\N	nuttapong	30	2013-03-23 00:00:00					รอชำระ	\N		ORD-230313-1433
\.


--
-- Data for Name: order_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY order_detail (order_detail_id, create_by, create_date, update_by, update_date, discount_amount, sell_price, product_code, quantity, size, color, order_code, payable_confirm, reserve_complete) FROM stdin;
6	\N	\N	\N	\N	0	790	P7110207	1	Free size	กรม	ORD-230313-2489	f	t
7	\N	\N	\N	\N	0	750	P2453408	1	S	ดำ	ORD-230313-2489	f	t
8	\N	\N	\N	\N	0	790	P7110207	2	Free size	กรม	ORD-230313-1757	f	t
9	\N	\N	\N	\N	0	750	P2453408	1	S	ดำ	ORD-230313-1757	f	t
10	\N	\N	\N	\N	0	790	P7110207	1	Free size	กรม	ORD-230313-1433	f	t
11	\N	\N	\N	\N	0	750	P2453408	1	M	ดำ	ORD-230313-1433	f	t
12	\N	\N	\N	\N	0	750	P2453408	1	S	ดำ	ORD-230313-1433	f	t
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product (product_code, create_by, create_date, update_by, update_date, cost_price, detail, last_sale_date, product_name, sell_price, status) FROM stdin;
P2453408	\N	2013-03-16 16:41:34.442	\N	2013-03-16 16:53:29.695	500	ปลายแขนจั้มสามารถดึงขึ้นมาเหมือนภาพ หรือจะเอาลงก็สวยดูดีค่ะ มีซิป ด้านหลัง	\N	เดรสดำ เว้าแขน	750	\N
P7110207	\N	2013-03-16 16:57:16.539	\N	\N	550	set เสื้อกางเกงกระโปรงอัดพรีต ลุคชิลๆ แบบไม่หวานมมาก	\N	Two-Piece Partysu Set	790	\N
\.


--
-- Data for Name: product_attach; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_attach (attach_id, create_by, create_date, update_by, update_date, attach_date, attach_type, content_type, description, file_size_bytes, order_no, origin_filename, refer_filename, product_code) FROM stdin;
27	\N	2013-03-16 16:53:23.221	\N	2013-03-16 16:53:23.221	\N	IMGS	image/jpeg	black dress 1	30648	\N	black dress 1.jpg	IMG20130316_165323-1710.jpg	P2453408
28	\N	2013-03-16 16:53:23.299	\N	2013-03-16 16:53:23.299	\N	IMGS	image/jpeg	black dress 2	21655	\N	black dress 2.jpg	IMG20130316_165323-7085.jpg	P2453408
29	\N	2013-03-16 16:53:23.362	\N	2013-03-16 16:53:23.362	\N	IMGS	image/jpeg	black dress 3	34808	\N	black dress 3.jpg	IMG20130316_165323-2008.jpg	P2453408
30	\N	2013-03-16 16:53:23.455	\N	2013-03-16 16:53:23.455	\N	IMGS	image/jpeg	black dress 4	37312	\N	black dress 4.jpg	IMG20130316_165323-1820.jpg	P2453408
31	\N	2013-03-16 16:53:23.549	\N	2013-03-16 16:53:23.549	\N	IMGS	image/jpeg	black dress 5	30958	\N	black dress 5.jpg	IMG20130316_165323-6975.jpg	P2453408
32	\N	2013-03-16 16:57:11.563	\N	2013-03-16 16:57:11.563	\N	IMGS	image/jpeg	542135_345634108878167_1162599210_n	69022	\N	542135_345634108878167_1162599210_n.jpg	IMG20130316_165711-3360.jpg	P7110207
33	\N	2013-03-16 16:57:11.703	\N	2013-03-16 16:57:11.703	\N	IMGS	image/jpeg	Two-Piece Partysu Set	100024	\N	Two-Piece Partysu Set.jpg	IMG20130316_165711-1393.jpg	P7110207
\.


--
-- Data for Name: province; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY province (province_id, province_name) FROM stdin;
\.


--
-- Data for Name: shop; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY shop (shop_id, shop_code, shop_name, create_by, create_date, update_by, update_date) FROM stdin;
\.


--
-- Data for Name: shop_member; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY shop_member (username, password, shop_id, create_by, create_date, update_by, update_date) FROM stdin;
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
-- Name: shop_member_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY shop_member
    ADD CONSTRAINT shop_member_pkey PRIMARY KEY (username);


--
-- Name: shop_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY shop
    ADD CONSTRAINT shop_pkey PRIMARY KEY (shop_id);


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
-- Name: shop_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY shop_member
    ADD CONSTRAINT shop_fkey FOREIGN KEY (shop_id) REFERENCES shop(shop_id);


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

