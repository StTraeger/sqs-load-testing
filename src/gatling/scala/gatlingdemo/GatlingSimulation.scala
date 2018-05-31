package gatlingdemo

import io.gatling.core.Predef._;
import io.gatling.http.Predef._;

import scala.concurrent.duration._

class GatlingSimulation extends Simulation{

  val httpConf = http.baseURL("http://localhost:8080")

  val getAllCarsScenario = scenario("Car Search")
    .exec(http("get_all").get("/cars"))
    .during(15 seconds){
      feed(feeder)
        .exec(http(s"Get Car by VIN ${vin}").get(s"/cars/${vin}"))
        .pause(1 second)
        .exec(http())
    }

  setUp(
    getAllCarsScenario.inject(rampUsers(50).over(10))
  ).protocols(httpConf)



}
