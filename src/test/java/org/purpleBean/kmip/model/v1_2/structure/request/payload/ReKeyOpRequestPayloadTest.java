package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ReKeyOpRequestPayload Domain Tests")
class ReKeyOpRequestPayloadTest extends AbstractKmipStructureTestSuite<ReKeyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<ReKeyOpRequestPayload> type() {
        return ReKeyOpRequestPayload.class;
    }

    @Override
    protected ReKeyOpRequestPayload createDefault() {
        return ReKeyOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .offset(Offset.builder().value(100).build())
                .templateAttribute(TemplateAttribute.builder().build())
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
        assertThat(values).hasSize(3);
        assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
        assertThat(values.get(1)).isInstanceOf(Offset.class);
        assertThat(values.get(2)).isInstanceOf(TemplateAttribute.class);
    }
}