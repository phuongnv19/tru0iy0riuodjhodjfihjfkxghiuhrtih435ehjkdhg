import psycopg2

# Setting connection with db	
dbname = 'erp'
conn = None
try:
	conn = psycopg2.connect("dbname=erp user=erp password=4y8IHWPtc5AlEu9xWV3H host=118.70.52.20 port=5432")
	print(conn)
except Exception as e:
	print("Unable to connect to database "+dbname)

if __name__ == "__main__":
    pass