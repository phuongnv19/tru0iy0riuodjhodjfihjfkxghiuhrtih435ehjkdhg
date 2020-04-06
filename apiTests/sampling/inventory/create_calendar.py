from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES, COUNTRIES
from apiTests.string_helper import randCapitalised, randomString, random_date, randTime, randPhoneNum, gen_datetime
import random
import time
import datetime
from datetime import timedelta

for i in range(0, 500):
    try:
        cur = conn.cursor()
        query = """
        insert into inventory_calendar (
            inventory_calendar_id,
            product_id,
            booking_status,
            contract_id,
            create_date,
            create_user_id,
            from_date,
            modified_date,
            parent_product_id,
            product_use_status,
            to_date 
        )
        values (
            NEXTVAL('inventory_calendar_inventory_calendar_id_seq'), 
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
        
        product_id = random.randint(1, 500)
        booking_status = None
        contract_id = None
        create_date = randTime()
        create_user_id = random.randint(1, 2)
        from_date = gen_datetime()
        modified_date = randTime()
        parent_product_id = None
        product_use_status = random.randint(0, 2)
        to_date = from_date + timedelta(hours=1)

        cur.execute(query, [\
            product_id, \
            booking_status, \
            contract_id, \
            create_date, \
            create_user_id, \
            str(from_date), \
            modified_date, \
            parent_product_id, \
            product_use_status, \
            str(to_date), \
        ])
        # conn.commit()
        print(f"i = {i}")
        # time.sleep(3)
    except Exception as e:
        print('Cannot execute query: ', e)
conn.commit()

print("CREATED Statuses")
