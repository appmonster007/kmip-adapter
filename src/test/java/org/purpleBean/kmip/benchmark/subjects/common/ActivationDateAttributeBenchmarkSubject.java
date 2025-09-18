package org.purpleBean.kmip.benchmark.subjects.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ActivationDateAttributeBenchmarkSubject implements KmipBenchmarkSubject {

    private ObjectMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ActivationDateAttribute obj;

    private String jsonStr;
    private String xmlStr;
    private ByteBuffer ttlvBuf;

    @Override
    public String name() {
        return "ActivationDateAttribute";
    }

    @Override
    public void setup() throws Exception {
        json = new ObjectMapper();
        json.findAndRegisterModules();
        json.registerModule(new JavaTimeModule());
        json.registerModule(new KmipJsonModule());

        xml = new XmlMapper();
        xml.findAndRegisterModules();
        xml.registerModule(new JavaTimeModule());
        xml.registerModule(new KmipXmlModule());

        ttlv = new TtlvMapper();
        ttlv.registerModule(new KmipTtlvModule());

        // Create test object
        obj = ActivationDateAttribute.builder()
            .dateTime(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
            .build();

        // Serialize to all formats for deserialization benchmarks
        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ByteBuffer.wrap(ttlv.writeValueAsBytes(obj));
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
        return json.readValue(jsonStr, ActivationDateAttribute.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ActivationDateAttribute.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ActivationDateAttribute.class);
    }
}
