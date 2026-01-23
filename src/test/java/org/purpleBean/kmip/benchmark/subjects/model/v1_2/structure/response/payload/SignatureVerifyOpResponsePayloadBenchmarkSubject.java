package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.SignatureVerifyOpResponsePayload;

public class SignatureVerifyOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<SignatureVerifyOpResponsePayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public SignatureVerifyOpResponsePayloadBenchmarkSubject() throws Exception {
        SignatureVerifyOpResponsePayload subject = SignatureVerifyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
                .build();
        initialize(subject, SignatureVerifyOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "SignatureVerifyOpResponsePayload";
    }
}
