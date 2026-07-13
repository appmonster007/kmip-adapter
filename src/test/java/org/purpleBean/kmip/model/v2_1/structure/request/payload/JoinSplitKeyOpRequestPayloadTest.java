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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JoinSplitKeyOpRequestPayload Domain Tests")
class JoinSplitKeyOpRequestPayloadTest extends AbstractKmipStructureTestSuite<JoinSplitKeyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<JoinSplitKeyOpRequestPayload> type() {
        return JoinSplitKeyOpRequestPayload.class;
    }

    @Override
    protected JoinSplitKeyOpRequestPayload createDefault() {
        return JoinSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("part-1").build())
                .uniqueIdentifier(UniqueIdentifier.builder().value("part-2").build())
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 3;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
                assertThat(values).hasSize(3);
        assertThat(values.get(0)).isInstanceOf(ObjectType.class);
        assertThat(values.get(1)).isInstanceOf(UniqueIdentifier.class);
        assertThat(values.get(2)).isInstanceOf(UniqueIdentifier.class);
    }
}