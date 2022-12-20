class StoreItem(val id: Int, val name: String, val value: Int) extends Item with Logger{
    override def logAction(actionName: String, name: String): Unit = println(s"$name $actionName.")
}
