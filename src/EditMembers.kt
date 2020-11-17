import java.io.FileWriter
import java.io.IOException

data class CreateMembers(var name: String = "Team") {

}
class MembersCreator() {
    fun createMember(name: String): CreateMembers {

        try {
            val writer = FileWriter("members.txt", true)
            writer.write(name + "\n")
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        try {
            val writer = FileWriter("TempMembers.txt", true)
            writer.write(name + "\n")
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return CreateMembers(name = name)
    }
}