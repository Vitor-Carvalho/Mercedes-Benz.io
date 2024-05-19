# Mercedes-Benz.io

TASK_2:  
To run the tests it is recommended that you use a screen resolution of at least 1920x1080.  
It was created a feature file containing the scenario under test and a steps definition file for the implementation of each step of the scenario. A SearchPage class was also created to keep the locators and methods of that page (page object model).  
It was created a TestRunner based on TestNG which allow us to run the tests from the terminal and generates a HTML report (under 'target' folder).  

NOTES: It was really dificult to build these tests as the pages under tests were highly fleky and the DOM component was really hard to deal with (sometimes almost impossible to manage web elements with selenium).  

Dependencies:  
cucumber-java  
version 7.17.0  

cucumber-testng  
version 7.17.0  

testng  
version 7.10.2  

selenium-java  
version 4.20.0  
