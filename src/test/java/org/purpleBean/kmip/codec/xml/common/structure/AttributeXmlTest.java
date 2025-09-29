package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.mockito.MockedStatic;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.structure.Attribute;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@DisplayName("Attribute Xml Serialization Tests")
class AttributeXmlTest extends AbstractXmlSerializationSuite<Attribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    private MockedStatic<KmipAttribute> mockKmipAttribute;

    @Override
    protected void setupTestSpecificResources() {
        super.setupTestSpecificResources();
        mockKmipAttribute = mockStatic(KmipAttribute.class);
        mockKmipAttribute.when(() -> KmipAttribute.getClassFromRegistry(any(), any())).thenReturn(ActivationDate.class);
    }

    @Override
    protected void cleanupTestSpecificResources() {
        mockKmipAttribute.close();
    }

    @Override
    protected Class<Attribute> type() {
        return Attribute.class;
    }

    @Override
    protected Attribute createDefault() {
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME).build();
        return Attribute.of(activationDate);
    }

    @Override
    protected Attribute createVariant() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", "value");
        map.put("key2", 1);
        return Attribute.of(CustomAttribute.of("x-apple", map));
    }
}
