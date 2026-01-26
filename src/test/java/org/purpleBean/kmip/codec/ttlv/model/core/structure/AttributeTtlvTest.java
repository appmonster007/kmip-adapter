package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.AttributeValueStructure;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Attribute Ttlv Serialization Tests")
class AttributeTtlvTest extends AbstractTtlvSerializationTestSuite<Attribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<Attribute> type() {
        return Attribute.class;
    }

    @Override
    protected Attribute createDefault() {
        return Attribute.of(State.Standard.COMPROMISED.inst());
    }

    @Override
    protected Attribute createVariant() {
        List<KmipDataType> list = new ArrayList<>();
        list.add(AttributeValueTextString.of("value"));
        list.add(AttributeValueInteger.of(1));
        return Attribute.of(CustomAttribute.of("x-apple", AttributeValueStructure.of(list)));
    }
}
