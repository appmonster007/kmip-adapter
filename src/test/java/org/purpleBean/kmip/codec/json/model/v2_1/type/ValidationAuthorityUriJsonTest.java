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
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityUri;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationAuthorityUri Json Serialization Tests")
class ValidationAuthorityUriJsonTest extends AbstractJsonSerializationTestSuite<ValidationAuthorityUri> {

    @Override
    public Class<ValidationAuthorityUri> type() {
        return ValidationAuthorityUri.class;
    }

    @Override
    public ValidationAuthorityUri createDefault() {
        return ValidationAuthorityUri.of("default-string");
    }

    @Override
    public ValidationAuthorityUri createVariant() {
        return ValidationAuthorityUri.of("variant-string");
    }
}