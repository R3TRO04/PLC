class StoreItem(val name :String,val value :Int) extends Item with Logger {
  val id :Int = getID
}
