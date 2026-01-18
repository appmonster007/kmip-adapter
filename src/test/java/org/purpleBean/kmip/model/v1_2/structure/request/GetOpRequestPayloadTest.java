package org.purpleBean.kmip.model.v1_2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GetOpRequestPayload Domain Tests")
class GetOpRequestPayloadTest extends AbstractKmipStructureTestSuite<GetOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<GetOpRequestPayload> type() {
        return GetOpRequestPayload.class;
    }

    @Override
    protected GetOpRequestPayload createDefault() {
        return GetOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 0;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(1);
        assertThat(values.getFirst()).isInstanceOf(UniqueIdentifier.class);
    }
}