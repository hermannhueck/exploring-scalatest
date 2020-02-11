package blog

object Platform {
  // 1.0.toString returns "1.0" on the JVM
  final val isJS  = 1.0.toString == "1"
  final val isJVM = !isJS
}
