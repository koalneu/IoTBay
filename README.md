# Implementation of the Login, Register and Update user details

The database has been connected to account for the various variables and functions although the code must be edited to account for the database's login credentials and name.
Need to double check the data fields for differences if any occurred between my database and the one we are working on but it should be the same.
A new page has been added called Edit.jsp which is the update details page.

Not sure if we need to change the "guest" login functions and how we will handle that - Needs to be discussed.

Not sure if i need to implement a delete account function aswell - Needs to be discussed.

For reference this is the database that i have been working on locally:

LOCAL VARIABLE NAMES:

NOTE: NEEDS TO BE CHANGED TO FUNCTION WITH ACTUAL DB

Database name: TEST
DB username: test
DB password: test
DB table name: CUSTOMERS


![database](https://user-images.githubusercontent.com/126222338/236108652-00a27f86-a823-4cea-ad9c-259618343ffc.PNG)


Sections which look like the image below need to be adjusted accordingly to the new DB
![Capture](https://user-images.githubusercontent.com/126222338/236109019-f0e00ebc-037f-4a71-aac1-7b23dfa0e217.PNG)


NEED TO DO:

text field checks

move MODEL functions to Controller

Handle guest -> automatically guest remove buttons etc.

Add button to return to landing page on each screen

Database for acesss logs
