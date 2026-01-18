package org.purpleBean.kmip.codec.xml.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SimpleRequestPayload Xml Serialization Tests")
class SimpleRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<SimpleRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<SimpleRequestPayload> type() {
        return SimpleRequestPayload.class;
    }

    @Override
    protected SimpleRequestPayload createDefault() {
        return SimpleRequestPayload.builder().build();
    }

    @Override
    protected SimpleRequestPayload createVariant() {
        return SimpleRequestPayload.builder().build();
    }
}