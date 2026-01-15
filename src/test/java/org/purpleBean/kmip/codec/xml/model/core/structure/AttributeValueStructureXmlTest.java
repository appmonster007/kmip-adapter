package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.AttributeValueStructure;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.List;

@DisplayName("AttributeValue.Structure XML Serialization Tests")
class AttributeValueStructureXmlTest extends AbstractXmlSerializationTestSuite<AttributeValueStructure> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<AttributeValueStructure> type() {
        return AttributeValueStructure.class;
    }

    @Override
    protected AttributeValueStructure createDefault() {
        return AttributeValueStructure.of(List.of(AttributeValueInteger.of(123)));
    }

    @Override
    protected AttributeValueStructure createVariant() {
        return AttributeValueStructure.of(List.of(AttributeValueLongInteger.of(456L)));
    }
}
