package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentEcmqvPrivateKey Domain Tests")
class TransparentEcmqvPrivateKeyTest extends AbstractKmipStructureTestSuite<TransparentEcmqvPrivateKey> {

    @Override
    protected Class<TransparentEcmqvPrivateKey> type() {
        return TransparentEcmqvPrivateKey.class;
    }

    @Override
    protected TransparentEcmqvPrivateKey createDefault() {
        return TransparentEcmqvPrivateKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
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