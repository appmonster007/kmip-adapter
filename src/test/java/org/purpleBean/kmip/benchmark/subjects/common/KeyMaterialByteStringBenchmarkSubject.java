package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.KeyMaterial;

public class KeyMaterialByteStringBenchmarkSubject extends KmipBenchmarkSubject<KeyMaterial.ByteString> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public KeyMaterialByteStringBenchmarkSubject() throws Exception {
        KeyMaterial.ByteString keyMaterialByteString = KeyMaterial.ByteString.of(new byte[]{0x01, 0x02, 0x03});
        initialize(keyMaterialByteString, KeyMaterial.ByteString.class);
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