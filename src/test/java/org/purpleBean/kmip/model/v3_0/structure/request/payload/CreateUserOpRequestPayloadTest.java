package org.purpleBean.kmip.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

@DisplayName("CreateUserOpRequestPayload Domain Tests")
class CreateUserOpRequestPayloadTest extends AbstractKmipStructureTestSuite<CreateUserOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    protected Class<CreateUserOpRequestPayload> type() {
        return CreateUserOpRequestPayload.class;
    }

    @Override
    protected CreateUserOpRequestPayload createDefault() {
        return CreateUserOpRequestPayload.builder().attributes(Attributes.of(java.util.List.of())).build();
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