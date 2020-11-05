import java.io.*


data class Projects(var name: String = "Project") {

}
class CreateHandler2() {
    fun createProject(name: String):Projects{
        try {
            val writer = FileWriter("tempProjects.txt", true)
            writer.write(name + " /")
            writer.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Projects(name = name)
    }

}


class MembersHandler2(){
    fun addMember(name: String):Projects {

        try {
            val writer = FileWriter("projects.txt", true)
            writer.write(name + "\n")

            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Projects(name = name)
    }

}