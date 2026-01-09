package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentRsaPublicKey Domain Tests")
class TransparentRsaPublicKeyTest extends AbstractKmipStructureSuite<TransparentRsaPublicKey> {

    @Override
    protected Class<TransparentRsaPublicKey> type() {
        return TransparentRsaPublicKey.class;
    }

    @Override
    protected TransparentRsaPublicKey createDefault() {
        return TransparentRsaPublicKey.of(
                Modulus.of(BigInteger.valueOf(1)),
                PublicExponent.of(BigInteger.valueOf(2))
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
        assertThat(values.get(0)).isInstanceOf(Modulus.class);
        assertThat(values.get(1)).isInstanceOf(PublicExponent.class);
    }
}