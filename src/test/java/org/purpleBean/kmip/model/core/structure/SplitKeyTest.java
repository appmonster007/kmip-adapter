package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SplitKey Domain Tests")
class SplitKeyTest extends AbstractKmipStructureTestSuite<SplitKey> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<SplitKey> type() {
        return SplitKey.class;
    }

    @Override
    protected SplitKey createDefault() {
        return SplitKey.builder()
                .splitKeyParts(SplitKeyParts.of(1))
                .keyPartIdentifier(KeyPartIdentifier.of(1))
                .splitKeyThreshold(SplitKeyThreshold.of(1))
                .splitKeyMethod(SplitKeyMethod.Standard.XOR.inst())
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
        return 5;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(5);
        assertThat(values.get(0)).isInstanceOf(SplitKeyParts.class);
        assertThat(values.get(1)).isInstanceOf(KeyPartIdentifier.class);
        assertThat(values.get(2)).isInstanceOf(SplitKeyThreshold.class);
        assertThat(values.get(3)).isInstanceOf(SplitKeyMethod.class);
        assertThat(values.get(4)).isInstanceOf(KeyBlock.class);
    }
}