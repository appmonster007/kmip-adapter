package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;

public class BlockCipherModeBenchmarkSubject extends KmipBenchmarkSubject<BlockCipherMode> {

    public BlockCipherModeBenchmarkSubject() throws Exception {
        BlockCipherMode blockCipherMode = BlockCipherMode.Standard.CBC.inst();
        initialize(blockCipherMode, BlockCipherMode.class);
    }

    @Override
    public String name() {
        return "BlockCipherMode";
    }

}
