package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("FixedFieldLength Domain Tests")
class FixedFieldLengthTest extends AbstractKmipDataTypeSuite<FixedFieldLength> {

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