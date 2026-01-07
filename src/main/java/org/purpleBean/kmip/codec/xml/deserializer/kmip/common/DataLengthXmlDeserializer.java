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
import org.purpleBean.kmip.common.DataLength;

import java.io.IOException;

public class DataLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<DataLength> {
    private final KmipTag kmipTag = DataLength.kmipTag;
    private final EncodingType encodingType = DataLength.encodingType;

    @Override
    public DataLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DataLength.class, "Expected XML object for DataLength");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DataLength.class, "Invalid Tag for DataLength");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DataLength.class, "Missing or invalid '@type' attribute for DataLength");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DataLength.class,
                    "Missing or non-numeric 'value' for DataLength");
            return null;
        }

        Integer value = Integer.valueOf(valueNode.asText());
        DataLength dataLength = DataLength.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!dataLength.isSupported()) {
            ctxt.reportInputMismatch(DataLength.class, "DataLength not supported for spec " + spec);
            return null;
        }

        return dataLength;
    }
}
