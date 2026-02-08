package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Attribute Json Serialization Tests")
class AttributeJsonTest extends AbstractJsonSerializationTestSuite<Attribute> {
    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<Attribute> type() {
        return Attribute.class;
    }

    @Override
    public Attribute createDefault() {
        return Attribute.of(State.Standard.COMPROMISED.inst());
    }

    @Override
    public Attribute createVariant() {
        List<KmipDataType> list = new ArrayList<>();
        list.add(AttributeValue.ofTextString("value"));
        list.add(AttributeValue.ofInteger(1));
        return Attribute.of(CustomAttribute.of("x-apple", AttributeValue.ofStructure(list)));
    }
}
