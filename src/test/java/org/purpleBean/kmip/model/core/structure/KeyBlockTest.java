package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KeyBlock Domain Tests")
class KeyBlockTest extends AbstractKmipStructureTestSuite<KeyBlock> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<KeyBlock> type() {
        return KeyBlock.class;
    }

    @Override
    protected KeyBlock createDefault() {
        return KeyBlock.builder()
                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                .build();
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
        assertThat(values).hasSize(1);
        assertThat(values.getFirst()).isInstanceOf(KeyFormatType.class);
    }
}