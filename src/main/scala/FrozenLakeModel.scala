/**
  * Created by Ben on 6/7/2017.
  */
object FrozenLakeModel {

  def getValidActions(state:Int):List[Int] = {

    //3 is up, 2 is right, 1 is down, 0 is left
    //Additionally it's offset. What looks down on the screen is left
    //So for human convenience 0 is down, 2 is up, 3 is left, 1 is right
    //human left: 3
    //human right: 1
    //human down: 0
    //human up: 2
    state match {
      case 0 => List(0, 1)
      case 1 => List(1, 3)
      case 2 => List(0, 1, 3)
      case 3 => List(3)
      case 4 => List(0, 2)
      case 5 => List()
      case 6 => List(0, 2)
      case 7 => List()
      case 8 => List(1, 2)
      case 9 => List(0, 1, 3)
      case 10 => List(0, 2, 3)
      case 11 => List()
      case 12 => List()
      case 13 => List(1, 2)
      case 14 => List(1, 2, 3)
      case 15 => List()
    }
  }

  def getAllActions(state:Int):List[Int] = {

    //3 is up, 2 is right, 1 is down, 0 is left
    //Additionally it's offset. What looks down on the screen is left
    //So for human convenience 0 is down, 2 is up, 3 is left, 1 is right
    //human left: 3
    //human right: 1
    //human down: 0
    //human up: 2
    state match {
      case 0 => List(0, 1)
      case 1 => List(0, 1, 3)
      case 2 => List(0, 1, 3)
      case 3 => List(0, 3)
      case 4 => List(0, 1, 2)
      case 5 => List()
      case 6 => List(0, 1, 2, 3)
      case 7 => List()
      case 8 => List(0, 1, 2)
      case 9 => List(0, 1, 2, 3)
      case 10 => List(0, 1, 2, 3)
      case 11 => List()
      case 12 => List()
      case 13 => List(1, 2, 3)
      case 14 => List(1, 2, 3)
      case 15 => List()
    }
  }

}
