package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Key;
import java.nio.ByteBuffer;

public class KeyBenchmarkSubject extends KmipBenchmarkSubject<Key> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public KeyBenchmarkSubject() throws Exception {
        Key key = Key.of(new byte[]{0x01, 0x02, 0x03});
        initialize(key, Key.class);
    }

    @Override
    public String name() {
        return "Key";
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