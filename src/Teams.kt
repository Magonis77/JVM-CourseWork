import java.io.File
import java.io.FileWriter
import java.io.IOException
import javax.swing.DefaultListModel


data class Teams(var name: String = "Team", var members: String) {

}
val liss: DefaultListModel<*> = DefaultListModel<Any?>()
class CreateHandler() {
    fun createTeam(name: String, members: String):Teams{
        try {

            val writer = FileWriter("Teams.txt", true)
            writer.write(name + " " + members +  "\n")
            writer.close()
            val dir = File(".")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Teams(name = name, members = members)
    }

}












