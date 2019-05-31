package furhatos.app.patientdifficult2

import furhatos.app.patientdifficult2.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class Patientdifficult2Skill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
