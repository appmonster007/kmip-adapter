package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

public class KeyCompressionTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyCompressionType> {

    public KeyCompressionTypeBenchmarkSubject() throws Exception {
        KeyCompressionType keyCompressionType = new KeyCompressionType(KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_UNCOMPRESSED);
        initialize(keyCompressionType, KeyCompressionType.class);
    }

    @Override
    public String name() {
        return "KeyCompressionType";
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
