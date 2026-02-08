package org.purpleBean.kmip.codec.json.model.core.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SimpleResponsePayload Json Serialization Tests")
class SimpleResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<SimpleResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<SimpleResponsePayload> type() {
        return SimpleResponsePayload.class;
    }

    @Override
    public SimpleResponsePayload createDefault() {
        return SimpleResponsePayload.builder().build();
    }

    @Override
    public SimpleResponsePayload createVariant() {
        return SimpleResponsePayload.builder().build();
    }
}
