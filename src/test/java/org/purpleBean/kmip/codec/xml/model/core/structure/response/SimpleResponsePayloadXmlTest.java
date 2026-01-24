package org.purpleBean.kmip.codec.xml.model.core.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SimpleResponsePayload Xml Serialization Tests")
class SimpleResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<SimpleResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<SimpleResponsePayload> type() {
        return SimpleResponsePayload.class;
    }

    @Override
    protected SimpleResponsePayload createDefault() {
        return SimpleResponsePayload.builder().build();
    }

    @Override
    protected SimpleResponsePayload createVariant() {
        return SimpleResponsePayload.builder().build();
    }
}
