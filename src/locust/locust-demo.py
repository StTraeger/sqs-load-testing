from locust import HttpLocust, TaskSet, task
import csv, sys, os
import locust.stats

VINS = None
locust.stats.CSV_STATS_INTERVAL_SEC = 5

class UserBehavior(TaskSet):
    def getCars(self):
        self.client.get("/cars")

    def getCarsByVin(self):
        for vin in VINS:
            self.client.get("/cars/"+ str(vin)[2:-2])

    @task
    def doRequestTask(self):
        self.getCars()
        self.getCarsByVin()



class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    host = "http://localhost:8080"
    socket = None

    def __init__(self):
        super(WebsiteUser, self).__init__()
        global VINS
        if (VINS == None):
            with open(os.path.join(os.path.dirname(__file__), '../test/resources/cars.csv'), 'r') as f:
                reader = csv.reader(f)
                next(reader)
                VINS = list(reader)

