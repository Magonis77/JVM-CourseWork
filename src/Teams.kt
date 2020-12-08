import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import javax.swing.DefaultListModel


data class Teams(var name: String = "Team", var members: String) {

}

class CreateHandler() {
    //Create Team
    fun createTeam(name: String, members: String):Teams{
        val fileName = "Teams.txt"
        val myfile = File(fileName)
        val inputAsString = myfile.bufferedReader().use { it.readText() }
        myfile.printWriter().use { out ->
            out.write(inputAsString)
            out.write(name + " " + members + "\n")
        }

        return Teams(name = name, members = members)
    }

}












