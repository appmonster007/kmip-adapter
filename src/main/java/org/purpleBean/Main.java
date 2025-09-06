package org.purpleBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.RequestMessageStructure;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
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

        KmipCodecContext.setSpec(KmipSpec.V1_2);
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder()
                .dateTime(Instant.now().atOffset(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS))
                .build();

        State activeState = new State(State.Standard.ACTIVE);
        State customState = new State(State.register(-1341234, "Alive", Set.of(KmipSpec.V1_2)));

        SampleStructure sampleStructure = SampleStructure.builder()
                .activationDate(activationDate)
                .state(activeState)
                .build();

        ObjectMapper jsonMapper = buildJsonMapper();
        XmlMapper xmlMapper = buildXmlMapper();
        TtlvMapper ttlvMapper = buildTtlvMapper();

        demoJson(jsonMapper, protocolVersion, customState, activationDate, sampleStructure, requestMessage);
        demoXml(xmlMapper, protocolVersion, customState, activationDate, sampleStructure, requestMessage);
        demoTtlv(ttlvMapper, protocolVersion, customState, activationDate, sampleStructure, requestMessage);

        printHeader("DONE");
    }

    private static ObjectMapper buildJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new KmipJsonModule());
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    private static XmlMapper buildXmlMapper() {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new KmipXmlModule());
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    private static TtlvMapper buildTtlvMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    // ================= DEMOS =================

    private static void demoJson(ObjectMapper mapper,
                                 ProtocolVersion protocolVersion,
                                 State customState,
                                 ActivationDateAttribute activationDate,
                                 SampleStructure sampleStructure,
                                 SimpleRequestMessage requestMessage) throws JsonProcessingException {
        printSection("JSON Serialization/Deserialization");

        printJson(mapper, "ProtocolVersion", protocolVersion, ProtocolVersion.class);
        printJson(mapper, "State (Custom)", customState, State.class);
        printJson(mapper, "ActivationDateAttribute", activationDate, ActivationDateAttribute.class);
        printJson(mapper, "SampleStructure", sampleStructure, SampleStructure.class);
        // ErrorList excluded
        printJson(mapper, "SimpleRequestMessage", requestMessage, RequestMessageStructure.class);
    }

    private static void demoXml(XmlMapper mapper,
                                ProtocolVersion protocolVersion,
                                State customState,
                                ActivationDateAttribute activationDate,
                                SampleStructure sampleStructure,
                                SimpleRequestMessage requestMessage) throws JsonProcessingException {
        printSection("XML Serialization/Deserialization");

        printXml(mapper, "ProtocolVersion", protocolVersion, ProtocolVersion.class);
        printXml(mapper, "State (Custom)", customState, State.class);
        printXml(mapper, "ActivationDateAttribute", activationDate, ActivationDateAttribute.class);
        printXml(mapper, "SampleStructure", sampleStructure, SampleStructure.class);
        // ErrorList excluded
        printXml(mapper, "SimpleRequestMessage", requestMessage, RequestMessageStructure.class);
    }

    private static void demoTtlv(TtlvMapper mapper,
                                 ProtocolVersion protocolVersion,
                                 State customState,
                                 ActivationDateAttribute activationDate,
                                 SampleStructure sampleStructure,
                                 SimpleRequestMessage requestMessage) throws IOException {
        printSection("TTLV Serialization/Deserialization");

        roundTripTtlv(mapper, "ActivationDateAttribute", activationDate, ActivationDateAttribute.class);
        roundTripTtlv(mapper, "State (Custom)", customState, State.class);
        roundTripTtlv(mapper, "SampleStructure", sampleStructure, SampleStructure.class);
        // ErrorList excluded
        roundTripTtlv(mapper, "SimpleRequestMessage", requestMessage, RequestMessageStructure.class);
    }

    // ================= HELPERS =================

    private static <T> void printJson(ObjectMapper mapper, String label, T obj, Class<T> type) throws JsonProcessingException {
        String serialized = mapper.writeValueAsString(obj);
        T deserialized = mapper.readValue(serialized, type);
        System.out.println(label + " JSON:");
        System.out.println(serialized);
        System.out.println("Round-trip Result:");
        System.out.println(deserialized);
        System.out.println();
    }

    private static <T> void printXml(XmlMapper mapper, String label, T obj, Class<T> type) throws JsonProcessingException {
        String serialized = mapper.writeValueAsString(obj);
        T deserialized = mapper.readValue(serialized, type);
        System.out.println(label + " XML:");
        System.out.println(serialized);
        System.out.println("Round-trip Result:");
        System.out.println(deserialized);
        System.out.println();
    }

    private static <T> void roundTripTtlv(TtlvMapper mapper, String label, T obj, Class<T> type) throws IOException {
        ByteBuffer buffer = mapper.writeValueAsByteBuffer(obj);
        TtlvObject ttlvObject = TtlvObject.fromBuffer(buffer);
        buffer.rewind();
        T deserialized = mapper.readValue(buffer, type);
        System.out.println(label + " TTLV:");
        System.out.println(obj);
        System.out.println("Round-trip Result:");
        System.out.println(deserialized);
        System.out.println();
        System.out.println(ttlvObject.getStructuredByteString());
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
