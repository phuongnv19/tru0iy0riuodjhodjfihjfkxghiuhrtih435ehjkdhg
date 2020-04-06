import random
import string

import random
import time

from datetime import datetime, timedelta

min_year=1900
max_year=datetime.now().year

start = datetime(min_year, 1, 1, 00, 00, 00)
years = max_year - min_year+1
end = start + timedelta(days=365 * years)

for i in range(10):
    random_date = start + (end - start) * random.random()
    print(random_date)

#done

# or a function
def gen_datetime(min_year=2019, max_year=datetime.now().year):
    # generate a datetime in format yyyy-mm-dd hh:mm:ss.000000
    start = datetime(min_year, 1, 1, 00, 00, 00)
    years = max_year - min_year + 1
    end = start + timedelta(days=365 * years)
    return start + (end - start) * random.random()

def str_time_prop(start, end, format, prop):
    """Get a time at a proportion of a range of two formatted times.

    start and end should be strings specifying times formated in the
    given format (strftime-style), giving an interval [start, end].
    prop specifies how a proportion of the interval to be taken after
    start.  The returned time will be in the specified format.
    """

    stime = time.mktime(time.strptime(start, format))
    etime = time.mktime(time.strptime(end, format))

    ptime = stime + prop * (etime - stime)

    return time.strftime(format, time.localtime(ptime))

from random import randrange
from datetime import timedelta

def random_date(start, end):
    """
    This function will return a random datetime between two datetime 
    objects.
    """
    delta = end - start
    int_delta = (delta.days * 24 * 60 * 60) + delta.seconds
    random_second = randrange(int_delta)
    return start + timedelta(seconds=random_second)

d1 = datetime.strptime('1/1/1995 1:30 PM', '%m/%d/%Y %I:%M %p')
d2 = datetime.strptime('1/1/2019 4:50 AM', '%m/%d/%Y %I:%M %p')

def randTime():
    return str(random_date(d1, d2))

def randInt():
    return random.randint(100, 10000)

def randomString(stringLength):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters + ".") for i in range(stringLength))

def randCapitalised(stringLength):
    return randomString(stringLength).upper()

def randPhoneNum():
    return random.randint(200000000, 999999999)

import bcrypt

def hash_pass(password):
    return bcrypt.hashpw(password.encode('utf-8'), bcrypt.gensalt(rounds = 10, prefix=b"2a")).decode('ascii')
