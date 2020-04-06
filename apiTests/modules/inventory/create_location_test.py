import requests, json

from main_config import token, main_url, head
from string_helper import randomString, randCapitalised, randTime
import random 

name = randCapitalised(random.randint(10, 20))
Data = {
    "address": randomString(random.randint(40,50)),
    "closeTime": "2019-11-27T12:03:30.534Z",
    "id": 0,
    "isSize": True,
    "keySearch": randomString(5),
    "keySearch2": randomString(4),
    "keySearchAction": "string",
    "locationCode": randomString(6),
    "locationId": random.randint(0,1000000),
    "locationName": name,
    "openTime": "2019-11-27T18:03:30.534Z",
}

def test_create_location():
    res = requests.post(main_url+ "location/create", headers=head, json=Data)
    print(res)
    assert json.loads(res.text).get('address') is not None

def test_in_the_list():
    res = requests.get(main_url+ "common/getListLocation", headers=head)
    list_res = list(json.loads(res.text))
    names = [x.get('locationName') for x in list_res]
    assert name in names

# for i in range(0, 20):
#     test_create_location()
#     test_in_the_list()

name2 = randCapitalised(random.randint(10, 20))
Data2 = Data.copy()
Data2['closeTime'] = "2019-111-27T18:03:30.534Z"
def test_failed_time_create_location():
    res = requests.post(main_url+ "location/create", headers=head, json=Data2)
    print(res)
    assert json.loads(res.text).get('message') is not None


Data3 = Data.copy()
Data3['openTime'] = "2019-111-27T18:03:30.534Z"
def test_failed_time_create_location2():
    res = requests.post(main_url+ "location/create", headers=head, json=Data3)
    print(res)
    assert json.loads(res.text).get('message') is not None

Data4 = Data.copy()
Data4['locationName'] = "89u2398u@#$@#2"
def test_failed_name_create_location():
    res = requests.post(main_url+ "location/create", headers=head, json=Data4)
    print(res)
    assert json.loads(res.text).get('message') is not None

Data5 = Data.copy()
Data5['locationName'] = randomString(1000)
def test_oversized_name_create_location2():
    res = requests.post(main_url+ "location/create", headers=head, json=Data5)
    print(res)
    assert json.loads(res.text).get('message') is not None