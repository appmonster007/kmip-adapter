package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SecretData Domain Tests")
class SecretDataTest extends AbstractKmipStructureTestSuite<SecretData> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<SecretData> type() {
        return SecretData.class;
    }

    @Override
    protected SecretData createDefault() {
        return SecretData.builder()
                .secretDataType(SecretDataType.Standard.PASSWORD.inst())
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
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
        assertThat(values.get(0)).isInstanceOf(SecretDataType.class);
        assertThat(values.get(1)).isInstanceOf(KeyBlock.class);
    }
}