package org.purplebean.kmip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.request.RequestMessageStructure;
import org.purplebean.kmip.codec.KmipCodecManager;
import org.purplebean.kmip.codec.ttlv.TtlvObject;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.structure.Name;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestBatchItem;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestHeader;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestMessage;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purplebean.kmip.model.core.type.ActivationDate;
import org.purplebean.kmip.model.core.type.AttributeIndex;
import org.purplebean.kmip.model.core.type.CertificateValue;

/**
 * Application entry point for the KMIP adapter.
 */
public class Main {

  private static final String SEP = "================================================";

  /**
   * Application entry point.
   */
  public static void main(String[] args) throws IOException {

    printHeader("KMIP Serialization/Deserialization Demo");

    ProtocolVersion protocolVersion = ProtocolVersion.of(1, 2);
    SimpleRequestPayload payload = SimpleRequestPayload
        .builder()
        .build();
    SimpleRequestBatchItem batchItem = SimpleRequestBatchItem
        .builder()
        .requestPayloadStructure(payload)
        .build();
    SimpleRequestHeader requestHeader = SimpleRequestHeader
        .builder()
        .protocolVersion(protocolVersion)
        .build();

    // Error list is not serialized/deserialized, so we keep it separate
    List<Exception> errorList = List.of(new Exception("Error A"), new Exception("Error B"));

    SimpleRequestMessage requestMessage = SimpleRequestMessage
        .builder()
        .requestHeader(requestHeader)
        .requestBatchItems(List.of(batchItem, batchItem))
        .requestBatchItemErrors(errorList)
        .build();

    ActivationDate activationDate = ActivationDate
        .builder()
        .value(Instant
            .now()
            .atOffset(ZoneOffset.UTC)
            .truncatedTo(ChronoUnit.SECONDS))
        .build();

    State activeState = State.Standard.ACTIVE.inst();
    State customState = State
        .register(-1341234, "Alive", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2))
        .inst();

    Name name = Name.of("x-asfa", NameType.Standard.UNINTERPRETED_TEXT_STRING.inst());
    Attribute attr = Attribute
        .builder()
        .attributeName(activationDate.getAttributeName())
        .attributeIndex(AttributeIndex.of(0))
        .attributeValue(activationDate.getAttributeValue())
        .build();

    Certificate certificate = Certificate
        .builder()
        .certificateType(CertificateType.Standard.X_509.inst())
        .certificateValue(CertificateValue.of(new byte[] {1, 2, 3}))
        .build();

    KmipDataType[] dataTypes = {
        protocolVersion,
        activeState,
        customState,
        activationDate,
        name,
        requestMessage,
        // attr,
        // certificate,
    };

    JsonMapper jsonMapper = buildJsonMapper();
    XmlMapper xmlMapper = buildXmlMapper();
    TtlvMapper ttlvMapper = buildTtlvMapper();

    String strx = xmlMapper.writeValueAsString(requestMessage);
    System.out.println(strx);
    var desx = xmlMapper.readValue(strx, RequestMessageStructure.class);

    //        KmipContext.setSpec(KmipSpec.V1_2);

    demoJson(jsonMapper, dataTypes);
    demoXml(xmlMapper, dataTypes);
    demoTtlv(ttlvMapper, dataTypes);

    printHeader("DONE");
  }

  private static JsonMapper buildJsonMapper() {
    JsonMapper jsonMapper = KmipCodecManager.createJsonMapper();
    jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
    return jsonMapper;
  }

  private static XmlMapper buildXmlMapper() {
    XmlMapper xmlMapper = KmipCodecManager.createXmlMapper();
    xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    return xmlMapper;
  }

  private static TtlvMapper buildTtlvMapper() {
    TtlvMapper ttlvMapper = KmipCodecManager.createTtlvMapper();
    return ttlvMapper;
  }

  // ================= DEMOS =================

  private static void demoJson(ObjectMapper mapper, KmipDataType... dataTypes)
      throws JsonProcessingException {
    printSection("JSON Serialization/Deserialization");

    for (KmipDataType dataType : dataTypes) {
      if (dataType != null) {
        printJson(mapper, dataType
            .getKmipTag()
            .getDescription(), dataType, (Class<KmipDataType>) dataType.getClass());
      }
    }
  }

  private static void demoXml(XmlMapper mapper, KmipDataType... dataTypes)
      throws JsonProcessingException {
    printSection("XML Serialization/Deserialization");

    for (KmipDataType dataType : dataTypes) {
      if (dataType != null) {
        printXml(mapper, dataType
            .getKmipTag()
            .getDescription(), dataType, (Class<KmipDataType>) dataType.getClass());
      }
    }
  }

  private static void demoTtlv(TtlvMapper mapper, KmipDataType... dataTypes) throws IOException {
    printSection("TTLV Serialization/Deserialization");

    for (KmipDataType dataType : dataTypes) {
      if (dataType != null) {
        roundTripTtlv(mapper, dataType
            .getKmipTag()
            .getDescription(), dataType, (Class<KmipDataType>) dataType.getClass());
      }
    }
  }

  // ================= HELPERS =================

  private static <T> void printJson(ObjectMapper mapper, String label, T obj, Class<T> type)
      throws JsonProcessingException {
    String serialized = mapper.writeValueAsString(obj);
    System.out.println(label + " JSON:");
    System.out.println(serialized);
    T deserialized = mapper.readValue(serialized, type);
    System.out.println("Round-trip Result:");
    System.out.println(deserialized);
    System.out.println();
  }

  private static <T> void printXml(XmlMapper mapper, String label, T obj, Class<T> type)
      throws JsonProcessingException {
    String serialized = mapper.writeValueAsString(obj);
    System.out.println(label + " XML:");
    System.out.println(serialized);
    T deserialized = mapper.readValue(serialized, type);
    System.out.println("Round-trip Result:");
    System.out.println(deserialized);
    System.out.println();
  }

  private static <T> void roundTripTtlv(TtlvMapper mapper, String label, T obj, Class<T> type)
      throws IOException {
    ByteBuffer buffer = mapper.writeValueAsByteBuffer(obj);
    TtlvObject ttlvObject = TtlvObject.fromBuffer(buffer.duplicate());
    System.out.println(label + " TTLV:");
    System.out.println(ttlvObject.getStructuredByteString());
    T deserialized = mapper.readValue(buffer, type);
    System.out.println("Round-trip Result:");
    System.out.println(deserialized);
    System.out.println();
  }

  private static void printSection(String title) {
    System.out.println(SEP);
    System.out.println(title);
    System.out.println(SEP);
  }

  private static void printHeader(String title) {
    System.out.println("\n" + SEP);
    System.out.println(title);
    System.out.println(SEP + "\n");
  }
}
