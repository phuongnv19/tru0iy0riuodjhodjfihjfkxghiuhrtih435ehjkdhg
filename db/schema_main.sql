--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0 (Debian 12.0-2.pgdg100+1)
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: action_audit; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.action_audit (
    audit_id bigint NOT NULL,
    actor_type bigint,
    actory_id bigint,
    audit_type_id bigint,
    create_date timestamp(6) without time zone,
    description character varying(255),
    object_id bigint,
    object_type_id bigint,
    server_ip character varying(255),
    source_id bigint,
    user_id bigint
);


ALTER TABLE public.action_audit OWNER TO erp;

--
-- Name: action_audit_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.action_audit_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.action_audit_seq OWNER TO erp;

--
-- Name: attach_document; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.attach_document (
    attach_id bigint NOT NULL,
    document_path character varying(500) NOT NULL,
    source_id bigint,
    document_name character varying(200),
    table_name character varying(100),
    create_date date,
    create_user_id bigint
);


ALTER TABLE public.attach_document OWNER TO erp;

--
-- Name: audit_detail; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.audit_detail (
    id bigint NOT NULL,
    audit_id bigint,
    col_name character varying(255),
    create_date timestamp(6) without time zone,
    new_name character varying(255),
    old_name character varying(255),
    table_name character varying(50),
    record_id bigint
);


ALTER TABLE public.audit_detail OWNER TO erp;

--
-- Name: audit_detail_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.audit_detail_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.audit_detail_seq OWNER TO erp;

--
-- Name: company; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.company (
    company_id bigint NOT NULL,
    abn bigint,
    acn character varying(255),
    company_code character varying(255),
    company_email character varying(255),
    company_full_name character varying(255),
    company_intro character varying(255),
    company_landline_phone bigint,
    company_phone bigint,
    company_verification character varying(255),
    country character varying(255),
    country_num_prefix bigint,
    fax bigint,
    is_verified bigint,
    official_company_address character varying(255),
    parent_member_id bigint
);


ALTER TABLE public.company OWNER TO erp;

--
-- Name: company_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.company_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2;


ALTER TABLE public.company_seq OWNER TO erp;

--
-- Name: company_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE public.company_seq OWNED BY public.company.company_id;


--
-- Name: contact; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.contact (
    contact_id bigint NOT NULL,
    company_id bigint,
    contact_email character varying(255),
    contact_first_name character varying(255),
    contact_last_name character varying(255),
    contact_phone character varying(255),
    dob timestamp(6) without time zone
);


ALTER TABLE public.contact OWNER TO erp;

--
-- Name: contact_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.contact_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2;


ALTER TABLE public.contact_seq OWNER TO erp;

--
-- Name: contact_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE public.contact_seq OWNED BY public.contact.contact_id;


--
-- Name: contract; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.contract (
    id bigint NOT NULL,
    contract_comment character varying(255),
    contract_note character varying(255),
    contract_ref character varying(255),
    contract_signature character varying(255),
    contract_status bigint,
    contract_upload_docs character varying(255),
    create_time timestamp(6) without time zone,
    created_by bigint,
    deal_id bigint,
    modified_time timestamp(6) without time zone,
    total_value bigint,
    location_id bigint,
    contract_type bigint,
    parent_member_id bigint,
    paid_start_date timestamp(6) without time zone,
    paid_end_date timestamp(6) without time zone,
    phase_id bigint
);


ALTER TABLE public.contract OWNER TO erp;

--
-- Name: contract_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.contract_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2;


ALTER TABLE public.contract_id_seq OWNER TO erp;

--
-- Name: contract_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE public.contract_id_seq OWNED BY public.contract.id;


--
-- Name: contract_item; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.contract_item (
    contract_item_id bigint NOT NULL,
    bonus_credit bigint,
    bonus_hours bigint,
    contract_id bigint,
    contract_item_value bigint,
    contract_period bigint,
    curr_bonus_hours bigint,
    is_recurring bigint,
    lease_term bigint,
    monthly_fee bigint,
    num_free_weeks bigint,
    paid_end_date timestamp(6) without time zone,
    paid_start_date timestamp(6) without time zone,
    product_category character varying(255),
    product_id bigint,
    week_day bigint
);


ALTER TABLE public.contract_item OWNER TO erp;

--
-- Name: contract_item_contract_item_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.contract_item_contract_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.contract_item_contract_item_id_seq OWNER TO erp;

--
-- Name: contract_item_contract_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE public.contract_item_contract_item_id_seq OWNED BY public.contract_item.contract_item_id;


--
-- Name: crm_booking; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.crm_booking (
    booking_id bigint NOT NULL,
    add_on_product_id bigint,
    base_price bigint,
    book_by bigint,
    booker_type bigint,
    booking_end_time timestamp(6) without time zone,
    booking_ref character varying(255),
    booking_start_time timestamp(6) without time zone,
    booking_title character varying(255),
    create_time timestamp(6) without time zone,
    debitted_price bigint,
    member_id bigint,
    product_id bigint,
    recurring_end_date timestamp(6) without time zone,
    week_day bigint,
    week_num bigint
);


ALTER TABLE public.crm_booking OWNER TO erp;

--
-- Name: crm_booking_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.crm_booking_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.crm_booking_seq OWNER TO erp;

--
-- Name: crm_deal; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.crm_deal (
    deal_id bigint NOT NULL,
    booking_meeting_id bigint,
    contact_id bigint,
    contract_id bigint,
    create_date timestamp(6) without time zone,
    create_user_id character varying(255),
    deal_budget bigint,
    deal_note character varying(255),
    deal_priority_level bigint,
    deal_ref character varying(255),
    deal_score bigint,
    deal_status bigint,
    deal_terminated_type bigint,
    deal_uploads_path character varying(255),
    lead_id bigint,
    lead_score bigint,
    lead_source_id bigint,
    phase_id bigint,
    terminated_deal_reason character varying(255),
    user_id bigint,
    user_id2 bigint,
    user_id3 bigint,
    user_id4 bigint
);


ALTER TABLE public.crm_deal OWNER TO erp;

--
-- Name: crm_deal_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.crm_deal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.crm_deal_seq OWNER TO erp;

--
-- Name: crm_lead; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.crm_lead (
    lead_id bigint NOT NULL,
    booking_meeting_id bigint,
    contact_id bigint,
    create_datetime timestamp(6) without time zone,
    create_user_id bigint,
    lead_budget bigint,
    lead_note character varying(255),
    lead_ref character varying(255),
    lead_score bigint,
    lead_source_id bigint,
    lead_status bigint,
    lead_terminated_type bigint,
    lead_type bigint,
    phase_id bigint,
    priority_level bigint,
    terminated_lead_reason character varying(255),
    user_id bigint,
    user_id2 bigint
);


ALTER TABLE public.crm_lead OWNER TO erp;

--
-- Name: crm_lead_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.crm_lead_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.crm_lead_seq OWNER TO erp;

--
-- Name: crm_phase; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.crm_phase (
    phase_id bigint NOT NULL,
    phase_name character varying(255),
    phase_order bigint,
    phase_status bigint,
    phase_type bigint
);


ALTER TABLE public.crm_phase OWNER TO erp;

--
-- Name: crm_phase_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.crm_phase_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.crm_phase_seq OWNER TO erp;

--
-- Name: ct_access; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.ct_access (
    access_id bigint NOT NULL,
    access_end_date timestamp(6) without time zone,
    access_start_date timestamp(6) without time zone,
    member_id bigint,
    product_id bigint
);


ALTER TABLE public.ct_access OWNER TO erp;

--
-- Name: ct_access_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.ct_access_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.ct_access_seq OWNER TO erp;

--
-- Name: date; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.date (
    now timestamp(6) with time zone
);


ALTER TABLE public.date OWNER TO erp;

--
-- Name: inventory_calendar; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.inventory_calendar (
    product_id bigint,
    booking_status bigint,
    contract_id bigint,
    create_date timestamp(6) without time zone,
    create_user_id bigint,
    from_date timestamp(6) without time zone,
    inventory_calendar_id bigint NOT NULL,
    modified_date timestamp(6) without time zone,
    parent_product_id bigint,
    product_use_status bigint,
    to_date timestamp(6) without time zone
);


ALTER TABLE public.inventory_calendar OWNER TO erp;

--
-- Name: inventory_calendar_inventory_calendar_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.inventory_calendar_inventory_calendar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2;


ALTER TABLE public.inventory_calendar_inventory_calendar_id_seq OWNER TO erp;

--
-- Name: inventory_calendar_inventory_calendar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE public.inventory_calendar_inventory_calendar_id_seq OWNED BY public.inventory_calendar.inventory_calendar_id;


--
-- Name: lead_source; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.lead_source (
    id bigint NOT NULL,
    lead_source_company_name character varying(255),
    lead_source_type character varying(255),
    member_id bigint,
    user_id bigint
);


ALTER TABLE public.lead_source OWNER TO erp;

--
-- Name: location_location_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.location_location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;



ALTER TABLE public.location_location_id_seq OWNER TO erp;

--
-- Name: location; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.location (
    location_id bigint DEFAULT nextval('public.location_location_id_seq'::regclass) NOT NULL,
    address character varying(255),
    close_time timestamp(6) without time zone,
    location_name character varying(255),
    open_time timestamp(6) without time zone,
    location_code character varying(255)
);


ALTER TABLE public.location OWNER TO erp;

--
-- Name: member_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.member_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.member_seq OWNER TO erp;

--
-- Name: member; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.member (
    member_id bigint DEFAULT nextval('public.member_seq'::regclass) NOT NULL,
    check_ip bigint,
    company_id bigint,
    create_date timestamp(6) without time zone,
    created_by bigint,
    dob timestamp(6) without time zone,
    first_name character varying(255),
    gender character varying(255),
    identity_code character varying(255),
    identity_type character varying(255),
    ip character varying(255),
    key_fob_id character varying(255),
    last_change_password timestamp(6) without time zone,
    last_lock_date timestamp(6) without time zone,
    last_name character varying(255),
    login_failure_count bigint,
    mem_postal_address character varying(255),
    member_email character varying(255),
    member_longro character varying(255),
    member_status bigint,
    member_type bigint,
    parent_member_id bigint,
    password character varying(255),
    physical_key_code character varying(255),
    valid_from timestamp(6) without time zone,
    valid_to timestamp(6) without time zone
);


ALTER TABLE public.member OWNER TO erp;

--
-- Name: product_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.product_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.product_seq OWNER TO erp;

--
-- Name: product; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.product (
    product_id bigint DEFAULT nextval('public.product_seq'::regclass) NOT NULL,
    create_date timestamp(6) without time zone,
    create_user_id bigint,
    modified_date timestamp(6) without time zone,
    parent_product_id bigint,
    product_bonus_credit bigint,
    product_bonus_hours bigint,
    product_capacity bigint,
    product_category character varying(255),
    product_code character varying(255),
    product_description character varying(255),
    product_hourly_fee double precision,
    product_image character varying(255),
    product_location character varying(255),
    product_monthly_fee double precision,
    product_name character varying(255),
    product_quantity double precision,
    product_rate double precision,
    product_size bigint,
    product_type character varying(255),
    product_unit double precision,
    product_value double precision,
    product_weekly_fee double precision,
    product_workstation bigint
);


ALTER TABLE public.product OWNER TO erp;

--
-- Name: product_category_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.product_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_category_id_seq OWNER TO erp;

--
-- Name: product_category; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.product_category (
    id bigint DEFAULT nextval('public.product_category_id_seq'::regclass) NOT NULL,
    product_category_code character varying(255) NOT NULL,
    product_category_name character varying(255)
);


ALTER TABLE public.product_category OWNER TO erp;

--
-- Name: product_type_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.product_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_type_id_seq OWNER TO erp;

--
-- Name: product_type; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.product_type (
    id bigint DEFAULT nextval('public.product_type_id_seq'::regclass) NOT NULL,
    product_type_code character varying(255) NOT NULL,
    product_type_name character varying(255)
);


ALTER TABLE public.product_type OWNER TO erp;

--
-- Name: product_type_category; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.product_type_category (
    product_category_id bigint NOT NULL,
    product_type_id bigint NOT NULL
);


ALTER TABLE public.product_type_category OWNER TO erp;

--
-- Name: stock_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.stock_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.stock_seq OWNER TO erp;

--
-- Name: stock; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.stock (
    stock_id bigint DEFAULT nextval('public.stock_seq'::regclass) NOT NULL,
    create_date timestamp(6) without time zone,
    create_user_id bigint,
    modified_date timestamp(6) without time zone,
    stock_category character varying(255),
    stock_code character varying(255),
    stock_description character varying(255),
    stock_image character varying(255),
    stock_init_quatity bigint,
    stock_location character varying(255),
    stock_name character varying(255),
    stock_parent_id bigint,
    stock_quantity bigint,
    stock_status bigint,
    stock_type character varying(255),
    stock_unit bigint,
    stock_value double precision
);


ALTER TABLE public.stock OWNER TO erp;

--
-- Name: sys_objects_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.sys_objects_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.sys_objects_seq OWNER TO erp;

--
-- Name: sys_objects; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.sys_objects (
    object_id bigint DEFAULT nextval('public.sys_objects_seq'::regclass) NOT NULL,
    create_date timestamp(6) without time zone,
    create_user character varying(255),
    description character varying(255),
    object_code character varying(255),
    object_name character varying(255),
    object_type_id bigint,
    object_url character varying(255),
    ord bigint,
    parent_id bigint,
    path_img character varying(255),
    status bigint
);


ALTER TABLE public.sys_objects OWNER TO erp;

--
-- Name: sys_roles_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.sys_roles_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.sys_roles_seq OWNER TO erp;

--
-- Name: sys_role; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.sys_role (
    role_id bigint DEFAULT nextval('public.sys_roles_seq'::regclass) NOT NULL,
    create_date timestamp(6) without time zone,
    create_user character varying(255),
    description character varying(255),
    role_code character varying(255),
    role_name character varying(255),
    status bigint
);


ALTER TABLE public.sys_role OWNER TO erp;

--
-- Name: sys_role_object; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.sys_role_object (
    role_id bigint NOT NULL,
    is_active bigint,
    object_id bigint
);


ALTER TABLE public.sys_role_object OWNER TO erp;

--
-- Name: sys_role_user; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.sys_role_user (
    role_id bigint NOT NULL,
    is_active bigint,
    user_id bigint
);


ALTER TABLE public.sys_role_user OWNER TO erp;

--
-- Name: sys_user; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.sys_user (
    user_id bigint NOT NULL,
    check_ip bigint,
    create_date timestamp(6) without time zone,
    create_user_id character varying(255),
    date_of_birth timestamp(6) without time zone,
    description character varying(255),
    email character varying(255),
    first_name character varying(255),
    gender bigint,
    home_phone character varying(255),
    identity_code character varying(255),
    identity_type bigint,
    ip_address character varying(255),
    key_fob_id character varying(255),
    last_change_password timestamp(6) without time zone,
    last_lock_date timestamp(6) without time zone,
    last_name character varying(255),
    location_id bigint,
    login_failure_count bigint,
    mobile_phone character varying(255),
    password character varying(255),
    status bigint,
    valid_from timestamp(6) without time zone,
    valid_to timestamp(6) without time zone
);


ALTER TABLE public.sys_user OWNER TO erp;

--
-- Name: sys_user_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.sys_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.sys_user_seq OWNER TO erp;

--
-- Name: template_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.template_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.template_seq OWNER TO erp;

--
-- Name: template; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.template (
    template_id bigint DEFAULT nextval('public.template_seq'::regclass) NOT NULL,
    template_name character varying(255),
    template_path character varying(255),
    template_type character varying(255)
);


ALTER TABLE public.template OWNER TO erp;

--
-- Name: test; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.test (
    id integer NOT NULL
);


ALTER TABLE public.test OWNER TO erp;

--
-- Name: work_at_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE public.work_at_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 2
    CYCLE;


ALTER TABLE public.work_at_seq OWNER TO erp;

--
-- Name: work_at; Type: TABLE; Schema: public; Owner: erp
--

CREATE TABLE public.work_at (
    work_id bigint DEFAULT nextval('public.work_at_seq'::regclass) NOT NULL,
    location_id bigint,
    member_id bigint,
    modified_work_status_by bigint,
    modified_work_status_time timestamp(6) without time zone,
    product_id bigint,
    wokr_status bigint,
    work_end_date timestamp(6) without time zone,
    work_start_date timestamp(6) without time zone
);


ALTER TABLE public.work_at OWNER TO erp;

--
-- Name: company company_id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.company ALTER COLUMN company_id SET DEFAULT nextval('public.company_seq'::regclass);


--
-- Name: contact contact_id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.contact ALTER COLUMN contact_id SET DEFAULT nextval('public.contact_seq'::regclass);


--
-- Name: contract id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.contract ALTER COLUMN id SET DEFAULT nextval('public.contract_id_seq'::regclass);


--
-- Name: contract_item contract_item_id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.contract_item ALTER COLUMN contract_item_id SET DEFAULT nextval('public.contract_item_contract_item_id_seq'::regclass);


--
-- Name: inventory_calendar inventory_calendar_id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.inventory_calendar ALTER COLUMN inventory_calendar_id SET DEFAULT nextval('public.inventory_calendar_inventory_calendar_id_seq'::regclass);


--
-- Data for Name: action_audit; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.action_audit (audit_id, actor_type, actory_id, audit_type_id, create_date, description, object_id, object_type_id, server_ip, source_id, user_id) FROM stdin;
\.


--
-- Data for Name: attach_document; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.attach_document (attach_id, document_path, source_id, document_name, table_name, create_date, create_user_id) FROM stdin;
\.


--
-- Data for Name: audit_detail; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.audit_detail (id, audit_id, col_name, create_date, new_name, old_name, table_name, record_id) FROM stdin;
\.


--
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.company (company_id, abn, acn, company_code, company_email, company_full_name, company_intro, company_landline_phone, company_phone, company_verification, country, country_num_prefix, fax, is_verified, official_company_address, parent_member_id) FROM stdin;
4	4	anhem	2009	toprate2@gmail.com	Emed an	\N	\N	\N	\N	\N	\N	\N	\N	\N	2
2	2	haha	2019	top@gmail.com	Toprate ha	\N	\N	\N	\N	\N	\N	\N	\N	\N	6
\.


--
-- Data for Name: contact; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.contact (contact_id, company_id, contact_email, contact_first_name, contact_last_name, contact_phone, dob) FROM stdin;
2	2	jonh@gmail.com	jonh	fill	099818627	\N
3	4	mart@gmail.com	mart	anh	098282632	\N
5	2	tony@toprate.io	Tony	Stark	000111222	\N
4	\N	hitl@mail.com	hitl	kmad	062628262	\N
\.


--
-- Data for Name: contract; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.contract (id, contract_comment, contract_note, contract_ref, contract_signature, contract_status, contract_upload_docs, create_time, created_by, deal_id, modified_time, total_value, location_id, contract_type, parent_member_id, paid_start_date, paid_end_date, phase_id) FROM stdin;
24	anh em chung doi save	\N	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	1	6	2019-10-06 07:00:00	2019-10-06 07:00:00	\N
26	anh em chung doi save 66	\N	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	1	6	2019-10-06 07:00:00	2019-10-06 07:00:00	\N
25	anh em chung doi 25	\N	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	1	6	2019-10-06 07:00:00	2019-10-06 07:00:00	\N
15	abc 	\N	\N	\N	\N	\N	\N	2	\N	\N	\N	29	\N	2	\N	\N	\N
14	abc 	\N	\N	\N	\N	\N	\N	2	\N	\N	\N	29	\N	2	\N	\N	\N
50	Created by who??	Contract 	not found	\N	\N	\N	\N	\N	50	\N	\N	28	\N	1	\N	\N	1
3	abc 	Contract  #1	\N	\N	2	\N	\N	2	\N	\N	\N	\N	\N	2	\N	\N	3
10	abc 	Contract  2	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	\N	2	\N	\N	1
11	anh em chung doi 123	Contract 3	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	1	6	2019-10-06 07:00:00	2019-10-06 07:00:00	2
13	abc 	Contract 4	\N	anamamaj	\N	\N	\N	2	\N	\N	\N	\N	\N	6	\N	\N	1
18	anh em chung doi	Contract 5	\N	nahanh	2	\N	\N	\N	\N	\N	\N	28	\N	2	2019-11-19 15:31:14	2019-11-11 15:31:19	2
17	abc 	Contract 6	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	\N	2	\N	\N	3
16	abc 	Contract 7	\N	aananaj	\N	\N	\N	2	\N	\N	\N	\N	\N	6	\N	\N	1
21	abc nhnahna h	Contract 8	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	\N	6	\N	\N	2
22	abc nhnahna h	Contract 9	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	\N	6	\N	\N	1
20	abc 	Contract 0	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	\N	2	\N	\N	4
12	abc 	\N	\N	\N	\N	\N	\N	2	\N	\N	\N	\N	\N	2	\N	\N	\N
\.


--
-- Data for Name: contract_item; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.contract_item (contract_item_id, bonus_credit, bonus_hours, contract_id, contract_item_value, contract_period, curr_bonus_hours, is_recurring, lease_term, monthly_fee, num_free_weeks, paid_end_date, paid_start_date, product_category, product_id, week_day) FROM stdin;
2	1	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
10	44	\N	17	4	\N	\N	2	\N	\N	22	2019-10-05 07:00:00	2019-10-05 07:00:00	cua so rong	444	\N
16	44	\N	18	4	\N	\N	2	\N	\N	22	2019-10-05 07:00:00	2019-10-05 07:00:00	hoa dong co dai	1234556	\N
28	44	\N	10	4	\N	\N	2	\N	\N	15	2019-10-05 07:00:00	2019-10-05 07:00:00	khong co gì ca	17	\N
29	44	\N	10	4	\N	\N	2	\N	\N	15	2019-10-05 07:00:00	2019-10-05 07:00:00	khong co gì dau naha ca	13	\N
14	44	\N	18	4	\N	\N	2	\N	\N	15	2019-10-05 07:00:00	2019-10-05 07:00:00	khong co gì ca	18	\N
15	44	\N	18	4	\N	\N	2	\N	\N	15	2019-10-05 07:00:00	2019-10-05 07:00:00	khong co gì dau naha ca	14	\N
32	44	\N	20	4	1	\N	2	\N	\N	22	2019-10-05 07:00:00	2019-10-05 07:00:00	VF	444	\N
33	44	\N	21	4	1	\N	2	\N	\N	22	2019-10-05 07:00:00	2019-10-05 07:00:00	VF	444	\N
36	44	\N	22	4	1	\N	2	\N	\N	22	2019-10-05 07:00:00	2019-10-05 07:00:00	VF	444	\N
37	44	\N	22	4	1	\N	2	\N	\N	22	2019-10-05 07:00:00	2019-10-05 07:00:00	VF	555	\N
52	44	\N	11	4	\N	\N	2	\N	\N	15	\N	\N	khong co gì ca	17	\N
53	44	\N	11	4	\N	\N	2	\N	\N	15	\N	\N	khong co gì dau naha ca	13	\N
54	44	\N	11	4	\N	\N	2	\N	\N	22	\N	\N	hoa dong co dai	1234556	\N
55	44	\N	24	4	0	\N	2	\N	\N	15	\N	\N	khong co gì ca	17	\N
56	44	\N	24	4	0	\N	2	\N	\N	15	\N	\N	khong co gì dau naha ca	13	\N
60	44	\N	26	4	0	\N	2	\N	\N	15	\N	\N	khong co gì ca	17	\N
61	44	\N	26	4	0	\N	2	\N	\N	15	\N	\N	khong co gì dau naha ca	13	\N
62	44	\N	25	4	\N	\N	2	\N	\N	15	\N	\N	khong co gì ca	17	\N
63	44	\N	25	4	\N	\N	2	\N	\N	15	\N	\N	khong co gì dau naha ca	13	\N
64	44	\N	25	4	\N	\N	2	\N	\N	22	\N	\N	hoa dong co dai	1234556	\N
\.


--
-- Data for Name: crm_booking; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.crm_booking (booking_id, add_on_product_id, base_price, book_by, booker_type, booking_end_time, booking_ref, booking_start_time, booking_title, create_time, debitted_price, member_id, product_id, recurring_end_date, week_day, week_num) FROM stdin;
\.


--
-- Data for Name: crm_deal; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.crm_deal (deal_id, booking_meeting_id, contact_id, contract_id, create_date, create_user_id, deal_budget, deal_note, deal_priority_level, deal_ref, deal_score, deal_status, deal_terminated_type, deal_uploads_path, lead_id, lead_score, lead_source_id, phase_id, terminated_deal_reason, user_id, user_id2, user_id3, user_id4) FROM stdin;
50	\N	5	50	\N	\N	\N	\N	\N	ref\n	\N	1	\N	\N	\N	\N	\N	1	\N	\N	\N	\N	\N
\.


--
-- Data for Name: crm_lead; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.crm_lead (lead_id, booking_meeting_id, contact_id, create_datetime, create_user_id, lead_budget, lead_note, lead_ref, lead_score, lead_source_id, lead_status, lead_terminated_type, lead_type, phase_id, priority_level, terminated_lead_reason, user_id, user_id2) FROM stdin;
\.


--
-- Data for Name: crm_phase; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.crm_phase (phase_id, phase_name, phase_order, phase_status, phase_type) FROM stdin;
1	Contract Signed	1	1	2
2	Setup Payment	2	1	2
3	Issue Key	3	1	2
4	Health Check	4	1	2
5	Regular Member	5	1	2
7	Contract Made	20	1	0
8	Follow-Up	30	1	0
9	Meeting Arrange	40	1	0
6	Lead In	10	1	0
10	Won Lead	50	1	0
11	Lead Qualified	60	1	1
12	Proposal	70	1	1
13	Negotiation	80	1	1
14	Contract	90	1	1
15	Contract signed	100	1	1
\.


--
-- Data for Name: ct_access; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.ct_access (access_id, access_end_date, access_start_date, member_id, product_id) FROM stdin;
\.


--
-- Data for Name: date; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.date (now) FROM stdin;
2019-11-16 15:00:17.705204+00
\.


--
-- Data for Name: inventory_calendar; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.inventory_calendar (product_id, booking_status, contract_id, create_date, create_user_id, from_date, inventory_calendar_id, modified_date, parent_product_id, product_use_status, to_date) FROM stdin;
6	\N	18	\N	\N	2019-10-05 07:00:00	1	\N	\N	\N	2019-10-05 07:00:00
7	\N	18	\N	\N	2019-10-05 07:00:00	2	\N	\N	\N	2019-10-05 07:00:00
8	\N	18	\N	\N	2019-10-05 07:00:00	3	\N	\N	\N	2019-10-05 07:00:00
444	\N	22	\N	2	2019-10-05 07:00:00	27	\N	\N	\N	2019-10-05 07:00:00
555	\N	22	\N	2	2019-10-05 07:00:00	28	\N	\N	\N	2019-10-05 07:00:00
13	\N	11	\N	\N	\N	45	\N	\N	\N	\N
1234556	\N	11	\N	\N	\N	46	\N	\N	\N	\N
17	\N	24	\N	2	\N	47	\N	\N	\N	\N
13	\N	24	\N	2	\N	48	\N	\N	\N	\N
17	\N	26	\N	2	\N	52	\N	\N	\N	\N
13	\N	26	\N	2	\N	53	\N	\N	\N	\N
17	\N	25	\N	\N	\N	54	\N	\N	\N	\N
13	\N	25	\N	\N	\N	55	\N	\N	\N	\N
1234556	\N	25	\N	\N	\N	56	\N	\N	\N	\N
17	\N	11	2019-11-05 07:00:00	\N	\N	44	\N	\N	\N	\N
\.


--
-- Data for Name: lead_source; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.lead_source (id, lead_source_company_name, lead_source_type, member_id, user_id) FROM stdin;
\.


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.location (location_id, address, close_time, location_name, open_time, location_code) FROM stdin;
24  Address	2020-02-20 07:00:00	Demo modified Location	2019-01-01 07:00:00	\N
28	Address 1	2020-02-20 07:00:00	Demo Location 2	2019-01-15 07:00:00	Location code
29	Address 1	2020-02-20 07:00:00	Demo Location 2	2019-01-15 07:00:00	Location code
30	Address	2020-02-20 07:00:00	Demo Location	2019-01-01 07:00:00	\N
\.


--
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.member (member_id, check_ip, company_id, create_date, created_by, dob, first_name, gender, identity_code, identity_type, ip, key_fob_id, last_change_password, last_lock_date, last_name, login_failure_count, mem_postal_address, member_email, member_longro, member_status, member_type, parent_member_id, password, physical_key_code, valid_from, valid_to) FROM stdin;
1	\N	2	\N	\N	\N	Tony	Male	\N	\N	\N	\N	\N	\N	Stark	\N	\N	tonystark@toprate.io	\N	1	\N	2	\N	\N	\N	\N
2	\N	2	2019-11-13 11:03:39	2	\N	Jack	Male	\N	\N	\N	\N	\N	\N	Bill	\N	\N	billjack@gmail.com	\N	1	\N	\N	\N	\N	\N	\N
3	\N	2	2019-11-19 11:07:10	2	\N	gate	Male	\N	\N	\N	\N	\N	\N	Hero	\N	\N	hero@gmail.com	\N	1	\N	2	\N	\N	\N	\N
4	\N	4	2019-11-26 11:11:24	2	\N	Shoee	Female	\N	\N	\N	\N	\N	\N	suger	\N	\N	suger@gmail.com	\N	1	\N	6	\N	\N	\N	\N
5	\N	4	2019-11-21 10:14:55	3	\N	Dany	Female	\N	\N	\N	\N	\N	\N	Make	\N	\N	make@gmail.com	\N	1	\N	6	\N	\N	\N	\N
6	\N	4	2019-11-27 11:19:49	3	\N	Supper	Male	\N	\N	\N	\N	\N	\N	Hal	\N	\N	hel@toprate.io	\N	1	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.product (product_id, create_date, create_user_id, modified_date, parent_product_id, product_bonus_credit, product_bonus_hours, product_capacity, product_category, product_code, product_description, product_hourly_fee, product_image, product_location, product_monthly_fee, product_name, product_quantity, product_rate, product_size, product_type, product_unit, product_value, product_weekly_fee, product_workstation) FROM stdin;
1	2019-11-16 00:00:00	1	2019-11-16 00:00:00	\N	112	\N	\N	sp	product1	day la sp test	12	\N	HN	40	sp tt	9	1.5	\N	Loai 1	\N	\N	\N	\N
13	2019-11-16 00:00:00	\N	2019-12-16 00:00:00	\N	\N	\N	\N	sp2\n	sp2	\N	\N	\N	\N	\N	Product name 1	\N	\N	\N	\N	\N	\N	\N	\N
17	2019-11-16 00:00:00	\N	2019-12-16 00:00:00	1	\N	\N	\N	sp3	sp3	\N	\N	\N	\N	\N	Product name 2	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.product_category (id, product_category_code, product_category_name) FROM stdin;
1	Code 1	Category 1
2	Code 2	Category 2
3	Code 3	Category 3
4	Code 4	Category 4
5	Code 5	Category 5
6	Code 6	Category 6
\.


--
-- Data for Name: product_type; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.product_type (id, product_type_code, product_type_name) FROM stdin;
1	Type Code 1	Type 1
2	Type Code 2	Type 2
3	Type Code 3	Type 3
4	Type Code 4	Type 4
5	Type Code 5	Type 5
6	Type Code 6	Type 6
7	Type Code 7	Type 7
8	Type Code 8	Type 8
9	Code type 1	Type 1
\.


--
-- Data for Name: product_type_category; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.product_type_category (product_category_id, product_type_id) FROM stdin;
1	1
2	2
3	3
4	4
5	5
1	6
1	1
1	2
2	3
3	1
2	1
\.


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.stock (stock_id, create_date, create_user_id, modified_date, stock_category, stock_code, stock_description, stock_image, stock_init_quatity, stock_location, stock_name, stock_parent_id, stock_quantity, stock_status, stock_type, stock_unit, stock_value) FROM stdin;
\.


--
-- Data for Name: sys_objects; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.sys_objects (object_id, create_date, create_user, description, object_code, object_name, object_type_id, object_url, ord, parent_id, path_img, status) FROM stdin;
\.


--
-- Data for Name: sys_role; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.sys_role (role_id, create_date, create_user, description, role_code, role_name, status) FROM stdin;
\.


--
-- Data for Name: sys_role_object; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.sys_role_object (role_id, is_active, object_id) FROM stdin;
\.


--
-- Data for Name: sys_role_user; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.sys_role_user (role_id, is_active, user_id) FROM stdin;
\.


--
-- Data for Name: sys_user; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.sys_user (user_id, check_ip, create_date, create_user_id, date_of_birth, description, email, first_name, gender, home_phone, identity_code, identity_type, ip_address, key_fob_id, last_change_password, last_lock_date, last_name, location_id, login_failure_count, mobile_phone, password, status, valid_from, valid_to) FROM stdin;
1	0	2019-11-15 11:47:24.143	string	\N		toanbd@toprate.io	Toan	0			0		string	2019-11-15 12:26:12.85758	\N	Bùi	\N	0	0974127162	$2a$10$CVAB.d/0PO3J9v2MQ4tagODI4jyraRnD1rwcDNaeFuVaFW7Bko2zS	1	\N	\N
2	2	2019-11-14 13:44:46.202	\N	\N	\N	nguyenhoang@gmail.com	Hoang	\N	\N	\N	\N	\N	\N	\N	\N	Nguyen	\N	\N	0781736726	$2a$10$npZ6Hu9/cIbHMw5N7boq/eaJn4Zv/Kfv7K8JFK/MXb8F.EErr059O	1	\N	\N
\.


--
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.template (template_id, template_name, template_path, template_type) FROM stdin;
\.


--
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.test (id) FROM stdin;
\.


--
-- Data for Name: work_at; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY public.work_at (work_id, location_id, member_id, modified_work_status_by, modified_work_status_time, product_id, wokr_status, work_end_date, work_start_date) FROM stdin;
\.


--
-- Name: action_audit_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.action_audit_seq', 2, false);


--
-- Name: audit_detail_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.audit_detail_seq', 2, false);


--
-- Name: company_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.company_seq', 5, true);


--
-- Name: contact_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.contact_seq', 5, true);


--
-- Name: contract_id_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.contract_id_seq', 27, true);


--
-- Name: contract_item_contract_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.contract_item_contract_item_id_seq', 65, true);


--
-- Name: crm_booking_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.crm_booking_seq', 2, false);


--
-- Name: crm_deal_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.crm_deal_seq', 2, false);


--
-- Name: crm_lead_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.crm_lead_seq', 2, false);


--
-- Name: crm_phase_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.crm_phase_seq', 2, false);


--
-- Name: ct_access_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.ct_access_seq', 2, false);


--
-- Name: inventory_calendar_inventory_calendar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.inventory_calendar_inventory_calendar_id_seq', 57, true);


--
-- Name: location_location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.location_location_id_seq', 31, true);


--
-- Name: member_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.member_seq', 7, true);


--
-- Name: product_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.product_category_id_seq', 1, false);


--
-- Name: product_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.product_seq', 2, false);


--
-- Name: product_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.product_type_id_seq', 9, true);


--
-- Name: stock_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.stock_seq', 2, false);


--
-- Name: sys_objects_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.sys_objects_seq', 2, false);


--
-- Name: sys_roles_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.sys_roles_seq', 2, false);


--
-- Name: sys_user_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.sys_user_seq', 3, true);


--
-- Name: template_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.template_seq', 2, false);


--
-- Name: work_at_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('public.work_at_seq', 2, false);


--
-- Name: action_audit action_audit_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.action_audit
    ADD CONSTRAINT action_audit_pkey PRIMARY KEY (audit_id);


--
-- Name: attach_document attach_document_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.attach_document
    ADD CONSTRAINT attach_document_pkey PRIMARY KEY (attach_id);


--
-- Name: audit_detail audit_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.audit_detail
    ADD CONSTRAINT audit_detail_pkey PRIMARY KEY (id);


--
-- Name: company company_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (company_id);


--
-- Name: contact contact_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (contact_id);


--
-- Name: contract_item contract_item_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.contract_item
    ADD CONSTRAINT contract_item_pkey PRIMARY KEY (contract_item_id);


--
-- Name: contract contract_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.contract
    ADD CONSTRAINT contract_pkey PRIMARY KEY (id);


--
-- Name: crm_booking crm_booking_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.crm_booking
    ADD CONSTRAINT crm_booking_pkey PRIMARY KEY (booking_id);


--
-- Name: crm_deal crm_deal_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.crm_deal
    ADD CONSTRAINT crm_deal_pkey PRIMARY KEY (deal_id);


--
-- Name: crm_lead crm_lead_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.crm_lead
    ADD CONSTRAINT crm_lead_pkey PRIMARY KEY (lead_id);


--
-- Name: crm_phase crm_phase_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.crm_phase
    ADD CONSTRAINT crm_phase_pkey PRIMARY KEY (phase_id);


--
-- Name: ct_access ct_access_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.ct_access
    ADD CONSTRAINT ct_access_pkey PRIMARY KEY (access_id);


--
-- Name: inventory_calendar inventory_calendar_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.inventory_calendar
    ADD CONSTRAINT inventory_calendar_pkey PRIMARY KEY (inventory_calendar_id);


--
-- Name: lead_source lead_source_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.lead_source
    ADD CONSTRAINT lead_source_pkey PRIMARY KEY (id);


--
-- Name: location location_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (location_id);


--
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (member_id);


--
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- Name: product_type product_type_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.product_type
    ADD CONSTRAINT product_type_pkey PRIMARY KEY (id);


--
-- Name: stock stock_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (stock_id);


--
-- Name: sys_objects sys_objects_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.sys_objects
    ADD CONSTRAINT sys_objects_pkey PRIMARY KEY (object_id);


--
-- Name: sys_role_object sys_role_object_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.sys_role_object
    ADD CONSTRAINT sys_role_object_pkey PRIMARY KEY (role_id);


--
-- Name: sys_role sys_role_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.sys_role
    ADD CONSTRAINT sys_role_pkey PRIMARY KEY (role_id);


--
-- Name: sys_role_user sys_role_user_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.sys_role_user
    ADD CONSTRAINT sys_role_user_pkey PRIMARY KEY (role_id);


--
-- Name: sys_user sys_user_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.sys_user
    ADD CONSTRAINT sys_user_pkey PRIMARY KEY (user_id);


--
-- Name: template template_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.template
    ADD CONSTRAINT template_pkey PRIMARY KEY (template_id);


--
-- Name: test test_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (id);


--
-- Name: work_at work_at_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.work_at
    ADD CONSTRAINT work_at_pkey PRIMARY KEY (work_id);


--
-- PostgreSQL database dump complete
--

