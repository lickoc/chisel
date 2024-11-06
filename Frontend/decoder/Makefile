BUILD_DIR = ./build
TOP_V = $(BUILD_DIR)/Decoder.v
SCALA_FILE = $(shell find ./src -name '*.scala')

.DEFAULT_GOAL = verilog

$(TOP_V): $(SCALA_FILE)
	@mkdir -p $(@D)
	mill decoder.run decoder.main.testMain -td $(@D) --output-file $(@F)

verilog: $(TOP_V)

$(BUILD_DIR)/VDecoder.mk: $(TOP_V)
	@mkdir -p $(@D)
	verilator --cc --exe \
		-o $(abspath $(BUILD_DIR)/emu) -Mdir $(@D) $^ ./sim_main.cpp

$(BUILD_DIR)/emu: $(BUILD_DIR)/VDecoder.mk
	$(MAKE) -C $(BUILD_DIR) -f ./VDecoder.mk

emu: $(BUILD_DIR)/emu

clean:
	rm -rf build
