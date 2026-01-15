package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("TagLength Domain Tests")
class TagLengthTest extends AbstractKmipDataTypeTestSuite<TagLength> {

    @Override
    protected Class<TagLength> type() {
        return TagLength.class;
    }

    @Override
    protected TagLength createDefault() {
        return TagLength.of(128);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}