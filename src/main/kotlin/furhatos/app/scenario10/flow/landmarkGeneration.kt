package furhatos.app.scenario10.flow
import kotlin.random.Random

fun landmarkGeneration(roomNumber: String, dir: List<String>, landmarkList: List<List<String>>): String {

    var output = ""

    val firstCorridorNLG_LR: List<String> = listOf("Turn ${dir[0]} in the corridor ${landmarkList[0][0]} the ${landmarkList[0][1]}",
        "Take the corridor on your ${dir[0]} ${landmarkList[0][0]} the ${landmarkList[0][1]}",
        "Take the hallway on your ${dir[0]} ${landmarkList[0][0]} the ${landmarkList[0][1]}",
        "Go ${dir[0]} in the corridor ${landmarkList[0][0]} the ${landmarkList[0][1]}",
        "Go ${dir[0]} in the hallway ${landmarkList[0][0]} the ${landmarkList[0][1]}")

    val firstCorridorNLG_F: List<String> = listOf("Go in the corridor in front of this room ${landmarkList[0][0]} the ${landmarkList[0][1]}",
        "${landmarkList[0][0]} the ${landmarkList[0][1]} head in the hallway in front of this room",
        "${landmarkList[0][0]} the ${landmarkList[0][1]} continue forward in the front corridor",
        "${landmarkList[0][0]} the ${landmarkList[0][1]} continue forward in the front hallway")

    val thenDirection: List<String> = listOf("then turn",
        "then go",
        "continue forward and turn",
        "follow the corridor and turn",
        "follow the hallway and turn",
        "continue this way and turn",
        "walk before turning")

    val arrivedDestination: List<String> = listOf("You arrived at your destination",
        "You arrived in front of room $roomNumber",
        "You are at room $roomNumber")


    if (dir[0] != "forward") {
        output += firstCorridorNLG_LR[Random.nextInt(0, firstCorridorNLG_LR.size)]
    } else {
        output += firstCorridorNLG_F[Random.nextInt(0, firstCorridorNLG_F.size)]
    }

    for (i in 0..dir.size - 2) {
        output += ", ${thenDirection[Random.nextInt(0, thenDirection.size)]} ${dir[i+1]} ${landmarkList[i+1][0]} the ${landmarkList[i+1][1]}"
    }

    output += ". ${arrivedDestination[Random.nextInt(0, arrivedDestination.size)]}."

    return(output)

}