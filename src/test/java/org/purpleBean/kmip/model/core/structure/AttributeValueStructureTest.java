package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.util.List;

@DisplayName("AttributeValue.Structure Domain Tests")
class AttributeValueStructureTest extends AbstractKmipDataTypeTestSuite<AttributeValueStructure> {

    @Override
    protected Class<AttributeValueStructure> type() {
        return AttributeValueStructure.class;
    }

    @Override
    protected AttributeValueStructure createDefault() {
        return AttributeValueStructure.of(List.of(AttributeValueInteger.of(123)));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }
}
