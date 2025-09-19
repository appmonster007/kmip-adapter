package org.purpleBean.kmip.benchmark.subjects.common.structure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class SampleStructureBenchmarkSubject implements KmipBenchmarkSubject {

    private ObjectMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private SampleStructure obj;

    private String jsonStr;
    private String xmlStr;
    private ByteBuffer ttlvBuf;

    @Override
    public String name() {
        return "SampleStructure";
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
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder().dateTime(fixed).build();
        State state = new State(State.Standard.ACTIVE);
        obj = SampleStructure.builder()
                .activationDate(activationDate)
                .state(state)
                .build();

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
        return json.readValue(jsonStr, SampleStructure.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, SampleStructure.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), SampleStructure.class);
    }
}
