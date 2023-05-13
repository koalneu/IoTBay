# Implementation of the Login, Register and Update user details

**IMPLEMENTED THE CONTROLLER MODEL STUFF FOR THE DB MADE DIFFERENT SCREENS FOR THE ADMIN AND STAFF**

 - DATABASE FOR ACCESSLOGS NEEDS TO BE ADDED IF U HAVE NOT ADDED IT YET

**CUSTOMER DATABASE NEEDS A NEW COLUMN ADDED - USERTYPE STRING (SIZE 50 CHARS)**

![image](https://user-images.githubusercontent.com/126222338/236673372-84636a0f-ce81-4f63-8a1f-9c52737fd96e.png)

WHERE:
- ACCESSLOGID integer
- USERID integer
- USERNAME String
- ACESSTIME TimeStamp
- Action String


LOCAL VARIABLE NAMES:

WHICH NEEDS TO BE CHANGED TO FUNCTION WITH ACTUAL DB

Database name: TEST
DB username: test
DB password: test
DB table name: CUSTOMERS


![database](https://user-images.githubusercontent.com/126222338/236108652-00a27f86-a823-4cea-ad9c-259618343ffc.PNG)


Sections which look like the image below need to be adjusted accordingly to the new DB
![Capture](https://user-images.githubusercontent.com/126222338/236109019-f0e00ebc-037f-4a71-aac1-7b23dfa0e217.PNG)


NEED TO DO:

text field checks - ??? NOT SURE HOW TO DO

Account for admin and staff logins - ?? NOT SURE WHATS HAPPENNING WITH THAT

You will find the list of products once you login the application and click the "My profile" button.

please add dummy data for the products table in the database 

Constructors for the Product and Order models:

![image](https://github.com/koalneu/IoTBay/assets/126221701/79e851f9-0527-4d6a-bd92-3bdfd900decb)

![image](https://github.com/koalneu/IoTBay/assets/126221701/1e24b1a3-f815-4284-8bf8-97728f5dc09f)

so far orders are not being saved on the database because the order has to be recorded in the database after it gets payed for
