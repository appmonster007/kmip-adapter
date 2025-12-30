package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.structure.Attribute;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Attribute Xml Serialization Tests")
class AttributeXmlTest extends AbstractXmlSerializationSuite<Attribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

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
        ActivationDate activationDate = ActivationDate.of(FIXED_TIME);
        return Attribute.of(activationDate);
    }

    @Override
    protected Attribute createVariant() {
        List<KmipDataType> list = new ArrayList<>();
        list.add(AttributeValue.TextString.of("some-value"));
        list.add(AttributeValue.Integer.of(1));
        return Attribute.of(CustomAttribute.of("x-apple", AttributeValue.Structure.of(list)));
    }
}
