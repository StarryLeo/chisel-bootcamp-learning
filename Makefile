2.1 :
	@sbt "first_module/test:runMain first_module.PassthroughTester" \
		"first_module/test:runMain first_module.PassthroughGeneratorTester"

.PHONY : clean
clean :
	@rm -r test_run_dir
