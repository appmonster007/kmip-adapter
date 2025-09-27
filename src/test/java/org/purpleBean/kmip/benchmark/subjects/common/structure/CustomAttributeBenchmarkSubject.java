package org.purpleBean.kmip.benchmark.subjects.common.structure;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.CustomAttribute;

import java.nio.ByteBuffer;

public class CustomAttributeBenchmarkSubject implements KmipBenchmarkSubject {

    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private CustomAttribute obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public CustomAttributeBenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "CustomAttribute";
    }

    @Override
    public void setup() throws Exception {
        json = KmipCodecManager.getJsonMapper();
        xml = KmipCodecManager.getXmlMapper();
        ttlv = KmipCodecManager.getTtlvMapper();

        State state = new State(State.Standard.ACTIVE);
        obj = CustomAttribute.of("x-custom-state", state);

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
        return json.readValue(jsonStr, CustomAttribute.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, CustomAttribute.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), CustomAttribute.class);
    }
}
