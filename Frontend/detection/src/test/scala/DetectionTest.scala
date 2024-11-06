package detection

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class DetectionTest extends AnyFlatSpec with ChiselScalatestTester {
    behavior of "DetectionTest"

    it should "test Detection" in {
        test(new Detection) { c =>
            var seq_in  = Array(1,1,1,0,1,1,0,1,0,1,1,0,1,0)

            // 检测1101序列的参考输出
            var seq_out = Array(0,0,0,0,1,0,0,1,0,0,0,0,1,0)

            // 检测1011序列的参考输出
            // var seq_out = Array(0,0,0,0,0,1,0,0,0,0,1,0,0,0)

            for (i <- 0 until seq_in.length) {
                c.io.in.poke(seq_in(i).B)
                c.io.out.expect(seq_out(i).B)
                c.clock.step() // 执行一个时钟周期的仿真
            }
        }
    }
}
