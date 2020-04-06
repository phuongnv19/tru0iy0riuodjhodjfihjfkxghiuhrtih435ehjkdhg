-- =========================================
-- TESTING DATA

-- USER 
insert into public.sys_user(check_ip, create_date, create_user_id, date_of_birth, description, email, first_name, gender, home_phone, identity_code, identity_type, ip_address, key_fob_id, last_change_password, last_lock_date, last_name, location_id, login_failure_count, mobile_phone, password, status, valid_from, valid_toe)
values                      ();




-- LOCATION
insert into public.location(address, close_time, location_name, open_time, location_code)
values ('123 ANDREW RD, NSW 2018', '2002-12-31 18:15:00', 'RANDWICK', '2002-12-31 09:30:00', 'RND');
insert into public.location(address, close_time, location_name, open_time, location_code)
values ('234 ANDREW RD, QLD 1234', '2002-12-31 18:30:00', 'BOTANY', '2002-12-31 07:00:00', 'BTO');
insert into public.location(address, close_time, location_name, open_time, location_code)
values ('12 JOHN RD, QLD 2000', '2002-12-31 18:30:00', 'CBD', '2002-12-31 07:00:00', 'CBD');
insert into public.location(address, close_time, location_name, open_time, location_code)
values ('56 BOTANY RD, SA 3000', '2002-12-31 18:30:00', 'BOTANY', '2002-12-31 07:00:00', 'STH');



-- COMPANY
insert into public.company(abn  , acn, company_code, company_email, company_full_name, company_intro, company_landline_phone, company_phone, company_verification, country, country_num_prefix, fax, is_verified, official_company_address )
values                     (89237489, 'EMY', 'LTM', 'tuan@benit.io', 'TOPRATE LTD', 'Finance', 043543532, NULL, NULL, 'Australia', '61', 5672495892, 0, '123 Sydney Opera House');
insert into public.company(abn  , acn, company_code, company_email, company_full_name, company_intro, company_landline_phone, company_phone, company_verification, country, country_num_prefix, fax, is_verified, official_company_address )
values                     (89237489, 'TYY', 'Security', 'admin@benit.io', 'WESTPAC', 'Insurance', 043532, NULL, NULL, 'Australia', '61', 934095892, 0, 'Level 8, Botany Rd');
insert into public.company(abn  , acn, company_code, company_email, company_full_name, company_intro, company_landline_phone, company_phone, company_verification, country, country_num_prefix, fax, is_verified, official_company_address )
values                     (89237489, 'EY', 'Logistics', 'tuanHO@benit.io', 'BENIT INC', 'Insurance',  3543532, NULL, NULL, 'USA', '01', 675095892, 1, 'Level 1, Botany Rd');
insert into public.company(abn  , acn, company_code, company_email, company_full_name, company_intro, company_landline_phone, company_phone, company_verification, country, country_num_prefix, fax, is_verified, official_company_address )
values                     (89237489, 'MMHJ', 'Logistics', 'sdfh@di.com', 'TOPRATE INC', 'Banking', 0423424234, NULL, NULL, 'USA', '01', 345344095892, 1, 'Level 1, Botany Rd');
insert into public.company(abn  , acn, company_code, company_email, company_full_name, company_intro, company_landline_phone, company_phone, company_verification, country, country_num_prefix, fax, is_verified, official_company_address )
values                     (89237489, 'Amazon', 'dsfgdf', 'ceo@toprate.com', 'ZOHO INC', 'Banking', 0252423424234, NULL, NULL, 'Australia', '61', 3453095892, 0, 'Level 1, Botany Rd');
insert into public.company(abn  , acn, company_code, company_email, company_full_name, company_intro, company_landline_phone, company_phone, company_verification, country, country_num_prefix, fax, is_verified, official_company_address )
values                     (89237489, 'AmaTon', 'Facebook', 'sdfh@di.com', 'WOOHOO', 'Insurance', 09043543532, NULL, NULL, 'Vietnam', '84', 34095892, 1, 'CBD 2000');
insert into public.company(abn  , acn, company_code, company_email, company_full_name, company_intro, company_landline_phone, company_phone, company_verification, country, country_num_prefix, fax, is_verified, official_company_address )
values                     (89237489, 'Dsgsdfg', 'Facebook', 'IOIO@di.com', 'ZOHO INC 2', 'Telecom', 0943532, NULL, NULL, 'Australia', '61', 934095892, 0, 'CBD 2000');






-- CONTACT 




--- MEMBER
insert into public.member(check_ip, company_id, create_date, created_by, dob, first_name, gender, identity_code, identity_type, ip, key_fob_id, last_change_password, last_lock_date, last_name, login_failure_count, mem_postal_address, member_email, member_longro, member_status, member_type, parent_member_id, password, physical_key_code, valid_from, valid_to)
values                   ();
