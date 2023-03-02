package furhatos.app.scenario10.nlu


import furhatos.nlu.*
import furhatos.nlu.grammar.Grammar
import furhatos.nlu.kotlin.grammar
import furhatos.nlu.common.Number
import furhatos.util.Language


// Intents
class No() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("no", "I don't think so", "definetely not", "I don't" , "I believe not")
    }
}

class Yes() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("yes", "yes please" , "I believe so", "I think I do")
    }
}

class AllRight() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("it's all right", "it's okay", "I am good", "fine")
    }
}

class Howareyou : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("i'm good how are you?", "i'm good how are you?", "What about you?", "How is it going with you?", "I am good! how about you?", "I am doing great and you?", "Hi How are you", "I don't know how are you", "how are you", "how are you doing", "How are you today")
    }
}

class Thanks() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Thanks","thank you" )
    }
}

class Bye() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Bye", "Goodbye", "I have to go now", "We have to go now", "See you later")
    }
}

class AskRepeat() : Intent()
{
    override fun getExamples(lang: Language): List<String> {
        return listOf("I would like to hear it again", "Sorry can you repeat it", "sorry", "Can you repeat it", "please repeat it again")
    }
}

//class FindStaff(var staff : Staff? =null) :Intent()
//{
//    override fun getExamples(lang: Language): List<String>
//    {
//        return listOf("i want to meet @staff", "i'm here to meet @staff","where can i find @staff")
//    }
//}

class FindRoom(var room : Room? = null) : Intent()
{
    override fun getExamples(lang: Language): List<String>
    {
        return listOf( "I have a appointment in @room", "I would like to go to @room", "I am here to see the @room", "i want to get to @room","How can i get to @room","I would like to know, where can i find the @room","Do you know where is @room", "go to @room", "want to go at @room")
    }
}

class FindCompany(var company : Company? = null) : Intent()
{
    override fun getExamples(lang: Language): List<String>
    {
        return listOf("i want to get to @company","How can i get to @company","I would like to know, where can i find the @company","Do you know where is @company", "go to @company", "want to go at @company", "I have a meeting in @company", "I would like to go to @company", "I am here to see the @company" )
    }
}
// Entity classes


//class Staff() : EnumEntity(stemming = true, speechRecPhrases = true)
//{
//    override fun getEnum(lang: Language): List<String>
//    {
//        return listOf("Steve","person")
//    }
//}

class Room(): EnumEntity(stemming = true, speechRecPhrases = true)
{
    override fun getExamples(lang: Language?): List<String>
    {
        return listOf("conference hall","room1","room2","room3","room4")
    }
}


class Company() : EnumEntity(stemming = true, speechRecPhrases = true)
{
    override fun getEnum(lang: Language): List<String>
    {
        return listOf("touch lab","crover up", "NR management", "Cromacity Desk", "RAS Lab", "Shakey Robotics", "NR Engineers")
    }
}

class Good : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("i'm good thanks", "I am fine", "I am doing great thank you", "very good", "I am doing great")
    }
}

class Notsure : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("I don't know", "I am not sure", "I don't understand", "I have no idea", "I have no clue", "Who knows", "I haven't got the faintest idea", "not sure")
    }
}

