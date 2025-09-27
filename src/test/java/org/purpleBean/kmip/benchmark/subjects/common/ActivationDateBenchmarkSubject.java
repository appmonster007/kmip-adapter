package org.purpleBean.kmip.benchmark.subjects.common;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ActivationDate;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ActivationDateBenchmarkSubject implements KmipBenchmarkSubject {

    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ActivationDate obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public ActivationDateBenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "ActivationDate";
    }

    @Override
    public void setup() throws Exception {
        json = KmipCodecManager.getJsonMapper();
        xml = KmipCodecManager.getXmlMapper();
        ttlv = KmipCodecManager.getTtlvMapper();

        // Create test object
        obj = ActivationDate.builder()
                .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
                .build();

        // Serialize to all formats for deserialization benchmarks
        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }

    @Override
    public String jsonSerialize() throws Exception {
        return json.writeValueAsString(obj);
    }

    @Override
    public Object jsonDeserialize() throws Exception {
        return json.readValue(jsonStr, ActivationDate.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ActivationDate.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ActivationDate.class);
    }
}
