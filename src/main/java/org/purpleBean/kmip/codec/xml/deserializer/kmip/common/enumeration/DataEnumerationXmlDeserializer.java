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
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for DataEnumeration.
 */
public class DataEnumerationXmlDeserializer extends KmipDataTypeXmlDeserializer<DataEnumeration> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.DATA);

    @Override
    public DataEnumeration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DataEnumeration.class, "Expected XML element object for DataEnumeration");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DataEnumeration.class, "Invalid Tag for DataEnumeration");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DataEnumeration.class, "Missing or invalid '@type' attribute for DataEnumeration");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DataEnumeration.class, "Missing or non-text '@value' attribute for DataEnumeration");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        DataEnumeration dataenumeration = new DataEnumeration(DataEnumeration.fromName(spec, description));
        if (!dataenumeration.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("DataEnumeration '%s' not supported for spec %s", description, spec));
        }

        return dataenumeration;
    }
}
