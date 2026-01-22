package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EncryptOpResponsePayload Domain Tests")
class EncryptOpResponsePayloadTest extends AbstractKmipStructureTestSuite<EncryptOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<EncryptOpResponsePayload> type() {
        return EncryptOpResponsePayload.class;
    }

    @Override
    protected EncryptOpResponsePayload createDefault() {
        return EncryptOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .data(DataByteString.of(new byte[]{1, 2, 3}))
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
        assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
        assertThat(values.get(1)).isInstanceOf(DataByteString.class);
    }
}
