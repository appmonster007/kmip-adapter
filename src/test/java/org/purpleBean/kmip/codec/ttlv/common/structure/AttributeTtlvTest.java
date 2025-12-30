package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.structure.Attribute;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Attribute Ttlv Serialization Tests")
class AttributeTtlvTest extends AbstractTtlvSerializationSuite<Attribute> {

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
        list.add(AttributeValue.TextString.of("value"));
        list.add(AttributeValue.Integer.of(1));
        return Attribute.of(CustomAttribute.of("x-apple", AttributeValue.Structure.of(list)));
    }
}
