package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyFormatType> {

    public KeyFormatTypeBenchmarkSubject() throws Exception {
        KeyFormatType keyFormatType = KeyFormatType.Standard.RAW.inst();
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
