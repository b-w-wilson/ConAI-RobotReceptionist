package furhatos.app.scenario10.flow
import furhatos.app.scenario10.nlu.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.Goodbye
import furhatos.nlu.common.Yes
import furhatos.records.Location

val guidanceType = "landmark"

// The Skill starts here
val Start : State = state(Interaction) {
// When a new user enters the scene
    onEntry {

        do {
            furhat.say("Welcome to our experimental setup. I will give you instructions to navigate around a map, based on ${guidanceType} guidance.")
            furhat.attend(Location.DOWN)
            furhat.say("Below me is the map.")
            furhat.attend(users.current)
            furhat.say("Please follow the instructions that I give you with the pen on the map, starting in the reception, and drawing a continuous line from where you start to where you end.")
            furhat.ledStrip.solid(java.awt.Color.GREEN)
            val repeat = furhat.askYN("Do you want me to repeat?")
            furhat.ledStrip.solid(java.awt.Color(0, 0, 0))
        } while (repeat == true)

        goto(assistance())
    }


    onUserLeave (instant = false) {
        if(it == users.current){
            furhat.ledStrip.solid(java.awt.Color(0,0,0))
            furhat.ledStrip.solid(java.awt.Color.YELLOW)
            furhat.ledStrip.solid(java.awt.Color(0,0,0))
            furhat.say("Have a lovely day! Goodbye!")
        }
        goto(Interaction)
    }

}

fun assistance() : State = state(Interaction) {
    onEntry {
        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Ask me for the directions of your room.")
        furhat.ledStrip.solid(java.awt.Color.GREEN)
        furhat.gesture(Gestures.Smile)
        furhat.listen()

    }

    onUserLeave (instant = false) {
        if(it ==users.current) {
            furhat.ledStrip.solid(java.awt.Color(0, 0, 0))
            furhat.ledStrip.solid(java.awt.Color.YELLOW)
            furhat.ledStrip.solid(java.awt.Color(0, 0, 0))
            furhat.say("Bye for now!")
        }
        goto(Interaction)
    }

    onResponse <Notsure> {
        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        furhat.say("I am here if you need my help!")
        furhat.gesture(Gestures.Smile)
    }
    onResponse<Howareyou> {
        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Fortunately! I am programmed to be always happy!")
        furhat.gesture(Gestures.BigSmile)
    }

    /*onResponse<Thanks> {
        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        furhat.say("You are Welcome!")
        furhat.gesture(Gestures.BigSmile)
    }*/


    onResponse<FindRoom> {
        //val isLandmark = false
        var roomNumber = it.intent.getEntities().getValue("num").toString()

        if (roomNumber == "1") {
            roomNumber = "one"
        } else if (roomNumber == "2") {
            roomNumber = "two"
        } else if (roomNumber == "3") {
            roomNumber = "three"
        } else if (roomNumber == "4") {
            roomNumber = "four"
        } else if (roomNumber == "5") {
            roomNumber = "five"
        } else if (roomNumber == "6") {
            roomNumber = "six"
        } else if (roomNumber == "7") {
            roomNumber = "seven"
        }
        furhat.voice = slowVoice

        var directions = ""

        if (guidanceType == "landmark") {
            val (dir, landmarkList) = extractorLandmark("reception", "room $roomNumber", debugg = false)
            directions = landmarkGeneration(roomNumber, dir, landmarkList)
            furhat.say(directions)
        } else {
            val dir = extractorRelative("reception", "room $roomNumber", debugg = false)
            directions = relativeGeneration(roomNumber, dir)
            furhat.say(directions)
        }

        furhat.voice = genVoice

        // Version 1 -- no loop
        /*val repeat = furhat.askYN("Do you want me to repeat?")
        if (repeat == true) {
            furhat.say(directions)
            furhat.ask("Can I help you with any other things?")
        } else {
            furhat.ask("Can I help you with any other things?")
        }*/

        // Version 2 -- loop
        var stop = false
        do{
            furhat.ledStrip.solid(java.awt.Color.GREEN)
            val repeat = furhat.askYN("Do you want me to repeat?")
            furhat.ledStrip.solid(java.awt.Color(0, 0, 0))
            if (repeat == true) {
                furhat.say(directions)
            } else {
                stop = true
            }
        } while (stop == false)
        furhat.ask("If you need any other directions please ask or you can say goodbye.", timeout = 10000)

    }

    /*onResponse<AskRepeat> {
        furhat.say("ok")
        furhat.gesture(Gestures.BigSmile)
        furhat.listen()
    }*/

    onResponse<Bye> {
        furhat.say({random {
            + "Goodbye!"
            + "See you later!"
            + "Bye!" }
        })
        furhat.gesture(Gestures.BigSmile)
        goto(Idle)
    }


    onResponse { // Catches everything else

        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        furhat.say({
            random {
                + "Sorry! I didn't hear you, Would you speak a little louder when my green light is on please"
                + "Sorry I didn't catch that! Can you speak up, whenever my green light is on please?"
                + "Can you please speak more loudly, when the green light is on"
                + "Is it possible for you to raise your voice, a little bit please!"

            }
        })
        furhat.ledStrip.solid(java.awt.Color.GREEN)
        furhat.gesture(Gestures.Smile)
        furhat.listen()

    }
    onNoResponse { // Catches silence
        furhat.ledStrip.solid(java.awt.Color(0, 0, 0))
        furhat.say({
                random {
                    + "Sorry! I didn't hear you, Would you speak a little louder when my green light is on please"
                    + "Sorry I didn't catch that! Can you speak up, whenever my green light is on please?"
                    + "Can you please speak more loudly, when the green light is on"
                    + "Is it possible for you to raise your voice, a little bit please!"
                }
            })
        furhat.ledStrip.solid(java.awt.Color.GREEN)
        furhat.gesture(Gestures.Smile)
        furhat.listen()
    }
}
