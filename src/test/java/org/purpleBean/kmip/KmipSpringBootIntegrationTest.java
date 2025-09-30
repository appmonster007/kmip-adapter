package org.purpleBean.kmip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.purpleBean.kmip.common.CryptographicLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class)
class KmipSpringBootIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSerializeAndDeserializeKmipObject() throws Exception {
        // Arrange
        KmipDataType.register(KmipSpec.V1_2, KmipTag.Standard.CRYPTOGRAPHIC_LENGTH, EncodingType.INTEGER, CryptographicLength.class);
        CryptographicLength original = CryptographicLength.of(256);

        // Act
        String json = objectMapper.writeValueAsString(original);
        CryptographicLength deserialized = objectMapper.readValue(json, CryptographicLength.class);

        // Assert
        assertEquals(original, deserialized);
    }
}
