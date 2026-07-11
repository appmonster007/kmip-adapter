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
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationVersionMinor Json Serialization Tests")
class ValidationVersionMinorJsonTest extends AbstractJsonSerializationTestSuite<ValidationVersionMinor> {

    @Override
    public Class<ValidationVersionMinor> type() {
        return ValidationVersionMinor.class;
    }

    @Override
    public ValidationVersionMinor createDefault() {
        return ValidationVersionMinor.of(123);
    }

    @Override
    public ValidationVersionMinor createVariant() {
        return ValidationVersionMinor.of(456);
    }
}