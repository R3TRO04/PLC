class Database extends ShoppingCart {
  private var storedItems: Array[StoreItem] = Array.empty

  override def delete(id: Int): Array[StoreItem] = {
    storedItems.drop(id)
  }

  override def search(name: String): Array[StoreItem] = {
    val res = storedItems.filter(_.name == name)
    if (!res.isEmpty)
      res.foreach(_.logAction("found", name))
    else
      storedItems(0).logAction("not found", name)
    res
  }

  override def sortByValueAsc(): Array[StoreItem] = {
    storedItems.sortWith((storeItemA, storeItemB) => storeItemA.value < storeItemB.value)
  }

  override def sortByValueDesc(): Array[StoreItem] = {
    storedItems.sortWith((storeItemA, storeItemB) => storeItemA.value > storeItemB.value)
  }

  def sortByValueAsc(array: Array[StoreItem]): Array[StoreItem] = {
    array.sortWith((storeItemA, storeItemB) => storeItemA.value < storeItemB.value)
  }

  def sortByValueDesc(array: Array[StoreItem]): Array[StoreItem] = {
    array.sortWith((storeItemA, storeItemB) => storeItemA.value > storeItemB.value)
  }

  override def store(item: StoreItem): Array[StoreItem] = {
    item.logAction("stored", item.name)
    storedItems = storedItems :+ item
    storedItems
  }

  override def sumUp(): Int = {
    storedItems.foldLeft(0)(_ + _.value)
  }

  def filterByName(name: String): Array[StoreItem] = {
    storedItems.filter(_.name.contains(name)).sortBy(_.value)
  }
}