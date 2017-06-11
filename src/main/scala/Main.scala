import org.deeplearning4j.gym.ClientFactory
import org.deeplearning4j.rl4j.space.{DiscreteObservation, DiscreteSpace}

/**
  * Created by Ben on 6/6/2017.
  */
object Main {

  def main(args:Array[String]): Unit = {

    val qEngine = new QEngine(0.8, 0.95, 0.9)

    //val client = ClientFactory.build[DiscreteObservation, Integer, DiscreteSpace]("FrozenLake-v0", true)

    val queue = new LastNQueue[Int](100)
    for (episode <- 1 to 15000) {

      println(queue.sum / 100.0)

      //val observation = client.reset()
      //var state = observation.getState

      val client = new FrozenLake
      var state = client.getState

      var shouldContinue = true
      while (shouldContinue) {
        val action = qEngine.getBestAction(state, episode)
        val reply = client.step(action)

        qEngine.updateQMatrix(state, action, reply.getReward, reply.getObservation.getState)
        state = reply.getObservation.getState
        //println(state)

        if (reply.isDone) {
          shouldContinue = false
          if (reply.getReward == 1) {
            queue.enqueue(1)
          } else {
            queue.enqueue(0)
          }
        }
      }
    }
  }
}
