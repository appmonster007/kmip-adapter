package org.purpleBean.kmip.codec.xml.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.PasswordSaltAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PasswordSaltAlgorithm Xml Serialization Tests")
class PasswordSaltAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<PasswordSaltAlgorithm> {

    @Override
    public Class<PasswordSaltAlgorithm> type() {
        return PasswordSaltAlgorithm.class;
    }

    @Override
    public PasswordSaltAlgorithm createDefault() {
        return PasswordSaltAlgorithm.of(CryptographicAlgorithm.Standard.AES);
    }

    @Override
    public PasswordSaltAlgorithm createVariant() {
        return PasswordSaltAlgorithm.of(CryptographicAlgorithm.Standard.RSA);
    }
}