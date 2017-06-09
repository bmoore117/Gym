import org.deeplearning4j.gym.ClientFactory
import org.deeplearning4j.rl4j.space.{DiscreteObservation, DiscreteSpace}

/**
  * Created by Ben on 6/6/2017.
  */
object Main {

  def main(args:Array[String]): Unit = {

    val qEngine = new QEngine(0.8, 0.95, 0.9)

    val client = ClientFactory.build[DiscreteObservation, Integer, DiscreteSpace]("FrozenLake-v0", true)

    var iterationCount = 0
    var lastHundredPassRate = 0
    for (i <- 1 to 5000) {

      //println("Run " + iterationCount)

      val observation = client.reset()
      var state = observation.getState
      var action = qEngine.getBestAction(state)

      if(i % 100 == 0) {
        println("Passed: " + lastHundredPassRate)
        lastHundredPassRate = 0
      }

      var shouldContinue = true
      while (shouldContinue) {
        val reply = client.step(action)

        if (reply.isDone) {
          shouldContinue = false
          if (reply.getReward == 1) {
            //println("Completed successfully")
            lastHundredPassRate += 1
          } else {
            //println("Failed")
          }
        }

        qEngine.updateQMatrix(state, action, reply.getReward, reply.getObservation.getState)

        state = reply.getObservation.getState

        if (shouldContinue) {
          action = qEngine.getBestAction(state)
          qEngine.incrementStepCount()
        }
      }
      iterationCount += 1
    }
  }
}
