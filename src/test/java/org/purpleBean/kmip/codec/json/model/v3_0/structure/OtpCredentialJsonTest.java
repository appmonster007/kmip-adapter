package org.purpleBean.kmip.codec.json.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.OtpCredential;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OtpCredential Json Serialization Tests")
class OtpCredentialJsonTest extends AbstractJsonSerializationTestSuite<OtpCredential> {

    @Override
    public Class<OtpCredential> type() {
        return OtpCredential.class;
    }

    @Override
    public OtpCredential createDefault() {
        return OtpCredential.builder()
                .otpAlgorithm(OtpAlgorithm.of(OtpAlgorithm.Standard.TOTP))
                .build();
    }

    @Override
    public OtpCredential createVariant() {
        return OtpCredential.builder()
                .otpAlgorithm(OtpAlgorithm.of(OtpAlgorithm.Standard.HOTP))
                .otpSerial(OtpSerial.of("SN-12345"))
                .otpInterval(OtpInterval.of(30))
                .otpDigits(OtpDigits.of(6))
                .otpCounter(OtpCounter.of(42))
                .build();
    }
}