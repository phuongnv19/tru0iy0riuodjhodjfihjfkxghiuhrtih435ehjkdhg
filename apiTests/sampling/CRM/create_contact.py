from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES
from apiTests.string_helper import randCapitalised, randomString, random_date, randTime
import random
import time


for i in range(0, 500):
    try:
        cur = conn.cursor()
        query = """
        insert into contact (
            contact_id,
            company_id,
            contact_email,
            contact_first_name,
            contact_last_name,
            contact_phone,
            dob
        )
        values (
            NEXTVAL('contact_seq'), 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s
        );
        """
        company_id = random.randint(40, 500)
        contact_email = fake.email()
        contact_first_name = NAMES[random.randint(0, len(NAMES) - 1)]
        contact_last_name = NAMES[random.randint(0, len(NAMES) - 1)]
        contact_phone = random.randint(10000000, 99900000)
        dob = randTime()
        # print("Query = ", query)
        cur.execute(query, [\
            company_id, \
            contact_email, \
            contact_first_name, \
            contact_last_name, \
            contact_phone, \
            dob, \
        ])
        # conn.commit()
        print(f"i = {i}")
        # time.sleep(3)
    except Exception as e:
        print('Cannot execute query: ', e)
conn.commit()

print("CREATED CONTACTS")
