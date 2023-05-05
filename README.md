# Implementation of the Login, Register and Update user details

The database has been connected to account for the various variables and functions although the code must be edited to account for the database's login credentials and name.
Need to double check the data fields for differences if any occurred between my database and the one we are working on but it should be the same.
A new page has been added called Edit.jsp which is the update details page.

STRUCUTRE OF CODE HAS BEEN CHANGED:

Concept behind it is you are automatically a guest so therefore i require the index.html being changed to a jsp page. This is the landing page where you would see all the items you can buy and you can login or register e.g., similar to amazons page:

![image](https://user-images.githubusercontent.com/126222338/236471419-2dddee09-4de6-4a16-823b-0fe06a87e514.png)

If the user completes the log's in or Registers then they are redirected to a new page where the session is set to the new user found or created and a new page called "RegisteredUserPage" which replaced the old page called "welcome.jsp". This page would act as the alternative page from the landing page as it would simulate the same functions although have a few extra functionalities such as the ability to view their own details in "profile" which was before called "main.jsp" and allows them to edit.

![image](https://user-images.githubusercontent.com/126222338/236472223-ad4084a1-2775-48c3-848c-ba6c5293cfc5.png)

Summary: 

- Registered User: USES RegisteredUserPage.jsp, Basic functionality + Account functionality e.g, look, buy, view products and change current details, logout,                             view order history etc..

- Guest User: USES index.jsp, Basic functionality e.g, ability to order, view and look at products



NOTE: They should look essentially the same although one has more buttons!



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

move MODEL functions to Controller

Database for acesss logs

