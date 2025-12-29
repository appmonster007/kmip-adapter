package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.DigestValue;
import java.nio.ByteBuffer;

public class DigestValueBenchmarkSubject extends KmipBenchmarkSubject<DigestValue> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public DigestValueBenchmarkSubject() throws Exception {
        DigestValue digestValue = DigestValue.of(new byte[]{0x01, 0x02, 0x03});
        initialize(digestValue, DigestValue.class);
    }

    @Override
    public String name() {
        return "DigestValue";
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