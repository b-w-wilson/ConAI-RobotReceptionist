package furhatos.app.scenario10

import furhatos.app.scenario10.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class Scenario10Skill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}