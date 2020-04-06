import requests, json

from main_config import token, main_url, head
from string_helper import randomString, randCapitalised, randTime
import random 

Data = {
    "companyId": 1,
    "contactEmail": "tuan@gmail.com",
    # "contactEmail": randomString(5) + "@" + randomString(4) + "." + randomString(4),
    "contactFirstName": randomString(6),
    "contactId": 0,
    "contactLastName": randomString(7),
    "contactPhone": random.randint(10000000,99999999999),
    "id": 0,
    "isSize": True,
    "keySearch": "string",
    "keySearch2": "string",
    "keySearchAction": "string",
}

def test_create_contact():
    res = requests.post(main_url+ "contact/createContact", headers=head, json=Data)
    print(res)
    assert json.loads(res.text) == {}

# for i in range(0, 20):
#     test_create_location()
#     test_in_the_list()

