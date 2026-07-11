package org.purpleBean.kmip.codec.xml.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionAdditionalData;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AuthenticatedEncryptionAdditionalData Xml Serialization Tests")
class AuthenticatedEncryptionAdditionalDataXmlTest extends AbstractXmlSerializationTestSuite<AuthenticatedEncryptionAdditionalData> {

    @Override
    public Class<AuthenticatedEncryptionAdditionalData> type() {
        return AuthenticatedEncryptionAdditionalData.class;
    }

    @Override
    public AuthenticatedEncryptionAdditionalData createDefault() {
        return AuthenticatedEncryptionAdditionalData.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public AuthenticatedEncryptionAdditionalData createVariant() {
        return AuthenticatedEncryptionAdditionalData.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}