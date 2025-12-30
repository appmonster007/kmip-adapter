package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.time.OffsetDateTime;

@DisplayName("AttributeValue.DateTime Domain Tests")
class AttributeValueDateTimeTest extends AbstractKmipDataTypeSuite<AttributeValue.DateTime> {

    @Override
    protected Class<AttributeValue.DateTime> type() {
        return AttributeValue.DateTime.class;
    }

    @Override
    protected AttributeValue.DateTime createDefault() {
        return AttributeValue.DateTime.of(OffsetDateTime.now());
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.DATE_TIME;
    }
}
