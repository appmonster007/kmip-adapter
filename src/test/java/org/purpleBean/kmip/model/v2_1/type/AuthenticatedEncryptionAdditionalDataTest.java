package org.purpleBean.kmip.model.v2_1.type;

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


@DisplayName("AuthenticatedEncryptionAdditionalData Domain Tests")
class AuthenticatedEncryptionAdditionalDataTest extends AbstractKmipDataTypeTestSuite<AuthenticatedEncryptionAdditionalData> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<AuthenticatedEncryptionAdditionalData> type() {
        return AuthenticatedEncryptionAdditionalData.class;
    }

    @Override
    protected AuthenticatedEncryptionAdditionalData createDefault() {
        return AuthenticatedEncryptionAdditionalData.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}