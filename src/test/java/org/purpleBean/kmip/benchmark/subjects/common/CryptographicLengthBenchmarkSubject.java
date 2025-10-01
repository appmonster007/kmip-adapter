package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthBenchmarkSubject extends KmipBenchmarkSubject<CryptographicLength> {

    public CryptographicLengthBenchmarkSubject() throws Exception {
        CryptographicLength cryptographicLength = CryptographicLength.of(256);
        initialize(cryptographicLength, CryptographicLength.class);
    }

    @Override
    public String name() {
        return "CryptographicLength";
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
