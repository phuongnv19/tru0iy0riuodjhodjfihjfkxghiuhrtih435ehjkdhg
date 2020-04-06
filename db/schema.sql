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
    company_full_name character varying(255) NOT NULL,
    company_intro character varying(255),
    company_landline_phone bigint,
    company_phone bigint,
    company_verification character varying(255),
    country character varying(255),
    country_num_prefix bigint,
    fax bigint,
    is_verified bigint,
    official_company_address character varying(255)
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
-- Name: action_audit action_audit_pkey; Type: CONSTRAINT; Schema: public; Owner: erp
--

ALTER TABLE ONLY public.action_audit
    ADD CONSTRAINT action_audit_pkey PRIMARY KEY (audit_id);


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

