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
    storedItems.sortWith((storeItemA, storeItemB) => (storeItemA.value < storeItemB.value))
  }

  override def sortByValueDesc(): Array[StoreItem] = {
    storedItems.sortWith((storeItemA, storeItemB) => (storeItemA.value > storeItemB.value))
  }

  override def store(item: StoreItem): Array[StoreItem] = {
    item.logAction("stored", item.name)
    storedItems:+ item
  }

  override def sumUp(): Int = {
    storedItems.foldLeft(0)(_+_.value)
}

  def filterByName(name: String, itemArray: Array[StoreItem]):Array[StoreItem] ={
    itemArray.filter(_.name == name).sortBy(_.value)
  }
}
