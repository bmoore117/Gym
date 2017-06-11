import org.deeplearning4j.gym.StepReply
import org.deeplearning4j.rl4j.space.DiscreteObservation

/**
  * Created by ben on 6/11/17.
  */
class FrozenLake {

  private var state:Int = 0
  private val actions:Range = 0 until 4
  private val terminalStates = List(5, 7, 11, 12, 15)

  def step(action:Int):StepReply[DiscreteObservation] = {
    if(actions.contains(action)) {
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

    val reward = if(state == 15) {1} else {0}
    val done = if(terminalStates.contains(state)) {true} else {false}

    new StepReply[DiscreteObservation](new DiscreteObservation(state), reward, done, null)
  }

  def getState:Int = {
    state
  }
}
