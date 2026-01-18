package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Key;

public class KeyBenchmarkSubject extends KmipBenchmarkSubject<Key> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public KeyBenchmarkSubject() throws Exception {
        Key key = Key.of(new byte[]{0x01, 0x02, 0x03});
        initialize(key, Key.class);
    }

    @Override
    public String name() {
        return "Key";
    }

}