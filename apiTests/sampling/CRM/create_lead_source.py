from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES, COUNTRIES
from apiTests.string_helper import randCapitalised, randomString, random_date, randTime, randPhoneNum
import random
import time


for i in range(0, 5):
    try:
        cur = conn.cursor()
        query = """
        insert into lead_source (
            id,
            lead_source_company_name,
            lead_source_type,
            member_id,
            user_id 
        )
        values (
            NEXTVAL('lead_source_id_seq'), 
            %s, 
            %s, 
            %s, 
            %s 
        );
        """
        
        lead_source_company_name = fake.name() + ' ' + NAMES[random.randint(0, len(NAMES) - 1)] + ' Pty Ltd'
        lead_source_type = random.randint(0, 5)
        member_id = None
        user_id = random.randint(600, 700)
        
        cur.execute(query, [\
            lead_source_company_name, \
            lead_source_type, \
            member_id, \
            user_id, 
        ])
        # conn.commit()
        print(f"i = {i}")
        # time.sleep(3)
    except Exception as e:
        print('Cannot execute query: ', e)
conn.commit()

print("CREATED Lead Sources")
