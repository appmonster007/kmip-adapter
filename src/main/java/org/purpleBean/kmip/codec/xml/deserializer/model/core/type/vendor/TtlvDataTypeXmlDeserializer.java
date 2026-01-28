package org.purpleBean.kmip.codec.xml.deserializer.model.core.type.vendor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Set;

public class TtlvDataTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TtlvDataType, Object> {

    public TtlvDataTypeXmlDeserializer() {
        super(null, null, Object.class, null);
    }

    @Override
    public TtlvDataType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        JsonNode node = p.getCodec().readTree(p);

        KmipTag.Value kmipTag;
        if (currentName.startsWith("TTLV")) {
            String tag = node.get("tag").asText();
            int tagValue = Integer.decode(tag);
            kmipTag = KmipTag.register(tagValue, tag, Set.of(KmipContext.getSpec()));
        } else {
            kmipTag = KmipTag.fromName(currentName);
        }


        String type = node.get("type").asText();

        EncodingType encodingType = EncodingType.fromName(type).orElseThrow(
                () -> new IllegalArgumentException("Unsupported encoding type: " + type)
        );

        JsonNode valueNode = node.get("value");

        Object value = parseValue(valueNode, encodingType, kmipTag, ctxt);

        return TtlvDataType.builder()
                .kmipTag(kmipTag.inst())
                .encodingType(encodingType)
                .value(value)
                .build();
    }

    private Object parseValue(JsonNode valueNode, EncodingType encodingType, KmipTag.Value kmipTag, DeserializationContext ctxt) throws IOException {
        return switch (encodingType) {
            case INTEGER -> ctxt.readTreeAsValue(valueNode, Integer.class);
            case LONG_INTEGER -> ctxt.readTreeAsValue(valueNode, Long.class);
            case BIG_INTEGER -> ctxt.readTreeAsValue(valueNode, BigInteger.class);
            case BOOLEAN -> ctxt.readTreeAsValue(valueNode, Boolean.class);
            case TEXT_STRING -> ctxt.readTreeAsValue(valueNode, String.class);
            case BYTE_STRING -> ctxt.readTreeAsValue(valueNode, ByteBuffer.class);
            case DATE_TIME -> ctxt.readTreeAsValue(valueNode, OffsetDateTime.class);
            case INTERVAL -> ctxt.readTreeAsValue(valueNode, Integer.class);
            case ENUMERATION ->
                    ctxt.readTreeAsValue(valueNode, KmipDataType.getClassFromRegistry(kmipTag, EncodingType.ENUMERATION));
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        };
    }
}
