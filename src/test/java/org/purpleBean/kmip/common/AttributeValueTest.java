package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AttributeValue Domain Tests")
class AttributeValueTest extends AbstractKmipDataTypeSuite<AttributeValue> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<AttributeValue> type() {
        return AttributeValue.class;
    }

    @Override
    protected AttributeValue createDefault() {
        return AttributeValue.of(FIXED_TIME);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.DATE_TIME;
    }

    @Override
    protected void validateTagAndEncodingType() {
        AttributeValue obj = createDefault();
        KmipTag tag = obj.getKmipTag();
        EncodingType type = obj.getEncodingType();
        assertThat(tag).isNotNull();
        assertThat(type).isNotNull();
        if (expectedEncodingType() != null) {
            assertThat(type).isEqualTo(expectedEncodingType());
        }
    }
}
