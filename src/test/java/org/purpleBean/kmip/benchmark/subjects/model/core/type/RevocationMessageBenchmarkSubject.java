package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.RevocationMessage;

public class RevocationMessageBenchmarkSubject extends KmipBenchmarkSubject<RevocationMessage> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public RevocationMessageBenchmarkSubject() throws Exception {
        RevocationMessage revocationMessage = RevocationMessage.builder().value("test-revocation-message").build();
        initialize(revocationMessage, RevocationMessage.class);
    }

    @Override
    public String name() {
        return "RevocationMessage";
    }

}