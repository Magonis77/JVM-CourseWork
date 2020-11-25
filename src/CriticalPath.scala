
import java.util
import java.util.Comparator

import scala.collection.convert.ImplicitConversions.{`collection asJava`, `iterator asJava`}
import scala.collection.immutable.HashSet

object CriticalPath {

  var maxCost: Int = 0

  var format: String = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n"

  def main(args: Array[String]): Unit = {
    // The example dependency graph
    val end: Task = new Task("End", 0)
    val F: Task = new Task("F", 2, end)
    val A: Task = new Task("A", 3, end)
    val X: Task = new Task("X", 4, F, A)
    val Q: Task = new Task("Q", 2, A, X)
    val start: Task = new Task("Start", 0, Q)
    val allTasks: HashSet[Task] = HashSet(end, F, A, X, Q,start)
    val result: Array[Task] = criticalPath(allTasks)
    print(result)

  }

  class Task(var name: String, var cost: Int, dependencies: Task*) {

    // the cost of the task along the critical path
    var criticalCost: Int = 0

    // the earliest start
    var earlyStart: Int = 0

    // the earliest finish
    var earlyFinish: Int = -1

    // the latest start
    var latestStart: Int = 0

    // the latest finish
    var latestFinish: Int = 0

    // the tasks on which this task is dependant
    var dependencies: HashSet[Task] = new HashSet[Task]
    this.name = name
    this.cost = cost
    for (t <- dependencies) {
      this.dependencies.add(t)
    }
    this.earlyFinish = -1
    def setLatest(): Unit = {
      latestStart = maxCost - criticalCost
      latestFinish = latestStart + cost
    }

    def toStringArray(): Array[String] = {
      val criticalCond: String = if (earlyStart == latestStart) "Yes" else "No"
      val toString: Array[String] = Array(name,
        earlyStart + "",
        earlyFinish + "",
        latestStart + "",
        latestFinish + "",
        latestStart - earlyStart + "",
        criticalCond)
      toString
    }

    def isDependent(t: Task): Boolean = {
      // is t a direct dependency?
      if (dependencies.contains(t)) {
        true
      }
      // is t an indirect dependency
      for (dep <- dependencies if dep.isDependent(t)) {
        true
      }
      false
    }

  }

  def criticalPath(tasks: Set[Task]): Array[Task] = {
    // tasks whose critical cost has been calculated
    val completed: HashSet[Task] = new HashSet[Task]()
    // tasks whose critical cost needs to be calculated
    val remaining: HashSet[Task] = new HashSet[Task]()
    // while there are tasks whose critical cost isn't calculated.
    while (!remaining.isEmpty) {
      var progress: Boolean = false
      // find a new task to calculate
      var it: util.Iterator[Task] = remaining.iterator()
      while (it.hasNext) {
        val task: Task = it.next()
        if (completed.containsAll(task.dependencies)) {
          // critical cost, plus our cost
          var critical: Int = 0
          for (t <- task.dependencies if t.criticalCost > critical) {
            critical = t.criticalCost
          }
          task.criticalCost = critical + task.cost
          // set task as calculated an remove
          completed.add(task)
          it.remove()
          // note we are making progress
          progress = true
        }
        // all dependencies calculated, critical cost is max
        // dependency
        // all dependencies calculated, critical cost is max
        // dependency
      }
      // the graph and we wont be able to calculate the critical path
      if (!progress)
        throw new RuntimeException("Cyclic dependency, algorithm stopped!")
    }
    // If we haven't made any progress then a cycle must exist in
    // If we haven't made any progress then a cycle must exist in
    // get the cost
    maxCost(tasks)
    val initialNodes: HashSet[Task] = initials(tasks)
    calculateEarly(initialNodes)
    // get the tasks
    val ret: Array[Task] = Array.ofDim[Task](0)
    // create a priority list
    util.Arrays.sort(ret, new Comparator[Task]() {
      override def compare(o1: Task, o2: Task): Int =
        o1.name.compareTo(o2.name)
    })
    ret
  }

  def calculateEarly(initials: HashSet[Task]): Unit = {
    for (initial <- initials) {
      initial.earlyStart = 0
      initial.earlyFinish = initial.cost
      setEarly(initial)
    }
  }

  def setEarly(initial: Task): Unit = {
    val completionTime: Int = initial.earlyFinish
    for (t <- initial.dependencies) {
      if (completionTime >= t.earlyStart) {
        t.earlyStart = completionTime
        t.earlyFinish = completionTime + t.cost
      }
      setEarly(t)
    }
  }

  def initials(tasks: Set[Task]): HashSet[Task] = {
    val remaining: HashSet[Task] = new HashSet[Task]()
    for (t <- tasks; td <- t.dependencies) {
      remaining.remove(td)
    }
    System.out.print("Initial nodes: ")
    for (t <- remaining) System.out.print(t.name + " ")
    System.out.print("\n\n")
    remaining
  }

  def maxCost(tasks: Set[Task]): Unit = {
    var max: Int = -1
    for (t <- tasks if t.criticalCost > max) max = t.criticalCost
    maxCost = max
    println("Critical path length (cost): " + maxCost)
    for (t <- tasks) {
      t.setLatest()
    }
  }

  def print(tasks: Array[Task]): Unit = {
    System.out.format(format,
      "Task",
      "ES",
      "EF",
      "LS",
      "LF",
      "Slack",
      "Critical?")
    for (t <- tasks)
      System.out.format(format, t.toStringArray().asInstanceOf[Array[Any]])
  }

}
