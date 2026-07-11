package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityCountry;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationAuthorityCountry Json Serialization Tests")
class ValidationAuthorityCountryJsonTest extends AbstractJsonSerializationTestSuite<ValidationAuthorityCountry> {

    @Override
    public Class<ValidationAuthorityCountry> type() {
        return ValidationAuthorityCountry.class;
    }

    @Override
    public ValidationAuthorityCountry createDefault() {
        return ValidationAuthorityCountry.of("default-string");
    }

    @Override
    public ValidationAuthorityCountry createVariant() {
        return ValidationAuthorityCountry.of("variant-string");
    }
}