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
import org.purpleBean.kmip.model.v2_1.type.ValidationProfile;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationProfile Json Serialization Tests")
class ValidationProfileJsonTest extends AbstractJsonSerializationTestSuite<ValidationProfile> {

    @Override
    public Class<ValidationProfile> type() {
        return ValidationProfile.class;
    }

    @Override
    public ValidationProfile createDefault() {
        return ValidationProfile.of("default-string");
    }

    @Override
    public ValidationProfile createVariant() {
        return ValidationProfile.of("variant-string");
    }
}