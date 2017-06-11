import scala.util.Random

/**
  * Created by ben on 6/9/17.
  */
class QEngine2(val numStates:Int, val numActions:Int, val alpha:Double, val gamma:Double, val greedyThreshold:Double) {

  val qTable:Array[Array[Double]] = Array.ofDim[Double](numStates, numActions)
  val rng = new Random(123)

  val actions:Range = 0 until numActions

  var stepCount:Int = 0

  def getAction(state:Int, stepCount:Int):Int = {
    if(rng.nextDouble() < stepCount * (greedyThreshold / 1000)) {
      val max = qTable(state).max
      qTable(state).indexOf(max)
    } else {
      actions(rng.nextInt(actions.length))
    }
  }

  def learn(state:Int, action:Int, reward:Double, newState:Int):Unit = {
    val r2 = reward + gamma*qTable(newState).max
    qTable(state)(action) += alpha * (r2 - qTable(state)(action))
  }
}
