package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.common.structure.TransparentDsaPublicKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentDsaPublicKey JSON Serialization Tests")
class TransparentDsaPublicKeyJsonTest extends AbstractJsonSerializationSuite<TransparentDsaPublicKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentDsaPublicKey> type() {
        return TransparentDsaPublicKey.class;
    }

    @Override
    protected TransparentDsaPublicKey createDefault() {
        return TransparentDsaPublicKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                Y.of(BigInteger.valueOf(4))
        );
    }

    @Override
    protected TransparentDsaPublicKey createVariant() {
        return TransparentDsaPublicKey.of(
                P.of(BigInteger.valueOf(5)),
                Q.of(BigInteger.valueOf(6)),
                G.of(BigInteger.valueOf(7)),
                Y.of(BigInteger.valueOf(8))
        );
    }
}