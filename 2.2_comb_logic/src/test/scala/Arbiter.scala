package comb_logic

import chisel3._
import chisel3.stage.ChiselStage
import chisel3.iotesters.{PeekPokeTester, Driver}

class ArbiterTests(dut: Arbiter) extends PeekPokeTester(dut) {
  import scala.util.Random
  val data = Random.nextInt(65536)
  poke(dut.io.fifo_data, data.U)

  for (i <- 0 until 8) {
    poke(dut.io.fifo_valid, (((i >> 0) % 2) != 0).B)
    poke(dut.io.pe0_ready, (((i >> 1) % 2) != 0).B)
    poke(dut.io.pe1_ready, (((i >> 2) % 2) != 0).B)

    expect(dut.io.fifo_ready, (i > 0).B)
    expect(dut.io.pe0_valid, (i == 3 || i == 7).B)
    expect(dut.io.pe1_valid, (i == 5).B)

    if (i == 3 || i == 7)
      expect(dut.io.pe0_data, data.U)
    else if (i == 5)
      expect(dut.io.pe1_data, data.U)
  }
  println("SUCCESS!!")
}

object ArbiterTester extends App {
  val execute_args = Array("--target-dir", "test_run_dir")

  iotesters.Driver.execute(
    execute_args, () => new Arbiter) {
    c => new ArbiterTests(c)
  }
  val verilogString = (new ChiselStage).emitVerilog(new Arbiter, execute_args)
  println(verilogString)
}
