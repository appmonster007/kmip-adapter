package org.purpleBean.kmip.codec.json.model.core.type;

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
import org.purpleBean.kmip.model.core.type.OtpDigest;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OtpDigest Json Serialization Tests")
class OtpDigestJsonTest extends AbstractJsonSerializationTestSuite<OtpDigest> {

    @Override
    public Class<OtpDigest> type() {
        return OtpDigest.class;
    }

    @Override
    public OtpDigest createDefault() {
        return OtpDigest.of(CryptographicAlgorithm.Standard.AES);
    }

    @Override
    public OtpDigest createVariant() {
        return OtpDigest.of(CryptographicAlgorithm.Standard.RSA);
    }
}