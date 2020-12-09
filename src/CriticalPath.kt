import java.io.*


fun main() {
    CPHandling.CpHandle()
    calcCPath(CPHandling.TasksTogether)
    PrintRes(CPHandling.TasksTogether)
}

fun calcCPath(tasks: Collection<Task>) {
    val completed = hashSetOf<Task>()
    val remaining = tasks.toHashSet()


    while (remaining.isNotEmpty()) {
        var progress = false

        // find task to calculate
        val it = remaining.iterator()
        while (it.hasNext()) {
            val task = it.next()
            if (completed.containsAll(task.dependencies)) {
                val critical = task.dependencies.map { it.criticalCost }.maxOrNull() ?: 0
                task.criticalCost = critical + task.cost
                completed.add(task)
                it.remove()
                progress = true
            }
        }
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




class Task(
    var name: String,
    var cost: Int,
    vararg dependencies: Task
) {
    var criticalCost = 0

    var earlyStart = 0

    var earlyFinish = -1

    var latestStart = 0

    var latestFinish = 0

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