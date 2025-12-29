package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.KeyMaterialByteString;
import java.nio.ByteBuffer;

public class KeyMaterialByteStringBenchmarkSubject extends KmipBenchmarkSubject<KeyMaterialByteString> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public KeyMaterialByteStringBenchmarkSubject() throws Exception {
        KeyMaterialByteString keyMaterialByteString = KeyMaterialByteString.of(new byte[]{0x01, 0x02, 0x03});
        initialize(keyMaterialByteString, KeyMaterialByteString.class);
    }

    @Override
    public String name() {
        return "KeyMaterialByteString";
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