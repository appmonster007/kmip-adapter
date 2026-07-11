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
import org.purpleBean.kmip.model.v2_1.type.ValidationLevel;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationLevel Json Serialization Tests")
class ValidationLevelJsonTest extends AbstractJsonSerializationTestSuite<ValidationLevel> {

    @Override
    public Class<ValidationLevel> type() {
        return ValidationLevel.class;
    }

    @Override
    public ValidationLevel createDefault() {
        return ValidationLevel.of(123);
    }

    @Override
    public ValidationLevel createVariant() {
        return ValidationLevel.of(456);
    }
}