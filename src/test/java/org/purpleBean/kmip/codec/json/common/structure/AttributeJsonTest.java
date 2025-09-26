package org.purpleBean.kmip.codec.json.common.structure;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockedStatic;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.structure.Attribute;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@Slf4j
@DisplayName("Attribute Json Serialization Tests")
class AttributeJsonTest extends AbstractJsonSerializationSuite<Attribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    private MockedStatic<KmipAttribute> mockKmipAttribute;

    @Override
    protected void setupTestSpecificResources() {
        super.setupTestSpecificResources();
        mockKmipAttribute = mockStatic(KmipAttribute.class);
        mockKmipAttribute.when(() -> KmipAttribute.getClassFromRegistry(any(), any(), any())).thenReturn(ActivationDate.class);
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
        ActivationDate activationDate = ActivationDate.builder().dateTime(FIXED_TIME).build();
        return Attribute.of(activationDate);
    }

    @Override
    protected Attribute createVariant() {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("key", "value");
            map.put("key2", 1);
            return Attribute.of(Attribute.CustomAttribute.ofStructureString("x-apple", map));
        } catch (Exception e) {
            log.error("Failed to create variant", e);
            return null;
        }
    }
}
