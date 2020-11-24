import java.io.*

fun main() {
    val fileName1 = "CriticalPath.txt"
    val myfile1 = File(fileName1)

    myfile1.printWriter().use { out ->

        out.write("Task, Earliest Start , Earliest Finish , Latest Start, Latest Finish, Slack, Critical?")
    }
    val fileName2 = "InitialNodes.txt"
    val myfile2 = File(fileName2)

    myfile2.printWriter().use { out ->

        out.write("")
    }
    val fileName3 = "CriticalPathDays.txt"
    val myfile3 = File(fileName3)

    myfile3.printWriter().use { out ->

        out.write("")
    }

    val bufferedReader: BufferedReader = File("Tasks.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
    val fileName :String = "Tasks.txt"
    var i :Int = -1
    // using extension function readLines
    File(fileName).readLines().forEach {
        i++
    }
    if(i == 1){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val start = Task(split[1], int1)
        val allTasks = hashSetOf(start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 2){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[4], int2)
        val start = Task(split[1], int1, end)

        val allTasks = hashSetOf(end, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 3){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[7], int3)
        val A = Task(split[4], int2, end)
        val start = Task(split[1], int1, A)

        val allTasks = hashSetOf(end, A, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 4){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[10], int4)
        val A = Task(split[7], int3, end)
        val F = Task(split[4], int2, A)
        val start = Task(split[1], int1, F)

        val allTasks = hashSetOf(end, A, F, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 5){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[13], int5)
        val A = Task(split[10], int4, end)
        val F = Task(split[7], int3, A)
        val Q = Task(split[4], int2, F, A)
        val start = Task(split[1], int1, Q)

        val allTasks = hashSetOf(end, A, F, Q, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 6){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[16], int6)
        val F = Task(split[13], int5, end)
        val A = Task(split[10], int4, end)
        val X = Task(split[7], int3, F, A)
        val Q = Task(split[4], int2, A, X)
        val start = Task(split[1], int1, Q)
        val allTasks = hashSetOf(end, F, A, X, Q, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 7){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val int7 = split[20].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[19], int7)
        val Z = Task(split[16], int6, end)
        val F = Task(split[13], int5, end)
        val A = Task(split[10], int4, F, Z)
        val X = Task(split[7], int3, F, A, Z)
        val Q = Task(split[4], int2, A, X, F)
        val start = Task(split[1], int1, Q)
        val allTasks = hashSetOf(end, Z, F, A, X, Q, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 8){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val int7 = split[20].replace("days", "").replace(" ", "").toInt()
        val int8 = split[23].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[22], int8)
        val G = Task(split[19], int7, end)
        val Z = Task(split[16], int6, end)
        val F = Task(split[13], int5, Z, G)
        val A = Task(split[10], int4, F, Z)
        val X = Task(split[7], int3, F, A, Z, G)
        val Q = Task(split[4], int2, A, X, F, Z)
        val start = Task(split[1], int1, Q)
        val allTasks = hashSetOf(end, G, Z, F, A, X, Q, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 9){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val int7 = split[20].replace("days", "").replace(" ", "").toInt()
        val int8 = split[23].replace("days", "").replace(" ", "").toInt()
        val int9 = split[26].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[25], int9)
        val H = Task(split[22], int8, end)
        val G = Task(split[19], int7, end)
        val Z = Task(split[16], int6, G, H)
        val F = Task(split[13], int5, Z, G, H)
        val A = Task(split[10], int4, F, Z, G)
        val X = Task(split[7], int3, F, A, Z)
        val Q = Task(split[4], int2, A, X, F)
        val start = Task(split[1], int1, Q)
        val allTasks = hashSetOf(end, H, G, Z, F, A, X, Q, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
    if(i == 10){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val int7 = split[20].replace("days", "").replace(" ", "").toInt()
        val int8 = split[23].replace("days", "").replace(" ", "").toInt()
        val int9 = split[26].replace("days", "").replace(" ", "").toInt()
        val int10 = split[29].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[28], int10)
        val D = Task(split[25], int9, end)
        val H = Task(split[22], int8, end)
        val G = Task(split[19], int7, D, H)
        val Z = Task(split[16], int6, G, H, D)
        val F = Task(split[13], int5, Z, G, H)
        val A = Task(split[10], int4, F, Z, G)
        val X = Task(split[7], int3, F, A, Z)
        val Q = Task(split[4], int2, A, X, F)
        val start = Task(split[1], int1, Q)
        val allTasks = hashSetOf(end, D, H, G, Z, F, A, X, Q, start)

        calculateCriticalPath(allTasks)
        prettyPrintResult(allTasks)
    }
}

fun calculateCriticalPath(tasks: Collection<Task>) {
    // tasks whose critical cost has been calculated
    val completed = hashSetOf<Task>()
    // tasks whose critical cost needs to be calculated
    val remaining = tasks.toHashSet()

    // Backflow algorithm
    // while there are tasks whose critical cost isn't calculated.
    while (remaining.isNotEmpty()) {
        var progress = false

        // find a new task to calculate
        val it = remaining.iterator()
        while (it.hasNext()) {
            val task = it.next()
            if (completed.containsAll(task.dependencies)) {
                // all dependencies calculated, critical cost is max dependency critical cost, plus our cost
                val critical = task.dependencies.map { it.criticalCost }.maxOrNull() ?: 0
                task.criticalCost = critical + task.cost
                // set task as calculated an remove
                completed.add(task)
                it.remove()
                // note we are making progress
                progress = true
            }
        }
        // If we haven't made any progress then a cycle must exist in
        // the graph and we wont be able to calculate the critical path
        if (!progress) throw RuntimeException("Cyclic dependency, algorithm stopped!")
    }

    // get the cost
    val maxCost = tasks.map { it.criticalCost }.maxOrNull() ?: -1
   val test = "$maxCost"
    val fileName = "CriticalPathDays.txt"
    val myfile = File(fileName)
    myfile.printWriter().use { out ->
        out.write(test)

    }
    calculateLatest(tasks, maxCost)
    calculateEarly(tasks)
}

fun calculateLatest(tasks: Collection<Task>, maxCost: Int) = tasks.forEach { it.setLatest(maxCost) }

fun calculateEarly(tasks: Collection<Task>) = initials(tasks).forEach {
    it.earlyStart = 0
    it.earlyFinish = it.cost
    it.setEarlyForDependencies()

}

fun initials(tasks: Collection<Task>): Collection<Task> {
    val dependencies = tasks.flatMap { it.dependencies }.toSet()
    return tasks.filter { it !in dependencies }.also {
        val test = ("${it.joinToString { node -> node.name }}")
        val fileName = "InitialNodes.txt"
        val myfile = File(fileName)
        val inputAsString = myfile.bufferedReader().use { it.readText() }
        myfile.printWriter().use { out ->
            out.write(inputAsString)
            out.write(test)

        }
    }
}

fun prettyPrintResult(tasks: Collection<Task>) {
    val format = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n"
    tasks.sortedWith { o1, o2 -> o1.name.compareTo(o2.name) }.forEach {
        try {
            val writer = FileWriter("CriticalPath.txt", true)
            writer.write("${it.name}/ ${it.earlyStart}/ ${it.earlyFinish}/ ${it.latestStart}/ ${it.latestFinish}/ ${it.latestStart - it.earlyStart}/ ${if (it.isCritical()) "Yes" else "No"}")
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}



// A wrapper class to hold the tasks during the calculation
class Task(
    var name: String, // a name for the task for printing
    var cost: Int, // the actual cost of the task
    vararg dependencies: Task
) {
    // the cost of the task along the critical path
    var criticalCost = 0

    // the earliest start
    var earlyStart = 0

    // the earliest finish
    var earlyFinish = -1

    // the latest start
    var latestStart = 0

    // the latest finish
    var latestFinish = 0

    // the tasks on which this task is dependant
    var dependencies = hashSetOf(*dependencies)

    fun setLatest(maxCost: Int) {
        latestStart = maxCost - criticalCost
        latestFinish = latestStart + cost
    }

    fun setEarlyForDependencies() {
        val completionTime = earlyFinish
        dependencies.forEach {
            if (completionTime >= it.earlyStart) {
                it.earlyStart = completionTime
                it.earlyFinish = completionTime + it.cost
            }
            it.setEarlyForDependencies()
        }
    }

    fun isCritical() = earlyStart == latestStart

    fun isDependent(t: Task): Boolean = dependencies.contains(t) || dependencies.any { it.isDependent(t) }
}