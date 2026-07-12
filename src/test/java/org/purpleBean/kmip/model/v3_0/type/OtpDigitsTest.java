package org.purpleBean.kmip.model.v3_0.type;

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

import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.model.v3_0.type.OtpDigits;

@DisplayName("OtpDigits Domain Tests")
class OtpDigitsTest extends AbstractKmipDataTypeTestSuite<OtpDigits> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    protected Class<OtpDigits> type() {
        return OtpDigits.class;
    }

    @Override
    public OtpDigits createDefault() {
        return OtpDigits.of(1);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}