package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyFormatType> {

    public KeyFormatTypeBenchmarkSubject() throws Exception {
        KeyFormatType keyFormatType = new KeyFormatType(KeyFormatType.Standard.RAW);
        initialize(keyFormatType, KeyFormatType.class);
    }

    @Override
    public String name() {
        return "KeyFormatType";
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
