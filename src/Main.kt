abstract class Staff(val name: String, val salary: Int) {
    abstract fun getRole():String


}

class Nurse(name:String, private val shift:String,salary: Int = 500) : Staff(name, salary) {
    fun logShift() {
        println("[$name] is starting [$shift] shift")
    }

    override fun getRole(): String {
        return "Nurse"
    }

    fun getShift():String {
        return shift;
    }
}

class Doctor(name:String, salary: Int = 1500) : Staff(name, salary) {
    override fun getRole(): String {
        return "Doctor"
    }
}


fun generateReport( staff:Staff, notes:String) :String {
    return "Report for ${staff.name}: Notes: $notes"
}

fun main() {
    val staffMembers = mutableListOf<Staff>(Doctor("John"),
                                    Nurse("Tracy","night"),
                                    Doctor("Hose"),
                                    Doctor("Michael"),
                                    Doctor("James"),
                                    Nurse("Michael","afternoon"),
                                    Nurse("Tanja","morning"))

    println("All nurses salary :${staffMembers.sumOf { it.salary }}")
    println("All doctors salary : ${staffMembers.any { it.salary > 1000 }}")
    println("All employees salary : ${staffMembers.sumOf { it.salary }}")

   val nurse = staffMembers.firstOrNull{it.salary == 5500}?.name ?: "No one to display"
    println(nurse)
}