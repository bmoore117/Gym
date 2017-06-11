import org.deeplearning4j.gym.StepReply
import org.deeplearning4j.rl4j.space.DiscreteObservation

import scala.util.Random

/**
  * Created by ben on 6/11/17.
  */
class FrozenLake {

  private var state:Int = 0
  private val actions:Range = 0 until 4
  private val terminalStates = List(5, 7, 11, 12, 15)

  private val rng = new Random()

  def step(action:Int):StepReply[DiscreteObservation] = {

    val drawing = rng.nextDouble()
    if(drawing < 0.3) {
      val possibles = FrozenLakeModel.getAllActions(state)
      val windAction = possibles(rng.nextInt(possibles.length))
      doAction(windAction)
    } else {
      if(actions.contains(action)) {
        doAction(action)
      }
    }

    val reward = if(state == 15) {1} else {0}
    val done = if(terminalStates.contains(state)) {true} else {false}

    new StepReply[DiscreteObservation](new DiscreteObservation(state), reward, done, null)
  }

  def getState:Int = {
    state
  }

  private def doAction(action: Int): Unit = {
    if(action == 1) { //human right
      state += 1
    } else if(action == 3) { //human left
      state -= 1
    } else if(action == 0) { //human down
      state += 4
    } else if(action == 2) { //human up
      state -= 4
    }
  }
}
