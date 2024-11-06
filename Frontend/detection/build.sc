import scalalib._

object detection extends CrossSbtModule {
    def crossScalaVersion = "2.12.13"

    override def scalacPluginIvyDeps = Agg(ivy"edu.berkeley.cs:::chisel3-plugin:3.5.0")

    /**
     * Scala 2.12 module that is source-compatible with 2.11.
     * This is due to Chisel's use of structural types. See
     * https://github.com/freechipsproject/chisel3/issues/606
     */
    override def scalacOptions = T {
        super.scalacOptions() ++ Seq(
            "-Xsource:2.11"
        )
    }
  
    def ivyDeps = Agg (
        ivy"edu.berkeley.cs::chisel3:3.5.0",
        ivy"edu.berkeley.cs:::chisel3-plugin:3.5.0"
    )

    object test extends Tests with TestModule.ScalaTest {
        override def ivyDeps = super.ivyDeps() ++ Agg(
            ivy"org.scalatest::scalatest:3.2.2",
            ivy"edu.berkeley.cs::chiseltest:0.5.0"
        )
    }
}

