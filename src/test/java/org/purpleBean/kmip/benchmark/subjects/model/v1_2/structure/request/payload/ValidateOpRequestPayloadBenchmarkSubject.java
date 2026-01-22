package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.ValidityDate;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ValidateOpRequestPayload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ValidateOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<ValidateOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ValidateOpRequestPayloadBenchmarkSubject() throws Exception {
        ValidateOpRequestPayload subject = ValidateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .validityDate(ValidityDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
                .build();
        initialize(subject, ValidateOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "ValidateOpRequestPayload";
    }
}
