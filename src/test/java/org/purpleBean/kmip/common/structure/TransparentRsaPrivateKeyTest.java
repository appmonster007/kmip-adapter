package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.PrivateExponent;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentRsaPrivateKey Domain Tests")
class TransparentRsaPrivateKeyTest extends AbstractKmipStructureTestSuite<TransparentRsaPrivateKey> {

    @Override
    protected Class<TransparentRsaPrivateKey> type() {
        return TransparentRsaPrivateKey.class;
    }

    @Override
    protected TransparentRsaPrivateKey createDefault() {
        return TransparentRsaPrivateKey.of(
                Modulus.of(BigInteger.valueOf(1)),
                PrivateExponent.of(BigInteger.valueOf(2)),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 1;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(2);
        assertThat(values.get(0)).isInstanceOf(Modulus.class);
        assertThat(values.get(1)).isInstanceOf(PrivateExponent.class);
    }
}