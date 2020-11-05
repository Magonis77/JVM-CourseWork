import java.io.*


data class Teams(var name: String = "Team") {

}
class CreateHandler() {
    fun createTeam(name: String):Teams{
        try {
            val writer = FileWriter("tempTeams.txt", true)
            writer.write(name + " /")
            writer.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Teams(name = name)
    }

}


class MembersHandler(){
    fun addMember(name: String):Teams {

        try {
            val writer = FileWriter("members.txt", true)
            writer.write(name + "\n")

            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Teams(name = name)
    }

}







