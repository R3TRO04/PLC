class StoreItem(override val id: Int,override val name: String,override val value: Int) extends Item with Logger{
    override def logAction(actionName: String, name: String): Unit = println(s"$name $actionName")
    def logName(): Unit = println(s"$name")
    def logItem(): Unit = println(s"$name $value")
}
