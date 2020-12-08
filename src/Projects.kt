import java.io.*
import java.text.SimpleDateFormat
import java.util.*


fun read() {
    val inputStream: InputStream = File("CriticalPathDays.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }

}
//Append the txt File so its reset
fun rewrite(){
    val fileName = "Tasks.txt"
    val myfile = File(fileName)

    myfile.printWriter().use { out ->

        out.write("Task Name, Days to complete, progress %" + "\n")
    }
}

data class Projects(var name: String, var team: String, var tasks: String) {

}
data class Tasks(var tasks: String, var days: String) {

}
data class Progress(var tasks: String){

}

class ProjectsHandler() {
    //Add project
    fun addProject(name: String, team: String, tasks: String): Projects {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date(System.currentTimeMillis())
        val dateformate =(formatter.format(date))
        val fileName = "projects.txt"
        val myfile = File(fileName)
        val inputAsString = myfile.bufferedReader().use { it.readText() }
        myfile.printWriter().use { out ->
            out.write(inputAsString)
            out.write(name + ";" + team + ";" + dateformate + ";" + tasks + "\n")
        }

        return Projects(name = name, team = team, tasks = tasks)
    }
    //Add Tasks
    fun addTasks(tasks: String, days: String): Tasks {
        val fileName = "Tasks.txt"
        val myfile = File(fileName)
        val inputAsString = myfile.bufferedReader().use { it.readText() }
        myfile.printWriter().use { out ->
            out.write(inputAsString)
            out.write(tasks + " / " + days + " / " + "0%" + "\n")
        }
        return Tasks(tasks = tasks, days = days)
    }
    //Add progress
    fun addprogress(tasks: String): Progress {
        val fileName = "Tasks.txt"
        val myfile = File(fileName)
        val inputAsString = myfile.bufferedReader().use { it.readText() }
        myfile.printWriter().use { out ->
            out.write(inputAsString)
            out.write(tasks + "\n")
        }
        return Progress(tasks = tasks)
    }
    fun read(args: Array<String>) {
        val inputStream: InputStream = File("CriticalPathDays.txt").inputStream()
        val inputString = inputStream.bufferedReader().use { it.readText() }
    }

}


