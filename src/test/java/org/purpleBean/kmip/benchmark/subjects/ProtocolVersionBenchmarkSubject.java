package org.purpleBean.kmip.benchmark.subjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;

import java.nio.ByteBuffer;

public class ProtocolVersionBenchmarkSubject implements KmipBenchmarkSubject {

    private ObjectMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ProtocolVersion obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public ProtocolVersionBenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "ProtocolVersion";
    }

    @Override
    public void setup() throws Exception {
        // Configure mappers
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

        // Create test object with sample data
        obj = ProtocolVersion.of(1, 0);

        // Pre-serialize for benchmarks
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
        return json.readValue(jsonStr, ProtocolVersion.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ProtocolVersion.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ProtocolVersion.class);
    }
}
