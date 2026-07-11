package org.purpleBean.kmip.codec.ttlv.model.core.type;

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
import org.purpleBean.kmip.model.core.type.OtpDigits;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpDigits Ttlv Serialization Tests")
class OtpDigitsTtlvTest extends AbstractTtlvSerializationTestSuite<OtpDigits> {

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