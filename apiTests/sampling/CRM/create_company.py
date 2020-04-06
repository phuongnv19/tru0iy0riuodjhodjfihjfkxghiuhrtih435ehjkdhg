from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES, COUNTRIES
from apiTests.string_helper import randCapitalised, randomString, random_date, randTime, randPhoneNum
import random
import time


for i in range(0, 500):
    try:
        cur = conn.cursor()
        query = """
        insert into company (
            company_id,
            abn,
            acn,
            company_code,
            company_email,
            company_full_name,
            company_intro,
            company_landline_phone,
            company_phone,
            company_verification,
            country,
            country_num_prefix,
            fax,
            is_verified,
            official_company_address,
            parent_member_id 
        )
        values (
            NEXTVAL('company_company_id_seq'), 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s 
        );
        """
        
        abn = randPhoneNum()
        acn = randCapitalised(10)
        company_code = randCapitalised(10)
        company_email = fake.email()
        company_full_name = fake.name() + ' ' + NAMES[random.randint(0, len(NAMES) - 1)] + ' Pty Ltd'
        company_intro = fake.text()
        company_landline_phone = randPhoneNum()
        company_phone = randPhoneNum()
        company_verification = None
        country = COUNTRIES[random.randint(0, len(COUNTRIES) - 1)].get('name')
        country_num_prefix = None
        fax = randPhoneNum()
        is_verified = random.randint(0, 1)
        official_company_address = fake.address()
        parent_member_id = random.randint(1, 500)
        # print("Query = ", query)
        cur.execute(query, [\
            abn, \
            acn, \
            company_code, \
            company_email, \
            company_full_name, \
            company_intro, \
            company_landline_phone, \
            company_phone, \
            company_verification, \
            country, \
            country_num_prefix, \
            fax, \
            is_verified, \
            official_company_address, \
            parent_member_id, \
        ])
        # conn.commit()
        print(f"i = {i}")
        # time.sleep(3)
    except Exception as e:
        print('Cannot execute query: ', e)
conn.commit()

print("CREATED COMPANIES")
