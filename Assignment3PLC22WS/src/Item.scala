trait Item {
  var id: Int
  var name: String
  var value: Int

  def getID: Int = {
    id += 1
    id
  }
}
