from locust import HttpLocust, TaskSet, task

class WebsiteTasks(TaskSet):
    @task
    def getCars(self):
        self.client.get("/cars")

class WebsiteUser(HttpLocust):
    task_set = WebsiteTasks
    min_wait = 5000
    max_wait 15000
