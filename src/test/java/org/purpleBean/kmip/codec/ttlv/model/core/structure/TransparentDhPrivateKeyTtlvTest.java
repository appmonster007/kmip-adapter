package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.TransparentDhPrivateKey;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentDhPrivateKey TTLV Serialization Tests")
class TransparentDhPrivateKeyTtlvTest extends AbstractTtlvSerializationTestSuite<TransparentDhPrivateKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<TransparentDhPrivateKey> type() {
        return TransparentDhPrivateKey.class;
    }

    @Override
    public TransparentDhPrivateKey createDefault() {
        return TransparentDhPrivateKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                J.of(BigInteger.valueOf(4)),
                X.of(BigInteger.valueOf(5))
        );
    }

    @Override
    public TransparentDhPrivateKey createVariant() {
        return TransparentDhPrivateKey.of(
                P.of(BigInteger.valueOf(6)),
                Q.of(BigInteger.valueOf(7)),
                G.of(BigInteger.valueOf(8)),
                J.of(BigInteger.valueOf(9)),
                X.of(BigInteger.valueOf(10))
        );
    }
}