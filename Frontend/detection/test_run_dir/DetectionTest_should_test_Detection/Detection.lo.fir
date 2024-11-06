circuit Detection :
  module Detection :
    input clock : Clock
    input reset : UInt<1>
    input io_in : UInt<1>
    output io_out : UInt<1>

    reg stat : UInt<2>, clock with :
      reset => (UInt<1>("h0"), stat) @[detection.scala 18:23]
    node _T = eq(UInt<2>("h0"), stat) @[detection.scala 20:18]
    node _GEN_0 = mux(io_in, UInt<2>("h1"), UInt<2>("h0")) @[detection.scala 21:{31,37,61}]
    node _T_1 = eq(UInt<2>("h1"), stat) @[detection.scala 20:18]
    node _GEN_1 = mux(io_in, UInt<2>("h2"), UInt<2>("h0")) @[detection.scala 22:{31,37,61}]
    node _T_2 = eq(UInt<2>("h2"), stat) @[detection.scala 20:18]
    node _GEN_2 = mux(io_in, UInt<2>("h2"), UInt<2>("h3")) @[detection.scala 23:{31,37,61}]
    node _T_3 = eq(UInt<2>("h3"), stat) @[detection.scala 20:18]
    node _GEN_3 = mux(_T_3, _GEN_0, stat) @[detection.scala 20:18 18:23]
    node _GEN_4 = mux(_T_2, _GEN_2, _GEN_3) @[detection.scala 20:18]
    node _GEN_5 = mux(_T_1, _GEN_1, _GEN_4) @[detection.scala 20:18]
    node _GEN_6 = mux(_T, _GEN_0, _GEN_5) @[detection.scala 20:18]
    node _T_4 = bits(reset, 0, 0) @[detection.scala 27:11]
    node _T_5 = eq(_T_4, UInt<1>("h0")) @[detection.scala 27:11]
    node _io_out_T = eq(stat, UInt<2>("h3")) @[detection.scala 28:20]
    node _io_out_T_1 = and(_io_out_T, io_in) @[detection.scala 28:27]
    io_out <= _io_out_T_1 @[detection.scala 28:12]
    stat <= mux(reset, UInt<2>("h0"), _GEN_6) @[detection.scala 18:{23,23}]
    printf(clock, and(and(UInt<1>("h1"), _T_5), UInt<1>("h1")), "in = %d, out = %d\n", io_in, io_out) : printf @[detection.scala 27:11]
