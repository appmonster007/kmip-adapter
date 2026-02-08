package org.purpleBean.kmip.codec.ttlv.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SimpleRequestPayload Ttlv Serialization Tests")
class SimpleRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<SimpleRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<SimpleRequestPayload> type() {
        return SimpleRequestPayload.class;
    }

    @Override
    public SimpleRequestPayload createDefault() {
        return SimpleRequestPayload.builder().build();
    }

    @Override
    public SimpleRequestPayload createVariant() {
        return SimpleRequestPayload.builder().build();
    }
}