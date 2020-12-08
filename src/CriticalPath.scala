
import com.sun.deploy.trace.Trace.println

import scala.collection.convert.ImplicitConversions.`collection asJava`
import scala.collection.immutable.HashSet
import scala.jdk.CollectionConverters._



class CriticalPath{


  def calcCritPath(tasks: HashSet[Task]) = {
    var completed = HashSet[Task]()
    val remaining = tasks.map(x => x)

    while (remaining.nonEmpty) {
      var progress = false

      val it = remaining.iterator()
      while (it.hasNext()) {
        val task = it.next()
        if (completed.containsAll(task.dependencies)) {
          val criticalList = task.dependencies.map { it => it.criticalCost }
          val critical = if (criticalList.isEmpty) 0 else criticalList.max
          task.criticalCost = critical + task.cost
          completed.add(task)
          it.remove()
          progress = true
        }
      }
      if (!progress) throw new Exception("Cyclic dependency, algorithm stopped")
    }

    val maxCostCheck = tasks.map(it => it.criticalCost)
    val maxCost = if (maxCostCheck.isEmpty) 0 else maxCostCheck.max
    println(s"Critical Path lenght is (Cost): ${maxCost}")

    calculateLatest(tasks, maxCost)
    calculateEarly(tasks)
    completed
  }

  def calculateLatest(tasks: HashSet[Task], maxCost: Int): Unit = tasks.foreach(it => it.setLatest(maxCost))

  def calculateEarly(tasks: HashSet[Task]) = tasks.foreach(it => {
    it.earlyStart = 0
    it.earlyFinish = it.cost
    it.setEarlyForDependecies()
  })

  def initials(tasks: HashSet[Task]) = {
    val dependencies = tasks.map(it => it.dependencies)
    val results = tasks.filter(x => dependencies.contains(x))
    results
  }

  case class Task(
                        var name: String,
                        var cost: Int,
                        var dependencies: HashSet[Task]
                      ) {
    var criticalCost = 0
    var earlyStart = 0
    var earlyFinish = -1
    var latestStart = 0
    var latestFinish = 0

    var dependency = dependencies.map(x => x)

    def setLatest(maxCost: Int): Unit = {
      latestStart = maxCost - criticalCost
      latestFinish = latestStart + cost
    }

    def setEarlyForDependecies(): Unit = {
      val completionTime = earlyFinish
      dependency.foreach(x => {
        if (completionTime >= x.earlyStart) {
          x.earlyStart = completionTime
          x.earlyFinish = completionTime + x.cost
        }
        x.setEarlyForDependecies()
      })
    }


    def isCritical() = earlyStart == latestStart

    def isDependent(t: Task): Boolean = dependency.contains(t) || dependency.exists { it => it.isDependent(t) }


}}