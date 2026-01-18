package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.GetOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetOpRequestPayload Ttlv Serialization Tests")
class GetOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<GetOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<GetOpRequestPayload> type() {
        return GetOpRequestPayload.class;
    }

    @Override
    protected GetOpRequestPayload createDefault() {
        return GetOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
                .build();
    }

    @Override
    protected GetOpRequestPayload createVariant() {
        return GetOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("test-uid-variant"))
                .build();
    }
}