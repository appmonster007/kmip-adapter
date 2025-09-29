package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.common.structure.Name;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("Name TTLV Serialization Tests")
class NameTtlvTest extends AbstractTtlvSerializationSuite<Name> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<Name> type() {
        return Name.class;
    }

    @Override
    protected Name createDefault() {
        return Name.builder()
                .nameValue(NameValue.of("some-name"))
                .nameType(new NameType(NameType.Standard.UNINTERPRETED_TEXT_STRING))
                .build();
    }

    @Override
    protected Name createVariant() {
        return Name.builder()
                .nameValue(NameValue.of("some-variant-name"))
                .nameType(new NameType(NameType.Standard.URI))
                .build();
    }
}
