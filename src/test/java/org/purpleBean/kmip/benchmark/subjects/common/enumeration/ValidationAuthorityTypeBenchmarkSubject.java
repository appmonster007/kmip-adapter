package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.benchmark.util.MapperFactory;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

import java.nio.ByteBuffer;

public class ValidationAuthorityTypeBenchmarkSubject implements KmipBenchmarkSubject {
    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ValidationAuthorityType obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public ValidationAuthorityTypeBenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "ValidationAuthorityType";
    }

    @Override
    public void setup() throws Exception {
        json = MapperFactory.getJsonMapper();

        xml = MapperFactory.getXmlMapper();

        ttlv = MapperFactory.getTtlvMapper();

        obj = new ValidationAuthorityType(ValidationAuthorityType.Standard.UNSPECIFIED);

        // Pre-serialize to ensure all mappers are initialized
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
        return json.readValue(jsonStr, ValidationAuthorityType.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ValidationAuthorityType.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ValidationAuthorityType.class);
    }
}
