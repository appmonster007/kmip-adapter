package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.model.core.structure.TransparentDhPublicKey;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentDhPublicKey Domain Tests")
class TransparentDhPublicKeyTest extends AbstractKmipStructureTestSuite<TransparentDhPublicKey> {

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
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 3;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(5);
        assertThat(values.get(0)).isInstanceOf(P.class);
        assertThat(values.get(1)).isInstanceOf(Q.class);
        assertThat(values.get(2)).isInstanceOf(G.class);
        assertThat(values.get(3)).isInstanceOf(J.class);
        assertThat(values.get(4)).isInstanceOf(Y.class);
    }
}