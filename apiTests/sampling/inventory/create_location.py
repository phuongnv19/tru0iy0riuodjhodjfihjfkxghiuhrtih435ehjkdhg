from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES
from apiTests.string_helper import randCapitalised, randomString, random_date, randTime
import random
import time


for i in range(0, 100):
    try:
        cur = conn.cursor()
        query = """
        insert into location (
            location_id,
            address,
            close_time,
            location_name,
            open_time,
            location_code 
        )
        values (
            NEXTVAL('location_location_id_seq'), 
            %s, 
            %s, 
            %s, 
            %s, 
            %s
        );
        """
        address = fake.address()
        close_time = randTime()
        location_name = NAMES[random.randint(0, len(NAMES) - 1)] + ' ' + NAMES[random.randint(0, len(NAMES) - 1)]
        open_time = randTime()
        location_code = randCapitalised(6)

        # print("Query = ", query)
        cur.execute(query, [\
            address, \
            close_time, \
            location_name, \
            open_time, \
            location_code, \
        ])
        # conn.commit()
        print(f"i = {i}")
        # time.sleep(3)
    except Exception as e:
        print('Cannot execute query: ', e)
conn.commit()

print("CREATED LOCATION")
