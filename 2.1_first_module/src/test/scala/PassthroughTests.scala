package first_module

import chisel3._
import chisel3.stage.ChiselStage
import chisel3.iotesters.{PeekPokeTester, Driver}

class PassthroughTests(dut: Passthrough) extends PeekPokeTester(dut) {
  poke(dut.io.in, 0.U)
  expect(dut.io.out, 0.U)
  poke(dut.io.in, 1.U)
  expect(dut.io.out, 1.U)
  poke(dut.io.in, 2.U)
  expect(dut.io.out, 2.U)
  println("SUCCESS!!")
}

object PassthroughTester extends App {
  val execute_args = Array("--target-dir", "test_run_dir")

  iotesters.Driver.execute(
    execute_args, () => new Passthrough) {
    c => new PassthroughTests(c)
  }
  val verilogString = (new ChiselStage).emitVerilog(new Passthrough, execute_args)
  println(verilogString)
}
