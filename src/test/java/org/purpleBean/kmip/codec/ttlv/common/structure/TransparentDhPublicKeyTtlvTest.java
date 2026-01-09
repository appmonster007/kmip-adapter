package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentDhPublicKey;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentDhPublicKey TTLV Serialization Tests")
class TransparentDhPublicKeyTtlvTest extends AbstractTtlvSerializationSuite<TransparentDhPublicKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentDhPublicKey> type() {
        return TransparentDhPublicKey.class;
    }

    @Override
    protected TransparentDhPublicKey createDefault() {
        return TransparentDhPublicKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                J.of(BigInteger.valueOf(4)),
                Y.of(BigInteger.valueOf(5))
        );
    }

    @Override
    protected TransparentDhPublicKey createVariant() {
        return TransparentDhPublicKey.of(
                P.of(BigInteger.valueOf(6)),
                Q.of(BigInteger.valueOf(7)),
                G.of(BigInteger.valueOf(8)),
                J.of(BigInteger.valueOf(9)),
                Y.of(BigInteger.valueOf(10))
        );
    }
}