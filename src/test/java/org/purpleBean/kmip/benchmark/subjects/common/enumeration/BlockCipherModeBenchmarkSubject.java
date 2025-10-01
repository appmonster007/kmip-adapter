package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeBenchmarkSubject extends KmipBenchmarkSubject<BlockCipherMode> {

    public BlockCipherModeBenchmarkSubject() throws Exception {
        BlockCipherMode blockCipherMode = new BlockCipherMode(BlockCipherMode.Standard.CBC);
        initialize(blockCipherMode, BlockCipherMode.class);
    }

    @Override
    public String name() {
        return "BlockCipherMode";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
