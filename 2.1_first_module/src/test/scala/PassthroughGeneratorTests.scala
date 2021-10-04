package first_module

import chisel3._
import chisel3.stage.ChiselStage
import chisel3.iotesters.{PeekPokeTester, Driver}

class PassthroughGeneratorTests(dut: PassthroughGenerator) extends PeekPokeTester(dut) {
  poke(dut.io.in, 0.U)
  expect(dut.io.out, 0.U)
  poke(dut.io.in, 100.U)
  expect(dut.io.out, 100.U)
  poke(dut.io.in, 2.U)
  expect(dut.io.out, 2.U)
  println("SUCCESS!!")
}

object PassthroughGeneratorTester extends App {
  val execute_args = Array("--target-dir", "test_run_dir")

  iotesters.Driver.execute(
    execute_args, () => new PassthroughGenerator(10)) {
    c => new PassthroughGeneratorTests(c)
  }
  val verilogString = (new ChiselStage).emitVerilog(new PassthroughGenerator(10), execute_args)
  println(verilogString)
}
