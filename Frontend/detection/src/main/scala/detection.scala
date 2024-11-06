package detection

import chisel3._
import chisel3.util._
import chisel3.stage._

class Detection extends Module {
    val io = IO(new Bundle{
        val in = Input(Bool())
        val out = Output(Bool())
    })

    val S0 = 0.U(2.W) // 0
    val S1 = 1.U(2.W) // 1
    val S2 = 2.U(2.W) // 11
    val S3 = 3.U(2.W) // 110

    val stat = RegInit(0.U(2.W))

    switch(stat) {
        is (S0) { when(io.in) {stat := S1} .otherwise {stat := S0} }
        is (S1) { when(io.in) {stat := S2} .otherwise {stat := S0} }
        is (S2) { when(io.in) {stat := S2} .otherwise {stat := S3} }
        is (S3) { when(io.in) {stat := S1} .otherwise {stat := S0} }
    }

    printf(p"in = ${io.in}, out = ${io.out}\n")
    io.out := stat === S3 && io.in
}

object testMain extends App {
    (new ChiselStage).execute(args, Seq(
        ChiselGeneratorAnnotation(() => new Detection))
    )
}
