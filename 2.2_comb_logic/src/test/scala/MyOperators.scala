package comb_logic

import chisel3._
import chisel3.stage.ChiselStage
import chisel3.iotesters.{PeekPokeTester, Driver}

class MyOperatorsTests(dut: MyOperators) extends PeekPokeTester(dut) {
  expect(dut.io.out_add, 5.U)
  expect(dut.io.out_sub, 1.U)
  expect(dut.io.out_mul, 8.U)
  println("SUCCESS!!")
}

object MyOperatorsTester extends App {
  val execute_args = Array("--target-dir", "test_run_dir")

  iotesters.Driver.execute(
    execute_args, () => new MyOperators) {
    c => new MyOperatorsTests(c)
  }
  val verilogString = (new ChiselStage).emitVerilog(new MyOperators, execute_args)
  println(verilogString)
}
