package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPublicKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("TransparentDsaPublicKey JSON Serialization Tests")
class TransparentDsaPublicKeyJsonTest extends AbstractJsonSerializationTestSuite<TransparentDsaPublicKey> {

    @Override
    public Class<TransparentDsaPublicKey> type() {
        return TransparentDsaPublicKey.class;
    }

    @Override
    public TransparentDsaPublicKey createDefault() {
        return TransparentDsaPublicKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                Y.of(BigInteger.valueOf(4))
        );
    }

    @Override
    public TransparentDsaPublicKey createVariant() {
        return TransparentDsaPublicKey.of(
                P.of(BigInteger.valueOf(5)),
                Q.of(BigInteger.valueOf(6)),
                G.of(BigInteger.valueOf(7)),
                Y.of(BigInteger.valueOf(8))
        );
    }
}