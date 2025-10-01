package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

public class EncodingOptionBenchmarkSubject extends KmipBenchmarkSubject<EncodingOption> {

    public EncodingOptionBenchmarkSubject() throws Exception {
        EncodingOption encodingOption = new EncodingOption(EncodingOption.Standard.NO_ENCODING);
        initialize(encodingOption, EncodingOption.class);
    }

    @Override
    public String name() {
        return "EncodingOption";
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
