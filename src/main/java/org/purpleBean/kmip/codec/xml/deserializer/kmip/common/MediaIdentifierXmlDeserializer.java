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
import org.purpleBean.kmip.common.MediaIdentifier;

import java.io.IOException;

public class MediaIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<MediaIdentifier> {
    private final KmipTag kmipTag = MediaIdentifier.kmipTag;
    private final EncodingType encodingType = MediaIdentifier.encodingType;

    @Override
    public MediaIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(MediaIdentifier.class, "Expected XML object for MediaIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(MediaIdentifier.class, "Invalid Tag for MediaIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(MediaIdentifier.class, "Missing or invalid '@type' attribute for MediaIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MediaIdentifier.class,
                    "Missing or non-text 'value' for MediaIdentifier");
            return null;
        }

        String value = valueNode.asText();
        MediaIdentifier mediaIdentifier = MediaIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!mediaIdentifier.isSupported()) {
            ctxt.reportInputMismatch(MediaIdentifier.class, "MediaIdentifier not supported for spec " + spec);
            return null;
        }

        return mediaIdentifier;
    }
}