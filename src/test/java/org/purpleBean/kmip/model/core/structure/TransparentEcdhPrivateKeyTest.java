package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.model.core.structure.TransparentEcdhPrivateKey;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentEcdhPrivateKey Domain Tests")
class TransparentEcdhPrivateKeyTest extends AbstractKmipStructureTestSuite<TransparentEcdhPrivateKey> {

    @Override
    protected Class<TransparentEcdhPrivateKey> type() {
        return TransparentEcdhPrivateKey.class;
    }

    @Override
    protected TransparentEcdhPrivateKey createDefault() {
        return TransparentEcdhPrivateKey.of(
                RecommendedCurve.Standard.P_192.inst(),
                D.of(BigInteger.valueOf(1))
        );
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(2);
        assertThat(values.get(0)).isInstanceOf(RecommendedCurve.class);
        assertThat(values.get(1)).isInstanceOf(D.class);
    }
}