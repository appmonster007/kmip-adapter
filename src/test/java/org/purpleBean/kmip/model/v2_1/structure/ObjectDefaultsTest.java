package org.purpleBean.kmip.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ObjectDefaults Domain Tests")
class ObjectDefaultsTest extends AbstractKmipStructureTestSuite<ObjectDefaults> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<ObjectDefaults> type() {
        return ObjectDefaults.class;
    }

    @Override
    protected ObjectDefaults createDefault() {
        return ObjectDefaults.builder()
                .objectType(ObjectType.Standard.CERTIFICATE.inst())
                .attributes(Attributes.of(Collections.emptyList()))
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
    }
}
