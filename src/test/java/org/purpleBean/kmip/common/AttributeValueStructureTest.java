package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("AttributeValue.Structure Domain Tests")
class AttributeValueStructureTest extends AbstractKmipDataTypeSuite<AttributeValue.Structure> {

    @Override
    protected Class<AttributeValue.Structure> type() {
        return AttributeValue.Structure.class;
    }

    @Override
    protected AttributeValue.Structure createDefault() {
        return AttributeValue.Structure.of(List.of(AttributeValue.Integer.of(123)));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }
}
