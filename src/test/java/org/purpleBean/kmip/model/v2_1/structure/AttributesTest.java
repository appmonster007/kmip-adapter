package org.purpleBean.kmip.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.Collections;
import java.util.List;

@DisplayName("Attributes Domain Tests")
class AttributesTest extends AbstractKmipStructureTestSuite<Attributes> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<Attributes> type() {
        return Attributes.class;
    }

    @Override
    public Attributes createDefault() {
        return Attributes.of(Collections.emptyList());
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
        // empty list is valid
    }
}
