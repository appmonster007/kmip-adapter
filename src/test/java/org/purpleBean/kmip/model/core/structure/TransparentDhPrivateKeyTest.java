package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentDhPrivateKey Domain Tests")
class TransparentDhPrivateKeyTest extends AbstractKmipStructureTestSuite<TransparentDhPrivateKey> {

    @Override
    protected Class<TransparentDhPrivateKey> type() {
        return TransparentDhPrivateKey.class;
    }

    @Override
    protected TransparentDhPrivateKey createDefault() {
        return TransparentDhPrivateKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                J.of(BigInteger.valueOf(4)),
                X.of(BigInteger.valueOf(5))
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
        assertThat(values.get(4)).isInstanceOf(X.class);
    }
}