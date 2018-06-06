package gatlingdemo

import io.gatling.core.Predef._;
import io.gatling.http.Predef._;

import scala.concurrent.duration._

class GatlingSimulation extends Simulation{

  val httpConf = http.baseURL("http://localhost:8080")

  val feeder = csv("cars.csv").random

  val getAllCarsScenario = scenario("Car Search")
    .exec(http("get_all").get("/cars"))
    .during(15 seconds){
      feed(feeder)
        .exec(http("Get Car by VIN ${vin}").get("/cars/${vin}"))
        .pause(1 second)
    }

  setUp(
    getAllCarsScenario.inject(atOnceUsers(10), rampUsers(50) over(30 seconds))
  ).protocols(httpConf)



}
