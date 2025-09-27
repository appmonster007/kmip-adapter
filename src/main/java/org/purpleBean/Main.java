package org.purpleBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.Attribute;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

public class Main {

    private static final String SEP = "================================================";

    public static void main(String[] args) throws IOException {
        printHeader("KMIP Serialization/Deserialization Demo");

        ProtocolVersion protocolVersion = ProtocolVersion.of(1, 2);
        SimpleRequestBatchItem batchItem = SimpleRequestBatchItem.builder().build();
        SimpleRequestHeader requestHeader = SimpleRequestHeader.builder()
                .protocolVersion(protocolVersion)
                .build();

        // Error list is not serialized/deserialized, so we keep it separate
        List<Exception> errorList = List.of(new Exception("Error A"), new Exception("Error B"));

        SimpleRequestMessage requestMessage = SimpleRequestMessage.builder()
                .requestHeader(requestHeader)
                .requestBatchItems(List.of(batchItem, batchItem))
                .requestBatchItemErrors(errorList)
                .build();

        KmipContext.setSpec(KmipSpec.V1_2);
        ActivationDate activationDate = ActivationDate.builder()
                .value(Instant.now().atOffset(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS))
                .build();

        State activeState = new State(State.Standard.ACTIVE);
        State customState = new State(State.register(-1341234, "Alive", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)));

        SampleStructure sampleStructure = SampleStructure.builder()
                .activationDate(activationDate)
                .state(activeState)
                .build();

        JsonMapper jsonMapper = buildJsonMapper();
        XmlMapper xmlMapper = buildXmlMapper();
        TtlvMapper ttlvMapper = buildTtlvMapper();

        CustomAttribute customAttribute = CustomAttribute.of("x-asfa", customState);
        Attribute attr = Attribute.builder()
                .attributeName(activationDate.getAttributeName())
                .attributeIndex(AttributeIndex.of(0))
                .attributeValue(activationDate.getAttributeValue()).build();

        KmipDataType[] dataTypes = {protocolVersion, customState, activationDate, sampleStructure, requestMessage, attr, customAttribute};

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

    private static void demoJson(ObjectMapper mapper, KmipDataType... dataTypes) throws JsonProcessingException {
        printSection("JSON Serialization/Deserialization");

        for (KmipDataType dataType : dataTypes) {
            if (dataType != null) {
                printJson(mapper, dataType.getKmipTag().getDescription(), dataType, (Class<KmipDataType>) dataType.getClass());
            }
        }
    }

    private static void demoXml(XmlMapper mapper, KmipDataType... dataTypes) throws JsonProcessingException {
        printSection("XML Serialization/Deserialization");

        for (KmipDataType dataType : dataTypes) {
            if (dataType != null) {
                printXml(mapper, dataType.getKmipTag().getDescription(), dataType, (Class<KmipDataType>) dataType.getClass());
            }
        }
    }

    private static void demoTtlv(TtlvMapper mapper, KmipDataType... dataTypes) throws IOException {
        printSection("TTLV Serialization/Deserialization");

        for (KmipDataType dataType : dataTypes) {
            if (dataType != null) {
                roundTripTtlv(mapper, dataType.getKmipTag().getDescription(), dataType, (Class<KmipDataType>) dataType.getClass());
            }
        }
    }

    // ================= HELPERS =================

    private static <T> void printJson(ObjectMapper mapper, String label, T obj, Class<T> type) throws JsonProcessingException {
        String serialized = mapper.writeValueAsString(obj);
        System.out.println(label + " JSON:");
        System.out.println(serialized);
        T deserialized = mapper.readValue(serialized, type);
        System.out.println("Round-trip Result:");
        System.out.println(deserialized);
        System.out.println();
    }

    private static <T> void printXml(XmlMapper mapper, String label, T obj, Class<T> type) throws JsonProcessingException {
        String serialized = mapper.writeValueAsString(obj);
        System.out.println(label + " XML:");
        System.out.println(serialized);
        T deserialized = mapper.readValue(serialized, type);
        System.out.println("Round-trip Result:");
        System.out.println(deserialized);
        System.out.println();
    }

    private static <T> void roundTripTtlv(TtlvMapper mapper, String label, T obj, Class<T> type) throws IOException {
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
