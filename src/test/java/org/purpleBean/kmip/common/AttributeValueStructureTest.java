package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.common.structure.AttributeValueStructure;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("AttributeValue.Structure Domain Tests")
class AttributeValueStructureTest extends AbstractKmipDataTypeSuite<AttributeValueStructure> {

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
