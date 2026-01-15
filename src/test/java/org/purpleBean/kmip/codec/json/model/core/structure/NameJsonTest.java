package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("Name Json Serialization Tests")
class NameJsonTest extends AbstractJsonSerializationTestSuite<Name> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<Name> type() {
        return Name.class;
    }

    @Override
    protected Name createDefault() {
        return Name.builder()
                .nameValue(NameValue.of("some-name"))
                .nameType(NameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
                .build();
    }

    @Override
    protected Name createVariant() {
        return Name.builder()
                .nameValue(NameValue.of("some-variant-name"))
                .nameType(NameType.Standard.URI.inst())
                .build();
    }
}
