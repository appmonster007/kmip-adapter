package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.X;
import org.purpleBean.kmip.common.structure.TransparentDsaPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentDsaPrivateKey XML Serialization Tests")
class TransparentDsaPrivateKeyXmlTest extends AbstractXmlSerializationSuite<TransparentDsaPrivateKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentDsaPrivateKey> type() {
        return TransparentDsaPrivateKey.class;
    }

    @Override
    protected TransparentDsaPrivateKey createDefault() {
        return TransparentDsaPrivateKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                X.of(BigInteger.valueOf(4))
        );
    }

    @Override
    protected TransparentDsaPrivateKey createVariant() {
        return TransparentDsaPrivateKey.of(
                P.of(BigInteger.valueOf(5)),
                Q.of(BigInteger.valueOf(6)),
                G.of(BigInteger.valueOf(7)),
                X.of(BigInteger.valueOf(8))
        );
    }
}