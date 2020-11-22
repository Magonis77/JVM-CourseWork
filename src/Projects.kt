import java.io.*


data class Projects(var name: String, var team: String, var tasks: String) {

}
data class Tasks(var tasks: String, var days: String) {

}
data class Progress(var tasks: String){

}

class ProjectsHandler() {
    fun addProject(name: String, team: String, tasks: String): Projects {

        try {
            val writer = FileWriter("projects.txt", true)
            writer.write(name + ";" + team + ";" + tasks + "\n")
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Projects(name = name, team = team, tasks = tasks)
    }

    fun addTasks(tasks: String, days: String): Tasks {
        try {
            val writer = FileWriter("Tasks.txt", true)
            writer.write(tasks + " / " + days + " / " + "0%" + "\n")
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Tasks(tasks = tasks, days = days)
    }

    fun addprogress(tasks: String): Progress {
        try {
            val writer = FileWriter("Tasks.txt", true)
            writer.write(tasks + "\n")
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Progress(tasks = tasks)
    }
}