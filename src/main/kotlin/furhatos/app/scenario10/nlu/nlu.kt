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
        return listOf("@location", "I have a meeting in @location", "I would like to go to @location", "I am here to see the @location", "I am here because I have a meeting", "I am here for the conference")
    }
}

class Wifi() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("wifi", "internet", "I need to connect to the wifi", "Can I connect to the internet?" ,"I need internet connexion to check my emails?", "I don't have internet on my phone", "I need wifi to get the Q R code", "I need to connect to the wifi", "I need Wi-Fi to check my emails", "I need Wi-Fi to get the key")
    }
}

class Work() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I work here", "no i work here", "I don't have an invitation I work here", "I am here every day" ,"I am here for work", "I have an office here", "I need to go to my office", "this is my work place", "I work at the robotarium", "I work here")
    }
}

class AllRight() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("it's all right", "it's okay", "I am just typing my name", "I am looking for the email" ,"I am slow", "I am entering my details", "I found the options", "I am good", "I am typing", "I am almost done", "Let me check", "I will check", "I need to check my emails", "I will check my emails", "hold on a second", "I am checking", "I need to check", "fine")
    }
}

class NotGood() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("There is a problem", "I am not able to enter my details", "I can't find the option", "there is an issue" ,"what do I do", "I am not sure what I am doing", "I am not sure what I need to do", "I can't find the email", "I cant use the system", "this is a bad system!", "not yet", "I lost the Q R code", "I lost the email", "I lost my phone", "I forgot my phone", "no I can't sign in", "I can't sign in", "I cannot sign in", "continue button is grey", "not good", "I am trying to use the kiosk", "not scanning my code", "not scanning the Q R code")
    }
}

class NoHost() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I am not able to add the host name", "the host name", "problem host name", "I cannot write the host name" ,"what do I do with the host name", "it is not selecting the host name", "I don't know the name of my host")
    }
}

class Cafe() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("cafe", "tea", "I need a coffee", "Do you have a restaurant?", "Do you have a cafe?", "Where is the closest cafe?", "Which way is the cafe?", "is there a cafe in the building?")
    }
}

class TalkAboutRobotarium() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("can you tell me anything about the building", "tell me about the robotarium", "how is it going at the robotarium", "how is the new building", "this is a very nice place", "how is the robotarium", "can you tell me about the building")
    }
}

class WC() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Loo","Bathroom", "toilet", "Where is the closest bathroom?", "Which way is the toilet?", "Do you have a toilet?", "Where is the closest toilet?", "Which way is the showers?", "is there a bathroom around here?", "yes I need the way to the" )
    }
}

class Howareyou : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("i'm good how are you?", "i'm good how are you?", "What about you?", "How is it going with you?", "I am good! how about you?", "I am doing great and you?", "Hi How are you", "I don't know how are you", "how are you", "how are you doing", "How are you today")
    }
}

class WhatisQR() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("what is a Q R code?","what's a Q R code?", "I don't know where to find the Q R code", "Where is the Q R code", "How do I find the Q R code?", "Where do I find the Q R code?", "which one is the Q R code?", "How do I get a Q R code?", " I am not sure", "I don't know", "I don't understand", "I have no idea", "I have no clue", "Who knows", "I haven't got the faintest idea", "what is a visitor QR code", "what is a QR code", "What's a QR code", "Whats that", "whats a QR", "what QR" )
    }
}

class Thanks() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Thanks","thank you" )
    }
}

class PrintLabel() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("it's printing my card","it's printing my label","it's printing something" )
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



class Iwill : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Ok I will check it out", "I will have a look", "I will go there", "nice! I will check it out", "I would love to see it", "That's interesting")
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
        return listOf("I think i need to register", "I would like to register", "I want to register", "register", "I don't have a meeting", "I don't have any appointment", "I just want to see the robotarium", "I'd like to be a guest")
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

class Help() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Help", "I need help", "I want to speak to a human", "call someone for help", "I need someone to help me")
    }
}

class HowRegister() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("How do I register", "can you help me register", "I need help to register", "help register", "I don't know how to register")
    }
}
