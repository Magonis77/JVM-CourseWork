package ProjectSystem/*
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.Set


object CriticalPath {
    @JvmStatic
    fun main(args: Array<String>) {
        //The example dependency graph from
        //http://www.ctl.ua.edu/math103/scheduling/scheduling_algorithms.htm
        val allTasks = HashSet<Task>()
        val end = Task("End", 0)
        val F = Task("F", 1, end)
        val A = Task("A", 3, end)
        val X = Task("X", 4, F, A)
        val Q = Task("Q", 2, A, X)
        val start = Task("Start", 0, Q)
        allTasks.add(end)
        allTasks.add(F)
        allTasks.add(A)
        allTasks.add(X)
        allTasks.add(Q)
        allTasks.add(start)
        println("Critical Path: " + Arrays.toString(criticalPath(allTasks)))
    }

    fun criticalPath(tasks: Set<Task>?): Array<Task> {
        //tasks whose critical cost has been calculated
        val completed = HashSet<Task>()
        //tasks whose ciritcal cost needs to be calculated
        val remaining = HashSet(tasks)

        //Backflow algorithm
        //while there are tasks whose critical cost isn't calculated.
        while (!remaining.isEmpty()) {
            var progress = false

            //find a new task to calculate
            val it = remaining.iterator()
            while (it.hasNext()) {
                val task = it.next()
                if (completed.containsAll(task.dependencies)) {
                    //all dependencies calculated, critical cost is max dependency
                    //critical cost, plus our cost
                    var critical = 0
                    for (t in task.dependencies) {
                        if (t.criticalCost > critical) {
                            critical = t.criticalCost
                        }
                    }
                    task.criticalCost = critical + task.cost
                    //set task as calculated an remove
                    completed.add(task)
                    it.remove()
                    //note we are making progress
                    progress = true
                }
            }
            //If we haven't made any progress then a cycle must exist in
            //the graph and we wont be able to calculate the critical path
            if (!progress) throw RuntimeException("Cyclic dependency, algorithm stopped!")
        }

        //get the tasks
        val ret: Array<Task> = completed.toArray(arrayOfNulls(0))
        //create a priority list
        Arrays.sort(ret, Comparator { o1, o2 -> //sort by cost
            val i = o2.criticalCost - o1.criticalCost
            if (i != 0) return@Comparator i

            //using dependency as a tie breaker
            //note if a is dependent on b then
            //critical cost a must be >= critical cost of b
            if (o1.isDependent(o2)) return@Comparator -1
            if (o2.isDependent(o1)) 1 else 0
        })
        return ret
    }

    //A wrapper class to hold the tasks during the calculation
    class Task(//a name for the task for printing
        var name: String, //the actual cost of the task
        var cost: Int, vararg dependencies: Task
    ) {
        //the cost of the task along the critical path
        var criticalCost = 0

        //the tasks on which this task is dependant
        var dependencies = HashSet<Task>()
        override fun toString(): String {
            return "$name: $criticalCost"
        }

        fun isDependent(t: Task): Boolean {
            //is t a direct dependency?
            if (dependencies.contains(t)) {
                return true
            }
            //is t an indirect dependency
            for (dep in dependencies) {
                if (dep.isDependent(t)) {
                    return true
                }
            }
            return false
        }

        init {
            for (t in dependencies) {
                this.dependencies.add(t)
            }
        }
    }
}*/
