package org.purpleBean.kmip.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.v2_1.enumeration.RotateNameType;
import org.purpleBean.kmip.model.v2_1.type.RotateNameValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RotateName Domain Tests")
class RotateNameTest extends AbstractKmipStructureTestSuite<RotateName> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<RotateName> type() {
        return RotateName.class;
    }

    @Override
    protected RotateName createDefault() {
        return RotateName.of(
                RotateNameValue.of("default"),
                RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst());
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
        assertThat(values.get(0)).isInstanceOf(RotateNameValue.class);
        assertThat(values.get(1)).isInstanceOf(RotateNameType.class);
    }
}
