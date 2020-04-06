from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES
from apiTests.string_helper import randCapitalised, randomString, random_date, randTime
import random
import time


for i in range(0, 500):
    try:
        cur = conn.cursor()
        query = """
        insert into crm_lead (
            lead_id,
            booking_meeting_id,
            contact_id,
            create_datetime,
            create_user_id,
            lead_budget,
            lead_note,
            lead_ref,
            lead_score,
            lead_source_id,
            lead_status,
            lead_terminated_type,
            lead_type,
            phase_id,
            priority_level,
            terminated_lead_reason,
            user_id,
            user_id2 
        )
        values (
            NEXTVAL('contact_contact_id_seq'), 
            %s, 
            %s, 
            %s, 
            %s, 
            %s, 
            %s
        );
        """
        company_id = random.randint(1, 500)
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
