package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.X;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentDsaPrivateKey JSON Serialization Tests")
class TransparentDsaPrivateKeyJsonTest extends AbstractJsonSerializationTestSuite<TransparentDsaPrivateKey> {

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