package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

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
import org.purpleBean.kmip.common.enumeration.ObjectClass;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ObjectClass.
 */
public class ObjectClassXmlDeserializer extends KmipDataTypeXmlDeserializer<ObjectClass> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OBJECT_CLASS);

    @Override
    public ObjectClass deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ObjectClass.class, "Expected XML element object for ObjectClass");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ObjectClass.class, "Invalid Tag for ObjectClass");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ObjectClass.class, "Missing or invalid '@type' attribute for ObjectClass");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ObjectClass.class, "Missing or non-text '@value' attribute for ObjectClass");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ObjectClass objectclass = new ObjectClass(ObjectClass.fromName(spec, description));
        if (!objectclass.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ObjectClass '%s' not supported for spec %s", description, spec));
        }

        return objectclass;
    }
}
