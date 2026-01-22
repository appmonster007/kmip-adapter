package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.EncryptOpResponsePayload;

public class EncryptOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<EncryptOpResponsePayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public EncryptOpResponsePayloadBenchmarkSubject() throws Exception {
        EncryptOpResponsePayload subject = EncryptOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .data(DataByteString.of(new byte[]{1, 2, 3}))
                .build();
        initialize(subject, EncryptOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "EncryptOpResponsePayload";
    }
}
