from apiTests.sampling.DBconnect import conn
from apiTests.sampling.DATA import NAMES, read_names, ROOM_TYPES, fake, PRODUCT_CATEGORY, PRODUCT_TYPES, ROOM_TYPES, ADDRESSES, COUNTRIES, USER_EMAIL, USER_PASSWORD
from apiTests.string_helper import randCapitalised, randomString, random_date, randTime, randPhoneNum, gen_datetime, hash_pass
import random
import time
import datetime
from datetime import timedelta

for i in range(1, 200):
    try:
        cur = conn.cursor()
        query = """
        insert into sys_user (
            user_id,
            check_ip,
            create_date,
            create_user_id,
            date_of_birth,
            description,
            email,
            first_name,
            gender,
            home_phone,
            identity_code,
            identity_type,
            ip_address,
            key_fob_id,
            last_change_password,
            last_lock_date,
            last_name,
            location_id,
            login_failure_count,
            mobile_phone,
            password,
            status,
            valid_from,
            valid_to
        )
        values (
            NEXTVAL('sys_user_seq'), 
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
            %s, 
            %s
        );
        """
        
        check_ip = None
        create_date = randTime()
        create_user_id = None
        date_of_birth = randTime()
        description = fake.text()
        email = 'erp' + str(i) + randomString(3) + 'erp@' + 'gmail.com'
        first_name = fake.name()
        gender = None
        home_phone = randPhoneNum()
        identity_code = randCapitalised(7)
        identity_type = None
        ip_address = None
        key_fob_id = randCapitalised(15)
        last_change_password = None
        last_lock_date = None
        last_name = fake.name()
        location_id = random.randint(32, 52)
        login_failure_count = 0
        mobile_phone = randPhoneNum()
        password = hash_pass(USER_PASSWORD)
        status = 1
        valid_from = randTime()
        valid_to = None

        cur.execute(query, [\
            check_ip, \
            create_date, \
            create_user_id, \
            date_of_birth, \
            description, \
            email, \
            first_name, \
            gender, \
            home_phone, \
            identity_code, \
            identity_type, \
            ip_address, \
            key_fob_id, \
            last_change_password, \
            last_lock_date, \
            last_name, \
            location_id, \
            login_failure_count, \
            mobile_phone, \
            password, \
            status, \
            valid_from, \
            valid_to, \
        ])
        # conn.commit()
        print(f"i = {i}")
        # time.sleep(3)
    except Exception as e:
        print('Cannot execute query: ', e)
conn.commit()

print("CREATED Statuses")
