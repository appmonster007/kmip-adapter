package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;

public class KeyWrapTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyWrapType> {

    public KeyWrapTypeBenchmarkSubject() throws Exception {
        KeyWrapType keyWrapType = KeyWrapType.Standard.NOT_WRAPPED.inst();
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
