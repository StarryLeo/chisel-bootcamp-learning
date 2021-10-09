2.1 :
	@sbt "first_module/test:runMain first_module.PassthroughTester" \
	"first_module/test:runMain first_module.PassthroughGeneratorTester"

2.2 :
	@sbt "comb_logic/test:runMain comb_logic.MyOperatorsTester" \
	"comb_logic/test:runMain comb_logic.MyOperatorsTwoTester" \
	"comb_logic/test:runMain comb_logic.MACTester" \
	"comb_logic/test:runMain comb_logic.ArbiterTester"

.PHONY : clean 2.1 2.2
clean :
	@rm -r test_run_dir
