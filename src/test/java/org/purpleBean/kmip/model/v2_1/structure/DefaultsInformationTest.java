package org.purpleBean.kmip.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DefaultsInformation Domain Tests")
class DefaultsInformationTest extends AbstractKmipStructureTestSuite<DefaultsInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<DefaultsInformation> type() {
        return DefaultsInformation.class;
    }

    @Override
    protected DefaultsInformation createDefault() {
        return DefaultsInformation.of(List.of(
                ObjectDefaults.builder()
                        .objectType(ObjectType.Standard.CERTIFICATE.inst())
                        .attributes(Attributes.of(Collections.emptyList()))
                        .build()
        ));
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
        assertThat(values).hasSizeGreaterThanOrEqualTo(1);
    }
}
