package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageBenchmarkSubject extends KmipBenchmarkSubject<RevocationMessage> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public RevocationMessageBenchmarkSubject() throws Exception {
        RevocationMessage revocationMessage = RevocationMessage.builder().value("test-revocation-message").build();
        initialize(revocationMessage, RevocationMessage.class);
    }

    @Override
    public String name() {
        return "RevocationMessage";
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