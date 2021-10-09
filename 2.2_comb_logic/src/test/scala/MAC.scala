package comb_logic

import chisel3._
import chisel3.stage.ChiselStage
import chisel3.iotesters.{PeekPokeTester, Driver}

class MACTests(dut: MAC) extends PeekPokeTester(dut) {
  val cycles = 100
  import scala.util.Random
  for (i <- 0 until cycles) {
    val in_a = Random.nextInt(16)
    val in_b = Random.nextInt(16)
    val in_c = Random.nextInt(16)
    poke(dut.io.in_a, in_a.U)
    poke(dut.io.in_b, in_b.U)
    poke(dut.io.in_c, in_c.U)
    expect(dut.io.out, (in_a * in_b + in_c).U)
  }
  println("SUCCESS!!")
}

object MACTester extends App {
  val execute_args = Array("--target-dir", "test_run_dir")

  iotesters.Driver.execute(
    execute_args, () => new MAC) {
    c => new MACTests(c)
  }
  val verilogString = (new ChiselStage).emitVerilog(new MAC, execute_args)
  println(verilogString)
}
