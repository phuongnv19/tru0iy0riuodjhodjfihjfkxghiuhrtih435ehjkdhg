# import os
# os.path.abspath('/Volumes/ANHTUAN/CAREER_DEV/ErP_prep/ERP_ANYTIME_OFFICE/api_admin/')
# import sys, inspect
# currentdir = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
# sys.path.insert(1,currentdir) 
# print(currentdir)

from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES
from apiTests.string_helper import randCapitalised, randomString, randTime
import random
import time



for i in range(0, 500):
    try:
        cur = conn.cursor()
        query = """
        insert into stock (
            stock_id,
            create_date,
            create_user_id,
            modified_date,
            stock_category,
            stock_code,
            stock_description,
            stock_image,
            stock_init_quatity,
            stock_location,
            stock_name,
            stock_parent_id,
            stock_quantity,
            stock_status,
            stock_type,
            stock_unit,
            stock_value,
            location_id
        )
        values (
            NEXTVAL('stock_seq'), 
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

        create_date = randTime()
        create_user_id = random.randint(700, 1000)
        modified_date = randTime()
        stock_category = 
        stock_code = randCapitalised()
        stock_description = fake.text()
        stock_image = None
        stock_init_quatity = random.randint(100, 700)
        stock_location = None
        stock_name = 
        stock_parent_id = None
        stock_quantity = random.randint(100, 300)
        stock_status = 1
        stock_type = None
        stock_unit = None
        stock_value = random.randint(100, 700)
        location_id = random.randint(32, 52)


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
