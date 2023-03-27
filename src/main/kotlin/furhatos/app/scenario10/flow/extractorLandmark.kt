package furhatos.app.scenario10.flow

import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase

fun extractorLandmark(startRoom: String, goalRoom: String, debugg: Boolean): Pair<List<String>, List<List<String>>> {

    val url = "bolt://localhost:7687"
    val username = "neo4j"
    val password = "testtest"

    val driver: Driver = GraphDatabase.driver(url, AuthTokens.basic(username, password))    // Starts driver
    val session = driver.session()  // Starts a session

    // Create the mutable lists
    val directionList: MutableList<String> = mutableListOf()
    val landmarkList: MutableList<MutableList<String>> = mutableListOf()

    // Query
    val result = session.run(
        "match p=shortestPath((:Room {name:'$startRoom'})-[*]-(:Room {name:'$goalRoom'}))\n" +
                "return relationships(p)"
    )

    if (debugg) {
        println("match p=(:Room {name:'$startRoom'})-[*]-(:Room {name:'$goalRoom'})\n" + "return relationships(p)")
    }

    // Analyse of the query result
    result.list() { record ->
        // record is an InternalRecord type and record[0] is a ListValue type
        val relationsList = record[0]
        println("Number of relations: " + relationsList.size())

        // For each relation, gives the direction and order
        for (i in 0 until relationsList.size()) {
            if (debugg) {
                println("Relation " + (i + 1))
                println("direction: " + relationsList[i].get("direction").asString())
                println("landmark: " + relationsList[i].get("landmark").asString())
                println("landmark location: " + relationsList[i].get("landmarkLoc").asString())
            }

            val tmpList: MutableList<String> = mutableListOf(
                relationsList[i].get("landmarkLoc").asString(),
                relationsList[i].get("landmark").asString()
            )

            directionList.add(relationsList[i].get("direction").asString())
            landmarkList.add(tmpList)
        }
    }

    session.close()     // Closes session
    driver.close()      // Stop the driver

    return Pair(directionList, landmarkList)

}