package org.purpleBean.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlRoundTripTest extends BaseKmipTest {

    private XmlMapper buildMapper() {
        XmlMapper mapper = new XmlMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new KmipXmlModule());
        return mapper;
    }

    @Test
    void protocolVersion_serialization() throws Exception {
        XmlMapper mapper = buildMapper();
        ProtocolVersion original = ProtocolVersion.of(3, 5);
        String xml = mapper.writeValueAsString(original);
        
        // Verify serialization produces expected XML structure
        assertThat(xml).startsWith("<ProtocolVersion>");
        assertThat(xml).contains("<ProtocolVersionMajor");
        assertThat(xml).contains("<ProtocolVersionMinor");
        assertThat(xml).contains("type=\"Integer\"");
        assertThat(xml).contains("value=\"3\"");
        assertThat(xml).contains("value=\"5\"");
        assertThat(xml).endsWith("</ProtocolVersion>");
    }


    @Test
    void state_serialization_withStandard() throws Exception {
        XmlMapper mapper = buildMapper();
        withKmipSpec(KmipSpec.V1_2, () -> {
            try {
                State original = new State(State.Standard.ACTIVE);
                String xml = mapper.writeValueAsString(original);
                
                // Verify serialization produces expected XML structure
                assertThat(xml).contains("<State type=\"Enumeration\"");
                assertThat(xml).contains("value=\"Active\"");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void activationDate_serialization() throws Exception {
        XmlMapper mapper = buildMapper();
        ActivationDateAttribute original = ActivationDateAttribute.builder()
                .dateTime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
                .build();
        String xml = mapper.writeValueAsString(original);
        
        // Verify serialization produces expected XML structure
        assertThat(xml).contains("<ActivationDate");
        assertThat(xml).contains("type=\"DateTime\"");
        assertThat(xml).contains("value=\"2024-01-01T00:00Z\"");
    }

    @Test
    void sampleStructure_serialization() throws Exception {
        XmlMapper mapper = buildMapper();
        withKmipSpec(KmipSpec.V1_2, () -> {
            try {
                State active = new State(State.Standard.ACTIVE);
                ActivationDateAttribute activationDate = ActivationDateAttribute.builder()
                        .dateTime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
                        .build();
                SampleStructure original = SampleStructure.builder()
                        .activationDate(activationDate)
                        .state(active)
                        .build();
                String xml = mapper.writeValueAsString(original);
                
                // Verify serialization produces expected XML structure
                assertThat(xml).startsWith("<SampleStructure>");
                assertThat(xml).contains("<ActivationDate");
                assertThat(xml).contains("<State");
                assertThat(xml).contains("type=\"DateTime\"");
                assertThat(xml).contains("type=\"Enumeration\"");
                assertThat(xml).endsWith("</SampleStructure>");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}


