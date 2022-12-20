object MainApp extends Database {
  var database: Database = new Database
  def main(args: Array[String]): Unit = {
    saveItemsFromCSV("data.csv")

    println("--- SUM UP ---")
    println(database.sumUp())

    println("--- FILTERED BY ASUS ---")
    database.sortByValueAsc(database.filterByName("ASUS")).foreach(_.logName())
    println(database.sortByValueAsc(database.filterByName("ASUS")).length)

    println("--- FILTERED BY Lenovo ---")
    database.sortByValueAsc(database.filterByName("Lenovo")).foreach(_.logName())
    println(database.sortByValueAsc(database.filterByName("Lenovo")).length)

    println("--- FILTERED BY HP ---")
    database.sortByValueAsc(database.filterByName("HP")).foreach(_.logName())
    println(database.sortByValueAsc(database.filterByName("HP")).length)

    println("--- SORTED ITEMS ---")
    database.sortByValueDesc().foreach(_.logItem())
  }

  def saveItemsFromCSV(filename: String): Unit = {
    var items = List[StoreItem]()
    val bufferedSource = io.Source.fromFile(filename)
    for(line <- bufferedSource.getLines.drop(1)) {
      val cols = line.split(",").map(_.trim)
      val item = new StoreItem(cols(0).toInt, cols(1), cols(2).toInt)

      items = items :+ item
    }
    bufferedSource.close
    saveListToDatabase(items)
  }

  def saveListToDatabase(list: List[StoreItem]) : Unit = {
    list.foreach(database.store)
  }
}
