package furhatos.app.scenario10.flow

import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.util.*

val genVoice = PollyVoice("Amy-Neural", rate=1.0)
val slowVoice = PollyVoice("Amy-Neural", rate=0.85)



val Idle: State = state {

    init {

        furhat.voice = genVoice
        furhat.character = "Isabel"
        users.setSimpleEngagementPolicy(3.0, 3)

        if (users.count > 0) {
            furhat.attend(users.random)
            goto(Start)
        }
    }

    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(Start)
    }
}

val Interaction: State = state {
    onUserLeave(instant = true) {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it)
            }
        } else if (users.count == 0) {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

}