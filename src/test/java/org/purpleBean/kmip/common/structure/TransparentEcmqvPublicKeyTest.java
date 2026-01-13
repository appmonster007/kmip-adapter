package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentEcmqvPublicKey Domain Tests")
class TransparentEcmqvPublicKeyTest extends AbstractKmipStructureTestSuite<TransparentEcmqvPublicKey> {

    @Override
    protected Class<TransparentEcmqvPublicKey> type() {
        return TransparentEcmqvPublicKey.class;
    }

    @Override
    protected TransparentEcmqvPublicKey createDefault() {
        return TransparentEcmqvPublicKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
                QString.of("test".getBytes())
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
        assertThat(values.get(1)).isInstanceOf(QString.class);
    }
}