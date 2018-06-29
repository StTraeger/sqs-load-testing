# Load testing with Gatling, Locust and Artillery

## Intro
As project for the course 'Software Qualittässicherung' at the University of Applied Sciences Rosenheim i wrote a simple load testing project to compare some Open-Source tools.
The candidates for the comparison are:
- Gatling (Scala based) see: http://gatling.io
- Locust (Python based) see: http://locust.io
- Artillery (Yaml based) see: http://artillery.io

## Testscenario
The scenario which i have chosen for the comparison is quite simple: It will start with initial users and executes a ramp-up to 50 users in 30 seconds. Every user will fire 5 requests:
- 1x against the endpoint GET '/cars/'
- 4x against the endpoint GET '/cars/{vin}'

The endpoints belong to a related project which you should combine with this load-testing project: [Link to the demoserver](https://github.com/StTraeger/sqs-car-webservice).

The different vin values needed for the test will be provided through a csv file. So every toolkit should support some kind of a csv-feeder, or at least, should allow to implement a own routine for that.

## Installation
Make sure that you have installed:
- Gradle
- Npm
- Python 2.x or 3.x and pip
- Artillery ('npm install -g artillery')
- Locust ('python pip install locustio')

## Execution
First build the project using the 'gradle build' command. Since the project is Gradle based there is a Gradle task for every toolkit (Gatling brings native support for gradle. Locust and Artillery will be managed with own Gradle tasks.)

**1. Gatling:**
To execute the Gatling test just type 'gradle runGatling' in a commandline in the code directory.

**2. Locust:**
To execute the Locust test type 'gradle runLocust' in a commandLine in the code directory. After that you will see a output on the console which says the WebUI is available under http://localhost:8098. In this UI you will need to enter the amount of users you want to end up in the test and the rate how many users should spawn per second (starting from 0).

**3. Artillery:**
To execute the Artillery test type 'gradle runArtillery' in a commandline in the code directory. After the successfull execution of the test you can type 'artillery report report.json' to view the Testreport in your browser.
