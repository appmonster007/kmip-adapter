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
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SubjectAlternativeNameXmlDeserializer extends KmipDataTypeXmlDeserializer<SubjectAlternativeName> {
    private final KmipTag kmipTag = SubjectAlternativeName.kmipTag;
    private final EncodingType encodingType = SubjectAlternativeName.encodingType;

    @Override
    public SubjectAlternativeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, "Expected XML object for SubjectAlternativeName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, "Invalid Tag for SubjectAlternativeName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, "Missing or invalid '@type' attribute for SubjectAlternativeName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class,
                    "Missing or non-text 'value' for SubjectAlternativeName");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        SubjectAlternativeName subjectAlternativeName = SubjectAlternativeName.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!subjectAlternativeName.isSupported()) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, "SubjectAlternativeName not supported for spec " + spec);
            return null;
        }

        return subjectAlternativeName;
    }
}