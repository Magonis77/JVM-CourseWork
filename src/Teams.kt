import java.io.*


data class Teams(var name: String = "Team") {

}
class CreateHandler() {
    fun createTeam(name: String):Teams{
        try {
            val writer = FileWriter("tempTeams.txt", true)
            writer.write(name)
            writer.close()
            val dir = File(".")
            val pw = PrintWriter("Teams.txt")

            // BufferedReader object for file1.txt

            // BufferedReader object for file1.txt
            var br = BufferedReader(FileReader("tempTeams.txt"))

            var line = br.readLine()

            // loop to copy each line of
            // file1.txt to  file3.txt

            // loop to copy each line of
            // file1.txt to  file3.txt
            while (line != null) {
                pw.append(line)
                line = br.readLine()
            }

            br = BufferedReader(FileReader("selectedmembers.txt"))

            line = br.readLine()
            while (line != null) {
                pw.append(line + "\n")
                line = br.readLine()
            }

            pw.flush()
            br.close()
            pw.close()

            println("Merged file1.txt and file2.txt into file3.txt")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Teams(name = name)
    }

}


class MembersHandler(){
    fun addMember(name: String):Teams {
        try {
            val writer = FileWriter("selectedmembers.txt", true)
            writer.write(", " + name )
            writer.close()
            val dir = File(".")

        } catch (e: IOException) {
            e.printStackTrace()
        }


        return Teams(name = name)
    }

}







