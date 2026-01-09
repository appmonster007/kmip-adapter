package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.common.structure.TransparentSymmetricKey;

public class TransparentSymmetricKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentSymmetricKey> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public TransparentSymmetricKeyBenchmarkSubject() throws Exception {
        Key key = Key.of(new byte[]{0x01, 0x02, 0x03});
        TransparentSymmetricKey transparentSymmetricKey = TransparentSymmetricKey.of(key);
        initialize(transparentSymmetricKey, TransparentSymmetricKey.class);
    }

    @Override
    public String name() {
        return "TransparentSymmetricKey";
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