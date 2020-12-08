import java.util.*

data class ManageTeam(var name: String = "Team", var members: Array<String>) {

}
data class members(var members: Array<String>){

}
data class teamname(var teamname: String){

}

class ManageHandler() {
    //send from Team string to GUI
    fun sendlist(listing: String= "Team"):members{
        var list: String = listing
        var split = list.replace("[", ",").replace("]", "").replace(" ", "")
        var everything = split.split(",".toRegex()).toTypedArray()
        var test = everything[0]
        var members = split.replace(test, "").split(",".toRegex()).toTypedArray()

        return members(members)
    }
    //GetTeamName from JTextField
    fun getteamname(teamname: String = "Team A"):teamname{
            var list: String = teamname
            var split = list.replace("[", ",").replace("]", "").replace(" ", "")
            var everything = split.split(",".toRegex()).toTypedArray()
            var test = everything[0]

            return teamname(test)
        }
    }






