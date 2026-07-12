package org.purpleBean.kmip.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PrivateKeyAttributes Domain Tests")
class PrivateKeyAttributesTest extends AbstractKmipStructureTestSuite<PrivateKeyAttributes> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<PrivateKeyAttributes> type() {
        return PrivateKeyAttributes.class;
    }

    @Override
    protected PrivateKeyAttributes createDefault() {
        return PrivateKeyAttributes.of(Collections.emptyList());
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
        assertThat(values).isEmpty();
    }
}
