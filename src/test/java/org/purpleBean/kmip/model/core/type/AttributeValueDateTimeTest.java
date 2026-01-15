package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.time.OffsetDateTime;

@DisplayName("AttributeValue.DateTime Domain Tests")
class AttributeValueDateTimeTest extends AbstractKmipDataTypeTestSuite<AttributeValueDateTime> {

    @Override
    protected Class<AttributeValueDateTime> type() {
        return AttributeValueDateTime.class;
    }

    @Override
    protected AttributeValueDateTime createDefault() {
        return AttributeValueDateTime.of(OffsetDateTime.now());
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.DATE_TIME;
    }
}
