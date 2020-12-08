import java.io.BufferedReader
import java.io.File

object CPHandling{
   public var TasksTogether:HashSet<Task> = CpHandle()


    fun CpHandle(): HashSet<Task> {
    val fileName1 = "CriticalPath.txt"
    val file1 = File(fileName1)

    file1.printWriter().use { out ->
    //rewrite txt file
        out.write("Task, Earliest Start , Earliest Finish , Latest Start, Latest Finish, Slack, Critical?")
    }
    val fileName2 = "InitialNodes.txt"
    val file2 = File(fileName2)

    file2.printWriter().use { out ->
        //rewrite txt file
        out.write("")
    }
    val fileName3 = "CriticalPathDays.txt"
    val file3 = File(fileName3)

    file3.printWriter().use { out ->
        //rewrite txt file
        out.write("")
    }
    //Read txt file
    val bufferedReader: BufferedReader = File("Tasks.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
    val fileName :String = "Tasks.txt"
    var i :Int = -1
    File(fileName).readLines().forEach {
        i++
    }
        //Make the CriticalPath Node/Nodes
    if(i == 1){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val start = Task(split[1], int1)
        TasksTogether = hashSetOf(start)


    }
    if(i == 2){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[4], int2)
        val start = Task(split[1], int1, end)

        TasksTogether = hashSetOf(end, start)

    }
    if(i == 3){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[7], int3)
        val A = Task(split[4], int2, end)
        val start = Task(split[1], int1, A)

        TasksTogether = hashSetOf(end, A, start)


    }
    if(i == 4){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[10], int4)
        val A = Task(split[7], int3, end)
        val F = Task(split[4], int2, A)
        val start = Task(split[1], int1, F)

     TasksTogether = hashSetOf(end, A, F, start)

    }
    if(i == 5){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[13], int5)
        val A = Task(split[10], int4, end)
        val F = Task(split[7], int3, A)
        val Q = Task(split[4], int2, F, A)
        val start = Task(split[1], int1, Q)

       TasksTogether = hashSetOf(end, A, F, Q, start)


    }
    if(i == 6){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[16], int6)
        val F = Task(split[13], int5, end)
        val A = Task(split[10], int4, end)
        val X = Task(split[7], int3, F, A)
        val Q = Task(split[4], int2, A, X)
        val start = Task(split[1], int1, Q)
        TasksTogether = hashSetOf(end, F, A, X, Q, start)

    }
    if(i == 7){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val int7 = split[20].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[19], int7)
        val Z = Task(split[16], int6, end)
        val F = Task(split[13], int5, end)
        val A = Task(split[10], int4, F, Z)
        val X = Task(split[7], int3, F, A, Z)
        val Q = Task(split[4], int2, A, X, F)
        val start = Task(split[1], int1, Q)
       TasksTogether = hashSetOf(end, Z, F, A, X, Q, start)

    }
    if(i == 8){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val int7 = split[20].replace("days", "").replace(" ", "").toInt()
        val int8 = split[23].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[22], int8)
        val G = Task(split[19], int7, end)
        val Z = Task(split[16], int6, end)
        val F = Task(split[13], int5, Z, G)
        val A = Task(split[10], int4, F, Z)
        val X = Task(split[7], int3, F, A, Z, G)
        val Q = Task(split[4], int2, A, X, F, Z)
        val start = Task(split[1], int1, Q)
        TasksTogether = hashSetOf(end, G, Z, F, A, X, Q, start)

    }
    if(i == 9){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val int7 = split[20].replace("days", "").replace(" ", "").toInt()
        val int8 = split[23].replace("days", "").replace(" ", "").toInt()
        val int9 = split[26].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[25], int9)
        val H = Task(split[22], int8, end)
        val G = Task(split[19], int7, end)
        val Z = Task(split[16], int6, G, H)
        val F = Task(split[13], int5, Z, G, H)
        val A = Task(split[10], int4, F, Z, G)
        val X = Task(split[7], int3, F, A, Z)
        val Q = Task(split[4], int2, A, X, F)
        val start = Task(split[1], int1, Q)
       TasksTogether = hashSetOf(end, H, G, Z, F, A, X, Q, start)

    }
    if(i == 10){
        val befsplit = inputString.replace("%", "/")
        val split: List<String> = befsplit.split("/")
        val int1 = split[2].replace("days", "").replace(" ", "").toInt()
        val int2 = split[5].replace("days", "").replace(" ", "").toInt()
        val int3 = split[8].replace("days", "").replace(" ", "").toInt()
        val int4 = split[11].replace("days", "").replace(" ", "").toInt()
        val int5 = split[14].replace("days", "").replace(" ", "").toInt()
        val int6 = split[17].replace("days", "").replace(" ", "").toInt()
        val int7 = split[20].replace("days", "").replace(" ", "").toInt()
        val int8 = split[23].replace("days", "").replace(" ", "").toInt()
        val int9 = split[26].replace("days", "").replace(" ", "").toInt()
        val int10 = split[29].replace("days", "").replace(" ", "").toInt()
        val end = Task(split[28], int10)
        val D = Task(split[25], int9, end)
        val H = Task(split[22], int8, end)
        val G = Task(split[19], int7, D, H)
        val Z = Task(split[16], int6, G, H, D)
        val F = Task(split[13], int5, Z, G, H)
        val A = Task(split[10], int4, F, Z, G)
        val X = Task(split[7], int3, F, A, Z)
        val Q = Task(split[4], int2, A, X, F)
        val start = Task(split[1], int1, Q)
       TasksTogether = hashSetOf(end, D, H, G, Z, F, A, X, Q, start)


    }

    return TasksTogether
}}