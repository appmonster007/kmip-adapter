package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.IVCounterNonce;
import java.nio.ByteBuffer;

public class IVCounterNonceBenchmarkSubject extends KmipBenchmarkSubject<IVCounterNonce> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public IVCounterNonceBenchmarkSubject() throws Exception {
        IVCounterNonce iVCounterNonce = IVCounterNonce.of(new byte[]{0x01, 0x02, 0x03});
        initialize(iVCounterNonce, IVCounterNonce.class);
    }

    @Override
    public String name() {
        return "IVCounterNonce";
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