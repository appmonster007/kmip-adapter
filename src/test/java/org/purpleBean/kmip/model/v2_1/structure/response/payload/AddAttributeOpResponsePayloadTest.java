package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AddAttributeOpResponsePayload Domain Tests")
class AddAttributeOpResponsePayloadTest extends AbstractKmipStructureTestSuite<AddAttributeOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<AddAttributeOpResponsePayload> type() {
        return AddAttributeOpResponsePayload.class;
    }

    @Override
    protected AddAttributeOpResponsePayload createDefault() {
        return AddAttributeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
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
        assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    }
}
