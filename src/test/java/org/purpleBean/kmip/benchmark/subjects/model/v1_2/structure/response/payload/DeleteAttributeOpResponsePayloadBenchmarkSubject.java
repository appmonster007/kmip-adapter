package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DeleteAttributeOpResponsePayload;

public class DeleteAttributeOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<DeleteAttributeOpResponsePayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public DeleteAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
        DeleteAttributeOpResponsePayload subject = DeleteAttributeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .attribute(Attribute.of(AttributeName.of("test-attribute"), AttributeValueTextString.of("test-value")))
                .build();
        initialize(subject, DeleteAttributeOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "DeleteAttributeOpResponsePayload";
    }
}
