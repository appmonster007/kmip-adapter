package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DeleteAttributeOpRequestPayload;

public class DeleteAttributeOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<DeleteAttributeOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public DeleteAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
        DeleteAttributeOpRequestPayload subject = DeleteAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .attributeName(AttributeName.of("test-attribute"))
                .attributeIndex(AttributeIndex.of(1))
                .build();
        initialize(subject, DeleteAttributeOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "DeleteAttributeOpRequestPayload";
    }
}
