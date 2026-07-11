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
import org.purpleBean.kmip.model.core.type.OtpSerial;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpSerial Ttlv Serialization Tests")
class OtpSerialTtlvTest extends AbstractTtlvSerializationTestSuite<OtpSerial> {

    @Override
    public Class<OtpSerial> type() {
        return OtpSerial.class;
    }

    @Override
    public OtpSerial createDefault() {
        return OtpSerial.of("default-string");
    }

    @Override
    public OtpSerial createVariant() {
        return OtpSerial.of("variant-string");
    }
}