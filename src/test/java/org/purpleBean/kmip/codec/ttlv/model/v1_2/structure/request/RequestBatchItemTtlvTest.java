package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.CreateOpRequestPayload;
import org.purpleBean.kmip.model.v1_2.structure.request.GetOpRequestPayload;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestBatchItem Ttlv Serialization Tests")
class RequestBatchItemTtlvTest extends AbstractTtlvSerializationTestSuite<RequestBatchItem> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<RequestBatchItem> type() {
        return RequestBatchItem.class;
    }

    @Override
    protected RequestBatchItem createDefault() {
        return RequestBatchItem.builder()
                .operation(Operation.Standard.CREATE.inst())
                .requestPayloadStructure(CreateOpRequestPayload.builder()
                        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                        .templateAttribute(TemplateAttribute.builder().build())
                        .build())
                .build();
    }

    @Override
    protected RequestBatchItem createVariant() {
        return RequestBatchItem.builder()
                .operation(Operation.Standard.GET.inst())
                .requestPayloadStructure(GetOpRequestPayload.builder()
                        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
                        .build())
                .build();
    }
}