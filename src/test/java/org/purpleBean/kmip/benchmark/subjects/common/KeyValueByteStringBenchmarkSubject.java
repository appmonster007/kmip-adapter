package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.KeyValue;
import java.nio.ByteBuffer;

public class KeyValueByteStringBenchmarkSubject extends KmipBenchmarkSubject<KeyValue.ByteString> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public KeyValueByteStringBenchmarkSubject() throws Exception {
        KeyValue.ByteString keyValueByteString = KeyValue.ByteString.of(new byte[]{0x01, 0x02, 0x03});
        initialize(keyValueByteString, KeyValue.ByteString.class);
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