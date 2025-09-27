package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class DestroyDateXmlDeserializer extends KmipDataTypeXmlDeserializer<DestroyDate> {
    private final KmipTag kmipTag = DestroyDate.kmipTag;
    private final EncodingType encodingType = DestroyDate.encodingType;

    @Override
    public DestroyDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DestroyDate.class, "Expected XML object for DestroyDate");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DestroyDate.class, "Invalid Tag for DestroyDate");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DestroyDate.class, "Missing or invalid '@type' attribute for DestroyDate");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DestroyDate.class,
                    "Missing or non-text 'value' for DestroyDate");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        DestroyDate destroyDate = DestroyDate.builder().value(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!destroyDate.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(DestroyDate.class, "DestroyDate not supported for spec " + spec);
            return null;
        }

        return destroyDate;
    }
}
