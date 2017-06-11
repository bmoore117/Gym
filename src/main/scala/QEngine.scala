import scala.collection.mutable

/**
  * Created by Ben on 5/27/2017.
  */
class QEngine(val learningRate:Double, val gamma:Double, val greedyThreshold:Double) {

  private val qMatrix = new mutable.HashMap[Int, mutable.HashMap[Int, Double]]

  private val rng = new scala.util.Random(123)

  def getBestAction(state:Int, step:Int): Int = {
    val validActions = FrozenLakeModel.getValidActions(state)
    if (shouldTakePlannedAction(step)) {
      return getBestActionInternal(state, validActions)
    }
    println("Exploring")
    getRandomAction(validActions)
  }

  private def getBestActionInternal(state:Int, validActions:Seq[Int]):Int = {
    val actions = qMatrix.get(state)

    if(actions.isDefined) { //previously seen state
      val remainingActions = actions.get.filter(pair => validActions.contains(pair._1))

      if(remainingActions.nonEmpty) { //where we can do something
        var bestPair = (0, Double.MinValue)

        remainingActions.foreach(pair => {
          if (pair._2 > bestPair._2) { //_2 is reward
            bestPair = pair
          }
        })
        return bestPair._1 //return action with highest reward
      }
    }

    //if actions not defined, if no valid actions
    getRandomAction(validActions)
  }

  private def getRandomAction(validActions:Seq[Int]):Int = {
    //println("Taking random action")
    if(validActions.nonEmpty) {
      validActions(rng.nextInt(validActions.length))
    } else {
      0
    }
  }

  private def shouldTakePlannedAction(step:Int):Boolean = {
    val drawing = rng.nextDouble()

    drawing < greedyThreshold * (step/1000)
  }

  def updateQMatrix(state:Int, action:Int, reward:Double, newState:Int): Unit = {
    val row = qMatrix.get(state)

    if(row.isDefined) {
      val cell = row.get.get(action)

      if(cell.isDefined) {
        row.get.put(action, cell.get + learningRate*(reward + gamma*getBestActionInternal(newState, FrozenLakeModel.getValidActions(newState)) - cell.get))
      } else {
        //in this case cell.get would be 0, so it drops out
        row.get.put(action, learningRate*(reward + gamma*getBestActionInternal(newState, FrozenLakeModel.getValidActions(newState))))
      }
    } else {
      val newRow = new mutable.HashMap[Int, Double]()
      newRow.put(action, learningRate*(reward + gamma*getBestActionInternal(newState, FrozenLakeModel.getValidActions(newState))))
      qMatrix.put(state, newRow)
    }
  }
}
