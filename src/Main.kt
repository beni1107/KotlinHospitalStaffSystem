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

class Patient ( val name:String, val severity:Int  )


fun generateReport( staff:Staff, notes:String) :String {
    return "Report for ${staff.name}: Notes: $notes"
}

fun main() {
    val staffMembers = mutableListOf<Staff>(
        Doctor("John"),
        Nurse("Tracy", "night"),
        Doctor("Carl"),
        Doctor("Michael"),
        Doctor("James"),
        Nurse("Michael", "afternoon"),
        Nurse("Tanja", "morning")
    )

    println("All nurses salary :${staffMembers.sumOf { it.salary }}")
    println("All doctors salary : ${staffMembers.any { it.salary > 1000 }}")
    println("All employees salary : ${staffMembers.sumOf { it.salary }}")

    val nurse = staffMembers.firstOrNull { it.salary == 5500 }?.name ?: "No one to display"
    println(nurse)
    val earnsAbove800 = staffMembers.find { it.salary > 800 }?.name ?: " No one to display"
    println(earnsAbove800)

    val zero = staffMembers.none { it.salary < 100 }
    println(zero)
    println()
    println()
    val allNurses = staffMembers.filterIsInstance<Nurse>()
    println(allNurses.elementAt(0).logShift())
    println(allNurses.firstOrNull()?.logShift() ?: "No one to display")

    val (highpaid, lowpaid) = staffMembers.partition { it.salary > 800 }
    println("High paid: ${highpaid.joinToString { "${it.name}   ${it.salary}" }}")
    println()
    println("Low paid: ${lowpaid.joinToString { "${it.name}   ${it.salary}" }}")

    val list2 = staffMembers.filterNot { it.name == "John" }
    println()
    println("Staff members excluding John")
    print(list2.joinToString { " ${it.name} " })

    println()
    println()
    println("---MAP---")
    val payrollLabels =
        staffMembers.map { "Name : ${it.name}  Salary : ${it.salary}  Yearly Salary : ${it.salary * 12}" }
    println(payrollLabels.joinToString("\n") { "-  $it" })
    println()
    val tmp = staffMembers.mapNotNull { if (it is Nurse) it.logShift() else null }
    println(tmp.joinToString("shift : ") { "$it" })

    val tax = staffMembers.map { (it.salary * 0.2).toInt() }.sumOf { it }
    println("Monthly tax on salaries :$tax")

    val depts = listOf("Cardiology", "ER", "Pediatrics")
    val temp = depts.map { deptname ->
        val leader = staffMembers.firstOrNull { it.name.startsWith(deptname[0]) }?.name ?: "TBD"
        "$deptname Leader: $leader"
    }
    println(staffMembers.any { it.salary == 500 })
    println(staffMembers.all { it.name.length > 2 })
    println(staffMembers.none { it.salary == 0 })

    println()
    println()

    val wards = listOf("Cardiology", "Emergency", "Pediatrics")
    val patients = listOf<Patient>(
        Patient("Matt", 7), Patient("Derick", 4),
        Patient("Jefrey", 9), Patient("Ema", 2), Patient("Sasha", 6),
        Patient("Lisa", 8)
    )

    val sumOf = patients.filter { it.severity >= 7 }.map { it.severity * 100 }.sumOf { it }
    println("Sum severinty : $sumOf")
    val bonus = staffMembers.filter { it.name != "John" }.map { it.name.length * 50 }.sumOf { it }
    println("Bonus je : $bonus")

    val doc = wards.map { wardname ->
        staffMembers.firstOrNull { it is Doctor && it.name.startsWith(wardname[0]) }?.name ?: "No Doctor found"
    }
    println(doc)
    val assignments = wards.map { wardName ->
        // We are looking at ONE wardName at a time here
        val doctor = staffMembers.firstOrNull {
            it is Doctor && it.name.startsWith(wardName.first(), ignoreCase = true)
        }

        // Now we return a string for this specific ward
        "Ward: $wardName -> ${doctor?.name ?: "No Doctor"}"
    }

    val doc2 = wards.map { ward ->
        // 1. Find the doctor (this returns a Doctor? or null)
        val foundDoctor = staffMembers.firstOrNull {
            it is Doctor && it.name.startsWith(ward.first(), ignoreCase = true)
        }

        // 2. The last line is the result for this ward
        "Ward: $ward -> ${foundDoctor?.name ?: "No Doctor"}"
    }

    doc2.forEach { println(it) }

    patients.filter { patient -> patient.severity > 2 }
        .map { patient -> if (patient.severity > 8) 1000 else 500 }
        .sumOf { cost -> cost }

   val message =  patients.firstOrNull{patient -> patient.severity == 10}?.let{ patient -> patient "Moving {"$pa "}}
   }
}