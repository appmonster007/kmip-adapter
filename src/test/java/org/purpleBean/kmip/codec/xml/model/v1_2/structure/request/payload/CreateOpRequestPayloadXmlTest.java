package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateOpRequestPayload Xml Serialization Tests")
class CreateOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<CreateOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<CreateOpRequestPayload> type() {
        return CreateOpRequestPayload.class;
    }

    @Override
    protected CreateOpRequestPayload createDefault() {
        return CreateOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
    }

    @Override
    protected CreateOpRequestPayload createVariant() {
        return CreateOpRequestPayload.builder()
                .objectType(ObjectType.Standard.PUBLIC_KEY.inst())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
    }
}