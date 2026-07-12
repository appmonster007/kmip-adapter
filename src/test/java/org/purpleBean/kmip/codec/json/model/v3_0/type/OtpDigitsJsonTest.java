package org.purpleBean.kmip.codec.json.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.OtpDigits;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OtpDigits Json Serialization Tests")
class OtpDigitsJsonTest extends AbstractJsonSerializationTestSuite<OtpDigits> {

    @Override
    public Class<OtpDigits> type() {
        return OtpDigits.class;
    }

    @Override
    public OtpDigits createDefault() {
        return OtpDigits.of(123);
    }

    @Override
    public OtpDigits createVariant() {
        return OtpDigits.of(456);
    }
}