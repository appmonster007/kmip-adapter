package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.PrivateExponent;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentRsaPrivateKey XML Serialization Tests")
class TransparentRsaPrivateKeyXmlTest extends AbstractXmlSerializationTestSuite<TransparentRsaPrivateKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentRsaPrivateKey> type() {
        return TransparentRsaPrivateKey.class;
    }

    @Override
    protected TransparentRsaPrivateKey createDefault() {
        return TransparentRsaPrivateKey.of(
                Modulus.of(BigInteger.valueOf(1)),
                PrivateExponent.of(BigInteger.valueOf(2)),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    protected TransparentRsaPrivateKey createVariant() {
        return TransparentRsaPrivateKey.of(
                Modulus.of(BigInteger.valueOf(3)),
                null,
                null,
                P.of(BigInteger.valueOf(4)),
                Q.of(BigInteger.valueOf(5)),
                null,
                null,
                null
        );
    }
}