package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.PrivateExponent;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.structure.TransparentRsaPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentRsaPrivateKey JSON Serialization Tests")
class TransparentRsaPrivateKeyJsonTest extends AbstractJsonSerializationSuite<TransparentRsaPrivateKey> {

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