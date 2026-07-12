package org.purpleBean.kmip.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.purpleBean.kmip.model.v2_1.structure.Constraints;

@DisplayName("GetConstraintsOpResponsePayload Domain Tests")
class GetConstraintsOpResponsePayloadTest extends AbstractKmipStructureTestSuite<GetConstraintsOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<GetConstraintsOpResponsePayload> type() {
        return GetConstraintsOpResponsePayload.class;
    }

    @Override
    protected GetConstraintsOpResponsePayload createDefault() {
        return GetConstraintsOpResponsePayload.builder().constraints(Constraints.of(java.util.List.of())).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        // TODO: Set the expected minimum number of components
        return 0;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        // TODO: Validate the components of the structure
        // assertThat(values).hasSize(0);
    }
}