package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataBenchmarkSubject extends KmipBenchmarkSubject<SignatureData> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public SignatureDataBenchmarkSubject() throws Exception {
        byte[] data = "test signature data".getBytes();
        SignatureData signatureData = SignatureData.of(ByteBuffer.wrap(data));
        initialize(signatureData, SignatureData.class);
    }

    @Override
    public String name() {
        return "SignatureData";
    }

    @Override
    public void setup() {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
