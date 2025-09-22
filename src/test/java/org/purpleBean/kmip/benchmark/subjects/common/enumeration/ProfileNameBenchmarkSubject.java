package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.benchmark.util.MapperFactory;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.ProfileName;

import java.nio.ByteBuffer;

public class ProfileNameBenchmarkSubject implements KmipBenchmarkSubject {
    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ProfileName obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public ProfileNameBenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "ProfileName";
    }

    @Override
    public void setup() throws Exception {
        json = MapperFactory.getJsonMapper();
        xml = MapperFactory.getXmlMapper();
        ttlv = MapperFactory.getTtlvMapper();

        obj = new ProfileName(ProfileName.Standard.PLACEHOLDER_1);

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
        return json.readValue(jsonStr, ProfileName.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ProfileName.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ProfileName.class);
    }
}
