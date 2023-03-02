package furhatos.app.scenario10.flow
import furhatos.app.scenario10.nlu.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.Yes

// The Skill starts here
val Start : State = state(Interaction) {
// When a new user enters the scene
    onEntry {
        furhat.gesture(Gestures.BigSmile)
        furhat.say({
            +"Hello! Welcome to the national robotarium! Lovely to see you! I am Charlie!"
            +blocking {
                furhat.gesture(Gestures.BigSmile, async = true)
            }
            +"the receptionist! "})
        furhat.gesture(Gestures.BigSmile)
        furhat.say("it will be great if you can speak when my green light is on!")
        furhat.ledStrip.solid(java.awt.Color.GREEN)
        furhat.say("That's when I can hear you!")
        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        if(furhat.isListening()){
            furhat.ledStrip.solid(java.awt.Color.GREEN)
        }
        goto(assistance())
    }


    onUserLeave (instant = false) {
        if(it == users.current){
            furhat.ledStrip.solid(java.awt.Color(0,0,0))
            furhat.ledStrip.solid(java.awt.Color.YELLOW)
            furhat.ledStrip.solid(java.awt.Color(0,0,0))
            furhat.say("Have a lovely day! Goodbye !")
        }
        goto(Interaction)
    }

}

fun assistance() : State = state(Interaction) {
    onEntry {
        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        furhat.gesture(Gestures.BigSmile)
        furhat.say("How can I help you today ?")
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

    onResponse<Thanks> {
        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        furhat.say("You are Welcome!")
        furhat.gesture(Gestures.BigSmile)
    }

    //

    onResponse<FindRoom> {
        furhat.say("You can find the room by following the direction @direction")
        furhat.listen()
    }

    onResponse<FindCompany> {
        furhat.say("You can find the Company by following the direction @direction")
        furhat.listen()
    }

    onResponse<AskRepeat> {
        furhat.say("Bazooka")
        furhat.gesture(Gestures.BigSmile)
        furhat.listen()
    }

    //

    onResponse { // Catches everything else

        furhat.ledStrip.solid(java.awt.Color(0,0,0))
        furhat.say({
            random {
                +" Sorry! I didn't hear you, Would you speak a little louder when my green light is on please"
                +" Sorry I didn't catch that! Can you speak up, whenever my green light is on please?"
                +"Can you please speak more loudly, when the green light is on"
                +" Is it possible for you to raise your voice, a little bit please! When my green light is on! Just so I can hear you clearly!"

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
                    +" Sorry! I didn't hear you, Would you speak a little louder when my green light is on please"
                    +" Sorry I didn't catch that! Can you speak up, whenever my green light is on please?"
                    +"Can you please speak more loudly, when the green light is on"
                    +" Is it possible for you to raise your voice, a little bit please! When my green light is on! Just so I can hear you clearly!"

                }
            })
        furhat.ledStrip.solid(java.awt.Color.GREEN)
        furhat.gesture(Gestures.Smile)
        furhat.listen()
    }
}
