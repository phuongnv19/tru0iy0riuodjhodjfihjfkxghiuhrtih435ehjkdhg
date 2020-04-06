# import os
# os.path.abspath('/Volumes/ANHTUAN/CAREER_DEV/ErP_prep/ERP_ANYTIME_OFFICE/api_admin/')
# import sys, inspect
# currentdir = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
# sys.path.insert(1,currentdir) 
# print(currentdir)

from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES
from apiTests.string_helper import randCapitalised, randomString
import random
import time



for i in range(0, 200):
    try:
        cur = conn.cursor()
        query = """
        insert into PRODUCT (
            product_id,
            create_date,
            create_user_id,
            modified_date,
            parent_product_id,
            product_bonus_credit,
            product_bonus_hours,
            product_capacity,
            product_category_id,
            product_code,
            product_description,
            product_hourly_fee,
            product_image,
            product_location,
            product_monthly_fee,
            product_name,
            product_quantity,
            product_rate,
            product_size,
            product_type,
            product_unit,
            product_value,
            product_weekly_fee,
            product_workstation,
            location_id,
            product_type_id 
        )
        values (
            NEXTVAL('product_id_seq'), 
            NULL, 
            %s, 
            NULL, 
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
        location_id = random.randint(32, 52)
        create_user_id = random.randint(1, 200)
        parent_product_id = random.randint(100, 200)
        product_bonus_credit = random.randint(1, 30)
        product_bonus_hours = random.randint(1, 20)
        product_capacity = random.randint(1, 20)
        product_category_id = random.randint(1, len(PRODUCT_CATEGORY))
        product_code = str(randCapitalised(4) + str(random.randint(100, 200)))
        product_description = fake.text()
        product_hourly_fee = random.randint(10, 50)
        product_image = None
        product_location = None
        product_monthly_fee = random.randint(1010, 2000)
        product_name = ROOM_TYPES[random.randint(1, len(ROOM_TYPES) - 1)] + ' Room ' + str(random.randint(100, 999))
        product_quantity = random.randint(10, 200)
        product_rate = random.randint(15, 50)
        product_size = random.randint(10, 50)
        product_type = PRODUCT_TYPES[random.randint(1, len(PRODUCT_TYPES) - 1)]
        product_unit = random.randint(10, 20)
        product_value = random.randint(10, 20)
        product_weekly_fee = random.randint(100, 700)
        product_workstation = random.randint(1, 20)
        location_id = random.randint(32, 52)
        product_type_id = random.randint(1, len(PRODUCT_TYPES) - 1)

        # print("Query = ", query)
        cur.execute(query, [\
            str(location_id), \
            str(parent_product_id), \
            str(product_bonus_credit), \
            str(product_bonus_hours), \
            str(product_capacity), \
            str(product_category_id), \
            str(product_code), \
            str(product_description), \
            str(product_hourly_fee), \
            str(product_image), \
            str(product_location), \
            str(product_monthly_fee), \
            str(product_name), \
            str(product_quantity), \
            str(product_rate), \
            str(product_size), \
            str(product_type), \
            str(product_unit), \
            str(product_value), \
            str(product_weekly_fee), \
            str(product_workstation), \
            str(location_id), \
            str(product_type_id), \
        ])
        # conn.commit()
        print(f"i = {i}")
        # time.sleep(3)
    except Exception as e:
        print('Cannot execute query: ', e)
conn.commit()

print("CREATED PRODUCT")
