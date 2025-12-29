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
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SubjectDistinguishedNameXmlDeserializer extends KmipDataTypeXmlDeserializer<SubjectDistinguishedName> {
    private final KmipTag kmipTag = SubjectDistinguishedName.kmipTag;
    private final EncodingType encodingType = SubjectDistinguishedName.encodingType;

    @Override
    public SubjectDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, "Expected XML object for SubjectDistinguishedName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, "Invalid Tag for SubjectDistinguishedName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, "Missing or invalid '@type' attribute for SubjectDistinguishedName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class,
                    "Missing or non-text 'value' for SubjectDistinguishedName");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        SubjectDistinguishedName subjectDistinguishedName = SubjectDistinguishedName.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!subjectDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, "SubjectDistinguishedName not supported for spec " + spec);
            return null;
        }

        return subjectDistinguishedName;
    }
}