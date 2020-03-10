package filter

import java.io.{BufferedWriter, FileWriter}

import caseclasses.{Calendar, JoinOutput, Route, Trip, TripRouteCalendar}

class JoinData(val data:List[JoinOutput]) {

   val listData:List[TripRouteCalendar]=data.map(outputData=>{
     val t =outputData.right.getOrElse(" ").asInstanceOf[JoinOutput].left.asInstanceOf[Trip]
     val r=outputData.right.getOrElse(" ").asInstanceOf[JoinOutput].right.getOrElse(" ").asInstanceOf[Route]
     val c=outputData.left.asInstanceOf[Calendar]
    val dataList= TripRouteCalendar(t.routeId,t.serviceId,t.tripId,t.tripHeadSign,t.directionId,t.shapeId,
      t.wheelChairAccessible,t.noteFr,t.noteEn,r.routeShortName,c.monday)
    dataList
  })

 var map:List[TripRouteCalendar]= listData.filter(t=>t.routeId==1|t.routeId==2|t.routeId==3|t.routeId==4|t.routeId==5)
  var filteredMap:List[TripRouteCalendar]=map.filter(f=>f.monday==1)

  val sortedTrips:List[String]=filteredMap.map(tripData=>{
    val t= tripData.routeId + "," +
      tripData.serviceId + "," +
      tripData.tripId + "," +
      tripData.tripHeadSign + "," +
      tripData.directionId + "," +
      tripData.shapeId + "," +
      tripData.wheelChairAccessible + "," +
      tripData.noteFr.getOrElse("") + "," +
      tripData.noteEn.getOrElse("")
        t
  })

    val outputFile = new BufferedWriter(new FileWriter("/Users/apple/Desktop/ScalaProjectSTM/output.csv"))
      outputFile.write(Trip.getTripHeadings)
      for (i <- sortedTrips) {
        outputFile.newLine()
        outputFile.write(i)
        println(i)
      }
      outputFile.close()



}
