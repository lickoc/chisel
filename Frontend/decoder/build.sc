import scalalib._

object decoder extends CrossSbtModule {
    def crossScalaVersion = "2.12.10"

    def ivyDeps = Agg (
        ivy"edu.berkeley.cs::chisel3:3.3.2"
    )
}

