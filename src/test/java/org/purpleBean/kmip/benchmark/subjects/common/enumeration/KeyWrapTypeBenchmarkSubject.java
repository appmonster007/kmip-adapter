package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyWrapType> {

    public KeyWrapTypeBenchmarkSubject() throws Exception {
        KeyWrapType keyWrapType = new KeyWrapType(KeyWrapType.Standard.NOT_WRAPPED);
        initialize(keyWrapType, KeyWrapType.class);
    }

    @Override
    public String name() {
        return "KeyWrapType";
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
