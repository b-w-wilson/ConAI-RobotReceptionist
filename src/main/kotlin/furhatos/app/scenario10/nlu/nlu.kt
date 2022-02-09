package furhatos.app.scenario10.nlu


import furhatos.nlu.*
import furhatos.nlu.grammar.Grammar
import furhatos.nlu.kotlin.grammar
import furhatos.nlu.common.Number
import furhatos.util.Language

class Delivery(var receiver : Receivers? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@receiver", "I have a delivery for @receiver", "I would like to give this package to @receiver", "there is a package for @receiver" , "there is a delivery for @receiver", "I just need to drop a package")
    }
}

class No() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("no", "I don't think so", "definetely not", "I don't" , "I believe not")
    }
}

class Yes() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("yes", "I have a Q R code", "I should have a Q R code", "I think I do" , "I believe so", "I do have one", "I have it", "I should have it")
    }
}
class Meeting(var location : Map? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@location", "I have a meeting in @location", "I would like to go to @location", "I am here to see the @location")
    }
}

class Wifi() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("wifi", "internet", "I need to connect to the wifi", "Can I connect to the internet?" ,"I need internet connexion to check my emails?", "I don't have internet on my phone", "I need wifi to get the Q R code", "I need to connect to the wifi")
    }
}

class Cafe() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("cafe", "tea", "I need a coffee", "Do you have a restaurant?", "Do you have a cafe?", "Where is the closest cafe?", "Which way is the cafe?", "is there a cafe in the building?")
    }
}

class WC() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Loo","Bathroom", "toilet", "Where is the closest bathroom?", "Which way is the toilet?", "Do you have a toilet?", "Where is the closest toilet?", "Which way is the showers?", "is there a bathroom around here?", "yes I need the way to the" )
    }
}

class WhatisQR() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("what is a Q R code?","what's a Q R code?", "I don't know where to find the Q R code", "Where is the Q R code", "How do I find the Q R code?", "Where do I find the Q R code?", "which one is the Q R code?", "How do I get a Q R code?", " I am not sure", "I don't know", "I don't understand", "I have no idea", "I have no clue", "Who knows", "I haven't got the faintest idea", "what is a visitor QR code", "what is a QR code", "What's a QR code", "Whats that" )
    }
}

class Thanks() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Thanks","thank you" )
    }
}

class Map : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("conference center", "room 3", "room 4", "room 2")
    }
}

class Receivers : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Professor john smith", "mister john smith", "john smith")
    }
}

class Good : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("i'm good thanks", "I am fine", "I am doing great thank you", "very good", "I am doing great")
    }
}

class Howareyou : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("i'm good how are you?", "What about you?", "How is it going with you?", "I am good! how about you?", "I am doing great and you?")
    }
}

class Iwill : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Ok I will check it out", "I will have a look", "I will go there", "nice! I will check it out", "I would love to see it", "That's interesting")
    }
}

class Notgood : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("not yet", "I lost the Q R code", "I lost the email", "I lost my phone", "I forgot my phone")
    }
}

class Checkin : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("check in", "checkin", "I am here to check in", "I would like to check in", "I need to check in", "I think I need to check in", "I probably need to check in")
    }
}

class Checkout : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("check out", "I need to check out", "I am leaving", "I finished",  "check out please")
    }
}

class Register : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("I think i need to register", "I would like to register", "I want to register", "register", "I don't have a meeting", "I don't have any appointment", "I just want to see the robotarium")
    }
}

class Notsure : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("I don't know", "I am not sure", "I lost the qr code", "I don't understand", "I left my phone", "I have no idea", "I have no clue", "Who knows", "I haven't got the faintest idea", "not sure")
    }
}

class Bye(var receiver : Receivers? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Bye", "Goodbye", "I have to go now", "We have to go now")
    }
}

