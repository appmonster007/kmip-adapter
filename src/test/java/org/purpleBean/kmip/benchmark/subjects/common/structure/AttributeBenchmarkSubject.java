package org.purpleBean.kmip.benchmark.subjects.common.structure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class AttributeBenchmarkSubject implements KmipBenchmarkSubject {

    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private Attribute obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    private KmipSpec spec;

    public AttributeBenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "Attribute";
    }

    @Override
    public void setup() throws Exception {
        json = KmipCodecManager.getJsonMapper();
        xml = KmipCodecManager.getXmlMapper();
        ttlv = KmipCodecManager.getTtlvMapper();

        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ActivationDate activationDate = ActivationDate.builder().value(fixed).build();
        obj = Attribute.of(activationDate);

        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);

        spec = KmipSpec.V1_2;
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }

    @Override
    public String jsonSerialize() throws Exception {
        return KmipContext.withSpec(spec, () -> {
                    try {
                        return json.writeValueAsString(obj);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public Object jsonDeserialize() throws Exception {
        return KmipContext.withSpec(spec, () -> {
                    try {
                        return json.readValue(jsonStr, Attribute.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public String xmlSerialize() throws Exception {
        return KmipContext.withSpec(spec, () -> {
                    try {
                        return xml.writeValueAsString(obj);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return KmipContext.withSpec(spec, () -> {
                    try {
                        return xml.readValue(xmlStr, Attribute.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return KmipContext.withSpec(spec, () -> {
                    try {
                        return ttlv.writeValueAsByteBuffer(obj);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return KmipContext.withSpec(spec, () -> {
                    try {
                        return ttlv.readValue(ttlvBuf.duplicate(), Attribute.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
