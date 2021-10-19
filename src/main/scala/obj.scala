
import java.io.File
import java.util.Scanner
import scala.collection.mutable

class obj(Name: String, Way : String){
  var s : Scanner = new Scanner (new File(Name))
  var ArrayOfPoint =mutable.Buffer[Point]()
  var ArrayOfPoligons =mutable.Buffer[Poligon]()
  while(s.hasNextLine){
    var NextLine = s.nextLine()
    var NextLineSplit = NextLine.split(" ")
    if(NextLineSplit(0)=="v"){
      var a = new Point(NextLineSplit(1).toDouble,NextLineSplit(2).toDouble,NextLineSplit(3).toDouble)
      ArrayOfPoint+=a
    }
    else if(NextLineSplit(0)=="f"){
      var a = new Poligon(NextLineSplit(1).toInt,NextLineSplit(2).toInt,NextLineSplit(3).toInt)
      ArrayOfPoligons+=a
    }
  }
  case class Point(x: Double,y:Double,z:Double)
  case class Poligon(frst: Int,scnd:Int,thrd:Int)

}
