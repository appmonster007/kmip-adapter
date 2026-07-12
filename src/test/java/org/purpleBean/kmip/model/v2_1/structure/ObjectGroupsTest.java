package org.purpleBean.kmip.model.v2_1.structure;

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

@DisplayName("ObjectGroups Domain Tests")
class ObjectGroupsTest extends AbstractKmipStructureTestSuite<ObjectGroups> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<ObjectGroups> type() {
        return ObjectGroups.class;
    }

    @Override
    protected ObjectGroups createDefault() {
        return ObjectGroups.of(List.of(ObjectGroup.of("test-group")));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        // TODO: Set the expected minimum number of components
        return 0;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // TODO: Validate the components of the structure
        // assertThat(values).hasSize(0);
    }
}