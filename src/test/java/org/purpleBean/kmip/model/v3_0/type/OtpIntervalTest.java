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
import org.purpleBean.kmip.model.v3_0.type.OtpInterval;

@DisplayName("OtpInterval Domain Tests")
class OtpIntervalTest extends AbstractKmipDataTypeTestSuite<OtpInterval> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    protected Class<OtpInterval> type() {
        return OtpInterval.class;
    }

    @Override
    public OtpInterval createDefault() {
        return OtpInterval.of(1);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTERVAL;
    }
}