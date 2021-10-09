package comb_logic

import chisel3._
import chisel3.util._
import chisel3.stage.ChiselStage
import chisel3.iotesters.{PeekPokeTester, Driver}

class MyOperatorsTwoTests(dut: MyOperatorsTwo) extends PeekPokeTester(dut) {
  expect(dut.io.out_mux, 3.U)
  expect(dut.io.out_cat, 5.U)
  println("SUCCESS!!")
}

object MyOperatorsTwoTester extends App {
  val execute_args = Array("--target-dir", "test_run_dir")

  iotesters.Driver.execute(
    execute_args, () => new MyOperatorsTwo) {
    c => new MyOperatorsTwoTests(c)
  }
  val verilogString = (new ChiselStage).emitVerilog(new MyOperatorsTwo, execute_args)
  println(verilogString)
}
