package furhatos.app.scenario10.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.scenario10.nlu.Map
import furhatos.app.scenario10.nlu.*
import furhatos.gestures.Gestures
import furhatos.nlu.MultiIntentClassifierFactory.Companion.default
import furhatos.nlu.common.Thanks
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import khttp.structures.authorization.BasicAuthorization
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.*
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


//val username = "apiuser2"
//val password = "BSyMe883eCrhta8f"

val username = "apiuser"
val password = "69gZHS8Sy47mwQgE"

val currenttime = LocalDateTime.now()
val twominago = currenttime.minusMinutes(2)

//val BASE_URL = "https://sandbox.tdscloud.ie/suite_api/prebookVisitor" // Endpoint
val BASE_URL = "https://sandbox.tdscloud.ie/suite_api/addPerson" // Endpoint
val GET_PERSON = "https://heriotwattuniversitylive.tdscloud.ie/suite_api/suite_api/getPerson?person_code=SGTDS001&person_type=E" // Endpoint
//val GET_URL = "https://sandbox.tdscloud.ie/suite_api/getPerson?person_code=HWU002&person_type=E" // Endpoint
//var GET_VISITOR = "https://sandbox.tdscloud.ie/suite_api/getVisitors?from_due_date=2021-08-10T09:00:00&to_due_date=2021-08-17T19:00:00"
//var GET_VISITOR = "https://sandbox.tdscloud.ie/suite_api/getVisitors?from_due_date=2021-11-03T01:00:00&to_due_date=2021-11-04T18:20:00"
var GET_VISITOR = "https://heriotwattuniversitylive.tdscloud.ie/suite_api/getVisitors?from_due_date=2021-11-03T01:00:00&to_due_date=2021-11-04T18:20:00"


// users.count
val Start : State = state(Interaction) {
    onEntry {

        furhat.say({ random {
            +"Hello! Welcome to the national robotarium!"
            +"Good morning! Welcome to the national robotarium!"
            +"Hi! Welcome to the national robotarium!"
        }})
        furhat.gesture(Gestures.BigSmile)
        goto(code())
    }


}

fun code() : State = state(Interaction) {
    onEntry {

        furhat.ask("Do you have a visitor QR code?")
        furhat.gesture(Gestures.Smile)

    }

    onUserEnter(instant = false) {
        furhat.attend(users.other)
        delay(1000)
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Hi! I will be with you shortly")
//        furhat.gesture(Gestures.BigSmile)

        furhat.attend(users.other)
        furhat.ask("So did you find the QR code?")
    }
    onUserLeave (instant = false) {
        goto(userOutOfView)
    }
    onResponse<Yes> {

        furhat.gesture(Gestures.BigSmile)
        furhat.say("Great! Can you please scan the QR code in the Kiosk?")
        goto(usingkiosk())

    }
    onResponse<WhatisQR> {

        furhat.gesture(Gestures.BigSmile)
        goto(notsure())

    }

    onResponse<No> {
        furhat.gesture(Gestures.Oh)
        furhat.ask("Are you wanting to check in, check out, or register? ")
        //goto(donthaveqr())

    }
    onResponse <Checkin> {
        furhat.gesture(Gestures.Nod)
        furhat.say("To check in, You can use the Kiosk! Just enter your email address, or the reference code if you have one!")
        goto(usingkiosk())
    }
    onResponse <Checkout> {
        furhat.say("To check out, Just scan the label in the Kiosk!")
        furhat.gesture(Gestures.BigSmile)
        goto(CheckedOut())
    }
    onResponse <Meeting> {
        furhat.say("You need to check in prior to your meeting! You can use the kiosk to check in, Just enter your email address, or the reference code if you have one!")
        furhat.gesture(Gestures.BigSmile)
        goto(usingkiosk())
    }
    onResponse <Register> {
        furhat.say("To register, You can use the kiosk, just select the option Quick register")
        furhat.gesture(Gestures.BigSmile)
        goto(usingkiosk())
    }
    onResponse <Cafe> {
        furhat.say("There is a cafe in the first floor, you just need to Register on the kiosk, using the option quick register before going to the cafe")
        furhat.gesture(Gestures.BigSmile)
        goto(usingkiosk())
    }
    onResponse <Notsure> {

        goto(notsure())
    }
    onResponse<Wifi> {
        furhat.gesture(Gestures.BigSmile)
        furhat.say("You can connect to our guest wifi called the national robotarium wifi and the password is N R 2 0 2 1")
    }
    onResponse<Delivery> {

        val receiver = it.intent.receiver

        if (receiver == null) {

            goto(AskReceiverInfo())
        } else {
            //propagate()
            goto(ReceiverInfo(receiver))
        }
    }

    onUserEnter(instant = false) {

        furhat.attend(users.other)
        delay(1000)
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Hi! I will be with you shortly")
//        furhat.gesture(Gestures.BigSmile)
        furhat.attend(users.other)

    }
    onUserLeave (instant = false) {
        goto(userOutOfView)
    }

}

fun usingkiosk() : State = state(Interaction) {
    onEntry {

        furhat.say("You can follow the Kiosk instructions, Let me know if you need any help!")
        goto(CheckedIn())
    }
    onUserEnter(instant = false) {

        furhat.attend(users.other)
        delay(1000)
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Hi! I will be with you shortly")
//        furhat.gesture(Gestures.BigSmile)

        furhat.attend(users.other)
        furhat.ask("Let me know if you need any assistance with the kiosk")
    }
    onUserLeave (instant = false) {
        goto(userOutOfView)
    }
}

fun notsure() : State = state(Interaction) {
    onEntry {

        furhat.ask("Did you receive an invitation email with something that looks like a barcode?")
        furhat.gesture(Gestures.BrowRaise)
    }
    onUserEnter(instant = false) {

        furhat.attend(users.other)
        delay(1000)
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Hi! I will be with you shortly")
//        furhat.gesture(Gestures.BigSmile)

        furhat.attend(users.other)
        furhat.ask("Did you receive an invitation email with something that looks like a barcode?")
    }
    onUserLeave (instant = false) {
        goto(userOutOfView)
    }
    onResponse<Yes> {

        furhat.gesture(Gestures.BigSmile)
        furhat.say("Great! Can you please scan the QR code in the Kiosk?")
        goto(usingkiosk())

    }
    onResponse<Wifi> {
        furhat.say("You can connect to our guest wifi called the national robotarium wifi and the password is N R 2 0 2 1")
        delay(3000)
        furhat.ask("Did you find the invitation with the QR code?")
    }
    onResponse<No> {
        furhat.gesture(Gestures.Oh)
        furhat.say("No worries! If you are a walk-in visitor please use the kiosk, select the option quick register")
        goto(usingkiosk())

    }
}


fun ReceiverInfo(receiver : Receivers) : State = state(Interaction) {
    onEntry {
        val receiver = receiver

        if (receiver == null) {

            goto(AskReceiverInfo())
        } else {
            //propagate()
            goto(ConfirmReceiverInfo(receiver))
        }
    }
}


fun ConfirmReceiverInfo(receiver : Receivers): State = state(Interaction) {
    onEntry {
        furhat.ask("Great! Just to confirm you have a package for ${receiver.text}? in office number 3.5?")
    }
    onResponse<Yes> {

        furhat.gesture(Gestures.BigSmile)
        furhat.ask("Great, can you please drop the delivery on the desk, I am sending a notification to ${receiver.text}")

    }

    onResponse<No> {
        furhat.gesture(Gestures.Oh)
        furhat.ask("Appologies! I got the wrong name, who is the delivery for?")
        //goto(donthaveqr())

    }
    onResponse<Delivery> {
        val receiver = it.intent.receiver

        if (receiver == null) {

            goto(AskReceiverInfo())
        } else {
            //propagate()
            goto(ReceiverInfo(receiver))
        }

    }
    onUserEnter(instant = false) {

        furhat.attend(users.other)
        delay(1000)
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Hi! I will be with you shortly")
//        furhat.gesture(Gestures.BigSmile)

        furhat.attend(users.other)
        furhat.say("Do you know who is the delivery for?")
    }
    onUserLeave (instant = false) {
        goto(userOutOfView)
    }
}


fun AskReceiverInfo(): State = state(Interaction) {

    onEntry {
        furhat.ask("Thank you! Do you know who is the delivery for?")
    }
    onUserEnter(instant = false) {

        furhat.attend(users.other)
        delay(1000)
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Hi! I will be with you shortly")
//        furhat.gesture(Gestures.BigSmile)

        furhat.attend(users.other)
        furhat.say("Do you know who is the delivery for?")
    }
    onUserLeave (instant = false) {
        goto(userOutOfView)
    }
    onResponse<Delivery> {
        val receiver = it.intent.receiver

        if (receiver == null) {

            furhat.ask("Do you know who is the delivery for?")
        } else {
            //propagate()
            goto(ReceiverInfo(receiver))
        }

    }

}

fun CheckedIn() : State = state(Interaction) {
    onEntry {
        delay(3000)
        val response = call(query()) as String

        furhat.ask(response)

        furhat.gesture(Gestures.BigSmile)
    }
    onUserEnter(instant = false) {

        furhat.attend(users.other)
        delay(1000)
        furhat.gesture(Gestures.BigSmile)
        furhat.say({ random {
            +"Hello! Welcome to the national robotarium!"
            +"Good morning! Welcome to the national robotarium!"
            +"Hi! Welcome to the national robotarium!"
        }})
        goto(code())
    }
    onUserLeave (instant = false) {
        furhat.say("Enjoy your visit! Bye")

    }

}

fun CheckedOut() : State = state(Interaction) {
    onEntry {
        delay(3000)
        furhat.say("Thank you for your visit Meriam!")
        furhat.gesture(Gestures.BigSmile)
       // goto(idle(""))
    }
    onUserEnter(instant = false) {

        furhat.attend(users.other)
        delay(1000)
        furhat.gesture(Gestures.BigSmile)
        furhat.say({ random {
            +"Hello! Welcome to the national robotarium!"
            +"Good morning! Welcome to the national robotarium!"
            +"Hi! Welcome to the national robotarium!"
        }})
        goto(code())
    }
    onUserLeave (instant = false) {
        furhat.say("Enjoy the rest of your day! Bye")

    }

}

var userOutOfView = state(Interaction) {
    var requestIsUserThere = 0
    onEntry {
        requestIsUserThere++
        furhat.ask("Are you still there? Can you come closer?")
    }
    onNoResponse {

        if (requestIsUserThere < 2) {
            requestIsUserThere++
            furhat.ask("Are you still there? Can you come closer?")
        } else {
            dialogLogger.endSession()
      //      call(query("terminate"))
      //      goto(idle(""))
        }
    }
    onResponse {

        furhat.ask("I can't quite see you. Can you come closer?")
    }
    onUserEnter {
        //var currentUserID = users.current
        //currentUserID = it.persistentData.persistentUserId.toString()
        //if(currentUserID == it.persistentData.persistentUserId.toString() ) {
            furhat.ask("There you are!")
            //goto(idle(currentUserID))
        //}else{
        //    //TODO restart interaction with new user
            //goto(idle(""))
        //}

    }
}

// State to conduct the query to the API
fun query() = state {
    onEntry {
        //val question = question.replace("+", " plus ").replace(" ", "+")
        //val query = "$BASE_URL?i=$question"

/////////////////////////////////////////////////////////////       POST        //////////////////////////


        //    val addperson = mapOf("person_code" to "SGTDS002", "person_type" to "E", "forename" to "Alice", "surname" to "Jupiter", "zone_code" to "101001", "department_code" to "1", "sub_department_code" to "", "title" to "", "known_as" to "Alice", "date_of_birth" to "2021-08-16T09:45:00", "company_code" to "", "job_title_code" to "", "start_date" to "2021-08-16T09:45:00", "leave_date" to "2021-08-16T09:45:00","active_ind" to "Y","person_category_code" to "","email_address" to "alice@gmail.com", "mobile_phone_number" to "","home_phone_number" to "","access_reason_code" to "","middle_name" to "", "allowed_visitors_ind" to "Y", "notification_username" to "", "address_1" to "", "address_2" to "", "address_3" to "", "post_code" to "")
        val currenttime = ZonedDateTime.now(ZoneId.of( "Europe/London"))
                .truncatedTo(ChronoUnit.MINUTES)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val dateTime = LocalDateTime.now()

        val twominago = dateTime.minusMinutes(2)

        println("Current date and time: $currenttime")
        println("two minutes ago: $twominago")

        //   println("Sending to TDS server: $addperson")

        //   val res = khttp.post(BASE_URL, auth=BasicAuthorization(username, password), data= addperson).text
        //   println("Response from TDS : $res")


//////////////////////////////////////////////////////////     GET   //////////////////////////////////////////
        val slotresp = khttp.get(GET_VISITOR, auth = BasicAuthorization(username, password)).jsonObject
        println("the slots are: $slotresp")
        val builderresponse = StringBuilder()
        val parser: Parser = Parser.default()

        //val jslot = slotres.getJSONObject("items")
       // val slotres= khttp.get(GET_PERSON, auth = BasicAuthorization(username, password)).jsonObject
        val jresponse = slotresp.getJSONArray("items")
        println("the slots are: $slotresp")
        //println("the slots name are: $jresponse")
        println("${jresponse::class.simpleName}")

        for (i in 0 until jresponse.length()) {
            val item = jresponse.getJSONObject(i)
            val parser: Parser = Parser.default()
            val stringarray: StringBuilder = StringBuilder("$item")
            //println("I am here")
            // val array = parse(stringarray) as JsonArray<JsonObject>
            val jsonitem: JsonObject = parser.parse(stringarray) as JsonObject
            val checked_in_date_time = jsonitem.string("checked_in_date_time")

            if(checked_in_date_time != null ) {
                val checked_in_date = LocalDateTime.parse(checked_in_date_time)
                if (checked_in_date.isAfter(twominago) == true ){
                    val name = jsonitem.string("forename")
                    val email = jsonitem.string("email_address")
                    val visitreason = jsonitem.string("visit_reason_description")
                    println("yeeeeeaaaaahhhh! this is the person in front of the robot!!")
                    println("the name is: $name")
                    println("the email is: $email")
                    println("the checked in date is: $checked_in_date_time")



                    //    val jtext = jsonitem.string("text")
                    builderresponse.append("Hello ")
                    builderresponse.append(name)
                    builderresponse.append(" I can see you are here for ")
                    builderresponse.append(visitreason)
                }else{
                    //println("checked in a long time ago")
                }
            }
        }

        val response = builderresponse.toString()
        println("the response Furhat will say is: $response")
        // Reply to user depending on the returned response
        val reply = when {

            else -> response
        }
        //  Return the response
        terminate(reply)
    }

}
