package org.purpleBean.kmip.benchmark.api;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.nio.ByteBuffer;

/**
 * SPI for adding new KMIP serialization/deserialization benchmark subjects
 * without modifying the core benchmark class.
 */
@Data
public abstract class KmipBenchmarkSubject<T> {

    protected KmipSpec spec = KmipSpec.UnknownVersion;
    protected T obj;
    protected Class<T> type;
    protected JsonMapper json;
    protected XmlMapper xml;
    protected TtlvMapper ttlv;
    protected String jsonStr;
    protected String xmlStr;
    protected ByteBuffer ttlvBuf;

    /**
     * A short, unique name used to select this subject (e.g., via -p subject=Name).
     */
    public abstract String name();

    /**
     * Prepare mappers and test objects.
     */
    public abstract void setup() throws Exception;

    /**
     * Cleanup any state after benchmark.
     */
    public abstract void tearDown();

    public void initialize(T obj, Class<T> type) throws Exception {
        this.obj = obj;
        this.type = type;
        json = KmipCodecManager.createJsonMapper();
        xml = KmipCodecManager.createXmlMapper();
        ttlv = KmipCodecManager.createTtlvMapper();
        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);
    }

    // JSON
    public String jsonSerialize() throws Exception {
        return json.writeValueAsString(obj);
    }

    public T jsonDeserialize() throws Exception {
        return json.readValue(jsonStr, type);
    }

    // XML
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    public T xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, type);
    }

    // TTLV
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    public T ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), type);
    }
}
