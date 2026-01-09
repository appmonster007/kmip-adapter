package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentDsaPublicKey Domain Tests")
class TransparentDsaPublicKeyTest extends AbstractKmipStructureSuite<TransparentDsaPublicKey> {

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
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 4;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(4);
        assertThat(values.get(0)).isInstanceOf(P.class);
        assertThat(values.get(1)).isInstanceOf(Q.class);
        assertThat(values.get(2)).isInstanceOf(G.class);
        assertThat(values.get(3)).isInstanceOf(Y.class);
    }
}