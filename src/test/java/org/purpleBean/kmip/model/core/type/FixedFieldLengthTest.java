package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("FixedFieldLength Domain Tests")
class FixedFieldLengthTest extends AbstractKmipDataTypeTestSuite<FixedFieldLength> {

    @Override
    protected Class<FixedFieldLength> type() {
        return FixedFieldLength.class;
    }

    @Override
    protected FixedFieldLength createDefault() {
        return FixedFieldLength.of(128);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}