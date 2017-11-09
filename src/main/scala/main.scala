import com.typesafe.config.ConfigFactory

object Main extends App {
	println(ConfigFactory.load().getObject("test-cases"))
}
