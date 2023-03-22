package furhatos.app.scenario10.nlu


import furhatos.nlu.*
import furhatos.nlu.grammar.Grammar
import furhatos.nlu.kotlin.grammar
import furhatos.nlu.common.Number
import furhatos.util.Language


// Intents
class No() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("no", "I don't think so", "definetely not", "I don't" , "I believe not", "no thanks", "no thank you")
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
        return listOf("I would like to hear it again", "Sorry can you repeat it", "sorry I didn't catch that", "Can you repeat it", "please repeat it again", "say it again")
    }
}



class FindRoom(var num : Room0? = null) : Intent()
{
    override fun getExamplesWithParaphrases(lang: Language): List<String>
    {
        return listOf("room @num", "room number @num","i want to get to room @num","How can i get to room @num","where can i find the room @num","Do you know where is room @num", "go to room @num", "I have a meeting in room @num", "I would like to go to room @num", "I want to reach room @num" )
    }
}
// Entity classes


class Room0() : EnumEntity(stemming = true, speechRecPhrases = true)
{
    override fun getEnum(lang: Language): List<String>
    {
        return listOf("1", "2", "3", "4", "5", "6", "7", "one", "two", "three", "four", "five", "six", "seven")
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

