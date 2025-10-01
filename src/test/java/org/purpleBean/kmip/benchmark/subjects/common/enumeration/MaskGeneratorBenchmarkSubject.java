package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorBenchmarkSubject extends KmipBenchmarkSubject<MaskGenerator> {

    public MaskGeneratorBenchmarkSubject() throws Exception {
        MaskGenerator maskGenerator = new MaskGenerator(MaskGenerator.Standard.MFG1);
        initialize(maskGenerator, MaskGenerator.class);
    }

    @Override
    public String name() {
        return "MaskGenerator";
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
