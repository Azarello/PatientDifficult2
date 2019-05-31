package furhatos.app.patientdifficult2.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.patientdifficult2.nlu.*
import io.opencensus.stats.Aggregation
import java.util.*

val Start : State = state(Interaction) {
    onEntry {
        goto(SecondModule)
    }
}


val SecondModuleGoto : State = state {
    onEntry {
        goto(SecondModule)
    }
}

val SecondModule : State = state {

    onEntry {
        furhat.say("Welcome to this module, in which you will learn more about the second part of establishing a " +
                " conscious therapeutic alliance. This step involves getting the patient to declare their will to do therapy, " +
                " meaning they explicitly state they want to work on the issue in a therapeutic setting. " +
                "It is important to keep in mind that one should never explore a problem, unless the patient first expresses " +
                "their wish explore it with you. ")
        furhat.say("In this module, we will look at defenses that a patient may use, to avoid expressing " +
                "their desire to do therapy. Additionally you will encounter a new type of response, that of anxiety. " +
                " For now, if the patient experiences anxiety, simply label it as you have other defenses. In later modules you will" +
                " learn more specific skills for how to deal with it appropriately.")
        furhat.ask(" Your task is to identify and block five responses in order to get the patient to explicitly " +
                " declare their will to do therapy." +
                " Say start if you are ready, or say repeat if you would like to to hear the instructions again.")
    }

    onResponse<Continue> {
        goto(DeclareWill)
    }

    onResponse<Repeat> {
        goto(SecondModuleGoto)
    }
}



val Wait2 : State = state {

    onTime(delay=1500) {
        furhat.ask("When you are ready for the next defense, say next")
    }

    onResponse<Continue> {
        goto(Counter2)
    }
}


val DeclareWill : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> goto(Projection2)
            1 -> goto(HypotheticalSpeech2)
            2 -> goto(Defiance2)
            3 -> goto(Rumination2)
            4 -> goto(Anxiety2)
        }
    }
}


var counter2 = 0


val Counter2 : State = state {

    onEntry {
        counter2 += 1
        if (counter2 < 5)
            goto(DeclareWill)
        else {
            furhat.say(" Yes, I do want to work on my issues about getting angry with my father.")
            goto(Resolution2)
        }
    }
}

val Projection2Goto : State = state {
    onEntry {
        goto(Projection2)
    }
}

val Projection2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask("Well my brother thinks I should work on the problem, he's getting tired of it")
            1 -> furhat.ask("You really seem like you want to dig deeper into these issues")
            2 -> furhat.ask("Everyone in my family expect me to work on this problem, I guess it's hurting them")
            3 -> furhat.ask(" My doctor wants me to work on the problem because he's worried about my health")
            4 -> furhat.ask(" Do you think I should work on my problem?")
        }
    }

    onResponse<ProjectionBlock2> {

        it.intent.person
        it.intent.problem
        it.intent.avoid
        it.intent.will
        it.intent.force
        it.intent.intellect
        it.intent.obey
        it.intent.specify
        it.intent.feel
        it.intent.notice


        goto(Counter2)
    }


    onResponse<HypotheticalSpeechBlock2> {
        goto(DeclareWill)
    }

    onResponse<DefianceBlock2> {
        goto(DeclareWill)

    }

    onResponse<RuminationBlock2> {
        goto(DeclareWill)
    }

    onResponse<AnxietyBlock2> {
        goto(DeclareWill)
    }
}


val HypotheticalSpeech2Goto : State = state {
    onEntry {
        goto(HypotheticalSpeech2)
    }
}

val HypotheticalSpeech2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" I suppose digging deeper into my feelings could be of benefit")
            1 -> furhat.ask(" Perhaps discussing my problems would help me in dealing with them")
            2 -> furhat.ask(" I guess being in therapy could be helpful for my mental health")
            3 -> furhat.ask(" Maybe investigating what is going on could potentially be good")
            4 -> furhat.ask(" In theory it could conceivably improve my situation")
        }
    }

    onResponse<HypotheticalSpeechBlock2> {

        it.intent.avoid
        it.intent.will
        it.intent.indirect
        it.intent.notice
        it.intent.problem
        it.intent.deny
        it.intent.specify
        it.intent.feel


        goto(Counter2)
    }


    onResponse<ProjectionBlock2> {
        goto(DeclareWill)
    }

    onResponse<DefianceBlock2> {
        goto(DeclareWill)
    }

    onResponse<RuminationBlock2> {
        goto(DeclareWill)
    }

    onResponse<AnxietyBlock2> {
        goto(DeclareWill)
    }
}

val Defiance2Goto : State = state {
    onEntry {
        goto(Defiance2)
    }
}

val Defiance2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask("I don't think I want to work on my problem after all, it's not causing a lot " +
                    "of harm anyways ")
            1 -> furhat.ask(" On closer thought the problem is not as important as I let on, I don't want  " +
                    "to go into it. ")
            2 -> furhat.ask(" I just don't want to deal with it ok")
            3 -> furhat.ask(" Why would I want to work on something that's not a problem. My life is overall fine")
            4 -> furhat.ask(" I don't want to examine it closer since there really is not much to discover anyway.")
        }
    }

    onResponse<DefianceBlock2> {

        it.intent.feel
        it.intent.deny
        it.intent.notice
        it.intent.specific
        it.intent.avoid
        it.intent.problem

        goto(Counter2)
    }

    onResponse<DenialBlock1> {
        goto(DeclareWill)
    }

    onResponse<ProjectionBlock2> {
        goto(DeclareWill)
    }

    onResponse<HypotheticalSpeechBlock2> {
        goto(DeclareWill)
    }

    onResponse<RuminationBlock2> {
        goto(DeclareWill)
    }

    onResponse<AnxietyBlock2> {
        goto(DeclareWill)
    }
}

val Rumination2Goto : State = state {
    onEntry {
        goto(Rumination2)
    }
}


val Rumination2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" I do want to look into the problem. Or you know, part of me does and the other not, " +
                    "and then I get kind of weird about it")
            1 -> furhat.ask(" I don't think it's beneficial to look into my problem more because really it's " +
                    "about my brain chemistry, at least that's what my doctor said, so how could therapy help that")
            2 -> furhat.ask(" In a way I wish I could solve this part of my inner life, but at the same time " +
                    "it's a part of myself so it just gets really complex and strange thinking about it")
            3 -> furhat.ask(" I do wish I could get better, at least I wonder what it would feel like to " +
                    "be more balanced, but it is a bit scary also")
            4 -> furhat.ask(" What do you mean by declaring my will to do therapy. I mean, I am here after all " +
                    "so in a way I am already doing therapy am I not")
        }
    }

    onResponse<RuminationBlock2> {

        it.intent.ruminate
        it.intent.feel
        it.intent.notice
        it.intent.specify
        it.intent.intellect

        goto(Counter2)
    }

    onResponse<VagueBlock1> {
        goto(DeclareWill)
    }


    onResponse<ProjectionBlock2> {
        goto(DeclareWill)
    }

    onResponse<HypotheticalSpeechBlock2> {
        goto(DeclareWill)
    }

    onResponse<DefianceBlock2> {
        goto(DeclareWill)
    }

    onResponse<AnxietyBlock2> {
        goto(DeclareWill)
    }
}

val Anxiety2Goto : State = state {
    onEntry {
        goto(Anxiety2)
    }
}


val Anxiety2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" I do want to look at it, I just started feeling really uncomfortable right now")
            1 -> furhat.ask(" There is this strange feeling in my stomach that is coming up")
            2 -> furhat.ask(" Do you have a tylenol? I got a headache out of nowhere")
            3 -> furhat.ask(" I don't know why but I just started feeling really cold and sweaty all of a sudden")
            4 -> furhat.ask(" I do want to explore my feeling, it's just my heart started beating really fast " +
                    "so it's difficult")
        }
    }

    onResponse<AnxietyBlock2> {

        goto(Counter2)
    }

    onResponse<ProjectionBlock2> {
        goto(DeclareWill)
    }

    onResponse<HypotheticalSpeechBlock2> {
        goto(DeclareWill)
    }

    onResponse<DefianceBlock2> {
        goto(DeclareWill)
    }

    onResponse<RuminationBlock2> {
        goto(DeclareWill)
    }
}


val Resolution2 : State = state {

    onEntry {
        furhat.say("Great, you have successfully completed this module, as the patient declared their will to do " +
                " therapy. Once the patient has explicitly expressed their desire to work on the problem with you, you " +
                " have permission to dig deeper into the issue. What to do at this point will be explored in future modules. ")
        furhat.ask(" If you would like to go over this module again, say repeat. If not, say no.")

    }

    onResponse<Repeat> {
        goto(SecondModule)
    }

    onResponse<No> {
        goto(Idle)
    }
}
