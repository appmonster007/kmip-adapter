package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

public class KeyMaterialByteStringBenchmarkSubject extends KmipBenchmarkSubject<KeyMaterialByteString> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public KeyMaterialByteStringBenchmarkSubject() throws Exception {
        KeyMaterialByteString keyMaterialByteString = KeyMaterialByteString.of(new byte[]{0x01, 0x02, 0x03});
        initialize(keyMaterialByteString, KeyMaterialByteString.class);
    }

    @Override
    public String name() {
        return "KeyMaterialByteString";
    }

}