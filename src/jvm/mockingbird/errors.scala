package mockingbird

sealed abstract class ApiError extends Exception
object ApiError {
  case class InvalidSensorType(name: String) extends ApiError {
    override def getMessage: String = s"Unknown sensor type: $name."
  }
}
