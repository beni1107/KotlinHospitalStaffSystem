abstract class Staff(val name: String) {
    abstract fun getRole():String
}

class Nurse(name:String, private val shift:String) : Staff(name) {
    fun logShift() {
        println("[$name] is starting [$shift] shift")
    }

    override fun getRole(): String {
        return "Nurse"
    }
}

class Doctor(name:String) : Staff(name) {
    override fun getRole(): String {
        return "Doctor"
    }
}


fun generateReport( staff:Staff, notes:String) :String {
    return "Report for ${staff.name}: Notes: $notes"
}

fun main() {
    val nurse1 = Nurse("Elena","Night")
    nurse1.logShift()
    val staffMembers = listOf<Staff>(Doctor("John"),
                                    Nurse("Tracy","night"),
                                    Doctor("Hose"),
                                    Doctor("Michael"),
                                    Doctor("James"),
                                    Nurse("Michael","afternoon"),
                                    Nurse("Tanja","morning"))
        .forEach { p -> println(" Name : ${p.name}  Role : ${p.getRole()}") }
}