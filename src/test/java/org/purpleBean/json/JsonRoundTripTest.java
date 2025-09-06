package org.purpleBean.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonRoundTripTest extends BaseKmipTest {

    private ObjectMapper buildMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new KmipJsonModule());
        return mapper;
    }

    @Test
    void protocolVersion_serialization() throws Exception {
        ObjectMapper jsonMapper = buildMapper();
        ProtocolVersion original = ProtocolVersion.of(1, 2);
        String json = jsonMapper.writeValueAsString(original);
        
        // Verify serialization produces expected JSON structure
        assertThat(json).contains("\"tag\":\"ProtocolVersion\"");
        assertThat(json).contains("\"type\":\"Structure\"");
        assertThat(json).contains("\"value\"");
        assertThat(json).contains("\"tag\":\"ProtocolVersionMajor\"");
        assertThat(json).contains("\"tag\":\"ProtocolVersionMinor\"");
        assertThat(json).contains("\"value\":1");
        assertThat(json).contains("\"value\":2");
    }

    @Test
    void state_serialization_withCustom() throws Exception {
        ObjectMapper mapper = buildMapper();
        
        // Set proper KMIP context for custom state registration
        withKmipSpec(KmipSpec.V1_2, () -> {
            try {
                State.register(-1341234, "Alive", Set.of(KmipSpec.V1_2));
                State original = new State(State.fromName(KmipSpec.V1_2, "Alive"));
                String json = mapper.writeValueAsString(original);
                
                // Verify serialization produces expected JSON structure
                assertThat(json).contains("\"tag\":\"State\"");
                assertThat(json).contains("\"type\":\"Enumeration\"");
                assertThat(json).contains("\"value\":\"Alive\"");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void activationDate_serialization() throws Exception {
        ObjectMapper mapper = buildMapper();
        ActivationDateAttribute original = ActivationDateAttribute.builder()
                .dateTime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
                .build();
        String json = mapper.writeValueAsString(original);
        
        // Verify serialization produces expected JSON structure
        assertThat(json).contains("\"tag\":\"ActivationDate\"");
        assertThat(json).contains("\"type\":\"DateTime\"");
        assertThat(json).contains("\"value\":\"2024-01-01T00:00Z\"");
    }

    @Test
    void sampleStructure_serialization() throws Exception {
        ObjectMapper mapper = buildMapper();
        State active = new State(State.Standard.ACTIVE);
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder()
                .dateTime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
                .build();
        SampleStructure original = SampleStructure.builder()
                .activationDate(activationDate)
                .state(active)
                .build();
        String json = mapper.writeValueAsString(original);
        
        // Verify serialization produces expected JSON structure
        assertThat(json).contains("\"tag\":\"SecretData\"");
        assertThat(json).contains("\"type\":\"Structure\"");
        assertThat(json).contains("\"value\"");
        assertThat(json).contains("\"tag\":\"ActivationDate\"");
        assertThat(json).contains("\"tag\":\"State\"");
    }
}


