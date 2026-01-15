package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPrivateKey;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.PrivateExponent;
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