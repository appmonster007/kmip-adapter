package org.purpleBean.kmip.model.v3_0.structure.link;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ParentLink Domain Tests")
class ParentLinkTest extends AbstractKmipStructureTestSuite<ParentLink> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    protected Class<ParentLink> type() {
        return ParentLink.class;
    }

    @Override
    protected ParentLink createDefault() {
        // TODO: Create a default instance of the structure
        return ParentLink.of(UniqueIdentifier.of("test-id"));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        // TODO: Set the expected minimum number of components
        return 1;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        // TODO: Validate the components of the structure
        assertThat(values).hasSize(1);
    }
}