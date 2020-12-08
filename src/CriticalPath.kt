import java.io.*


fun main() {
    CPHandling.CpHandle()
    calcCPath(CPHandling.TasksTogether)
    PrintRes(CPHandling.TasksTogether)
}

fun calcCPath(tasks: Collection<Task>) {
    // tasks - critical cost calculated
    val completed = hashSetOf<Task>()
    // tasks - critical cost to be calculated
    val remaining = tasks.toHashSet()

    // while there are tasks whose critical cost isn't calculated.
    while (remaining.isNotEmpty()) {
        var progress = false

        // find task to calculate
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
    calcLatest(tasks, maxCost)
    calcLatest(tasks)
}

fun calcLatest(tasks: Collection<Task>, maxCost: Int) = tasks.forEach { it.setLatest(maxCost) }

fun calcLatest(tasks: Collection<Task>) = initials(tasks).forEach {
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

fun PrintRes(tasks: Collection<Task>) {
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