/**
  * Created by ben on 6/10/17.
  */
class RouteMaster {

  //human left: 3
  //human right: 1
  //human down: 0
  //human up: 2
  def getAction(state:Int):Int = {
    state match {
      case 0 => 1
      case 1 => 1
      case 2 => 0
      case 3 => 3
      case 4 => 2
      case 5 => -1
      case 6 => 0
      case 7 => -1
      case 8 => 1
      case 9 => 1
      case 10 => 0
      case 11 => -1
      case 12 => -1
      case 13 => 1
      case 14 => 1
      case 15 => -1
    }
  }
}
