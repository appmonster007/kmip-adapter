package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeBenchmarkSubject extends KmipBenchmarkSubject<SecretDataType> {

    public SecretDataTypeBenchmarkSubject() throws Exception {
        SecretDataType secretDataType = new SecretDataType(SecretDataType.Standard.PASSWORD);
        initialize(secretDataType, SecretDataType.class);
    }

    @Override
    public String name() {
        return "SecretDataType";
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
