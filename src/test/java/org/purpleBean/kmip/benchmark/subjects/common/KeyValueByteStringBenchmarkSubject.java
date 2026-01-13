package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.KeyValueByteString;

public class KeyValueByteStringBenchmarkSubject extends KmipBenchmarkSubject<KeyValueByteString> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public KeyValueByteStringBenchmarkSubject() throws Exception {
        KeyValueByteString keyValueByteString = KeyValueByteString.of(new byte[]{0x01, 0x02, 0x03});
        initialize(keyValueByteString, KeyValueByteString.class);
    }

    @Override
    public String name() {
        return "KeyValueByteString";
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