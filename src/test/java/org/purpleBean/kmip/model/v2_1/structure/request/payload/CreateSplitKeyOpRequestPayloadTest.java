package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CreateSplitKeyOpRequestPayload Domain Tests")
class CreateSplitKeyOpRequestPayloadTest extends AbstractKmipStructureTestSuite<CreateSplitKeyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<CreateSplitKeyOpRequestPayload> type() {
        return CreateSplitKeyOpRequestPayload.class;
    }

    @Override
    protected CreateSplitKeyOpRequestPayload createDefault() {
        return CreateSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("source-key-id").build())
                .splitKeyParts(SplitKeyParts.of(3))
                .splitKeyThreshold(SplitKeyThreshold.of(2))
                .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.XOR))
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
        assertThat(values.get(0)).isInstanceOf(ObjectType.class);
        assertThat(values.get(1)).isInstanceOf(UniqueIdentifier.class);
        assertThat(values.get(2)).isInstanceOf(SplitKeyParts.class);
        assertThat(values.get(3)).isInstanceOf(SplitKeyThreshold.class);
        assertThat(values.get(4)).isInstanceOf(SplitKeyMethod.class);
    }
}