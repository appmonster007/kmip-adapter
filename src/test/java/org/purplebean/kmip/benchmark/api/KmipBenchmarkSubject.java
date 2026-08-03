package org.purplebean.kmip.benchmark.api;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.nio.ByteBuffer;
import lombok.Data;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.codec.KmipCodecManager;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * SPI for adding new KMIP serialization/deserialization benchmark subjects
 * without modifying the core benchmark class.
 * <p>
 * This abstract class defines the contract for benchmark subjects. A benchmark subject
 * encapsulates a specific KMIP object and the logic to serialize and deserialize it
 * using JSON, XML, and TTLV formats. This allows the benchmarking framework to easily
 * plug in different test cases.
 *
 * @param <T> The type of the KMIP object being benchmarked.
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
   *
   * @return The name of the benchmark subject.
   */
  public abstract String name();

  /**
   * Prepare mappers and test objects.
   * <p>
   * This method sets up the KMIP context with the appropriate specification version.
   *
   * @throws Exception if setup fails.
   */
  public void setup() throws Exception {
    KmipContext.setSpec(getSpec());
  }

  /**
   * Cleanup any state after benchmark.
   * <p>
   * This method clears the KMIP context.
   */
  public void tearDown() {
    KmipContext.clear();
  }

  /**
   * Initializes the benchmark subject with the object to test and its type.
   * <p>
   * This method also initializes the codecs and pre-calculates the serialized forms
   * of the object to be used in deserialization benchmarks.
   *
   * @param obj  The object to benchmark.
   * @param type The class of the object.
   * @throws Exception if initialization fails.
   */
  public void initialize(T obj, Class<T> type) throws Exception {
    this.obj = obj;
    this.type = type;
    json = KmipCodecManager.getJsonMapper();
    xml = KmipCodecManager.getXmlMapper();
    ttlv = KmipCodecManager.getTtlvMapper();
    KmipContext.withSpec(getSpec(), () -> {
      try {
        jsonStr = jsonSerialize();
        xmlStr = xmlSerialize();
        ttlvBuf = ttlvSerialize();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      return null;
    });
  }

  // JSON

  /**
   * Serializes the object to JSON.
   *
   * @return The JSON string.
   * @throws Exception if serialization fails.
   */
  public String jsonSerialize() throws Exception {
    return json.writeValueAsString(obj);
  }

  /**
   * Deserializes the object from JSON.
   *
   * @return The deserialized object.
   * @throws Exception if deserialization fails.
   */
  public T jsonDeserialize() throws Exception {
    return json.readValue(jsonStr, type);
  }

  // XML

  /**
   * Serializes the object to XML.
   *
   * @return The XML string.
   * @throws Exception if serialization fails.
   */
  public String xmlSerialize() throws Exception {
    return xml.writeValueAsString(obj);
  }

  /**
   * Deserializes the object from XML.
   *
   * @return The deserialized object.
   * @throws Exception if deserialization fails.
   */
  public T xmlDeserialize() throws Exception {
    return xml.readValue(xmlStr, type);
  }

  // TTLV

  /**
   * Serializes the object to TTLV.
   *
   * @return The TTLV byte buffer.
   * @throws Exception if serialization fails.
   */
  public ByteBuffer ttlvSerialize() throws Exception {
    return ttlv.writeValueAsByteBuffer(obj);
  }

  /**
   * Deserializes the object from TTLV.
   *
   * @return The deserialized object.
   * @throws Exception if deserialization fails.
   */
  public T ttlvDeserialize() throws Exception {
    return ttlv.readValue(ttlvBuf.duplicate(), type);
  }
}
