package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPrivateKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.X;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentDsaPrivateKey Domain Tests")
class TransparentDsaPrivateKeyTest extends AbstractKmipStructureTestSuite<TransparentDsaPrivateKey> {

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
        assertThat(values.get(3)).isInstanceOf(X.class);
    }
}