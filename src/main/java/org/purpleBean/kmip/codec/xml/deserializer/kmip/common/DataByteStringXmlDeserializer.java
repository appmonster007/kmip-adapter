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
import org.purpleBean.kmip.common.DataByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DataByteStringXmlDeserializer extends KmipDataTypeXmlDeserializer<DataByteString> {
    private final KmipTag kmipTag = DataByteString.kmipTag;
    private final EncodingType encodingType = DataByteString.encodingType;

    @Override
    public DataByteString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DataByteString.class, "Expected XML object for DataByteString");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DataByteString.class, "Invalid Tag for DataByteString");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DataByteString.class, "Missing or invalid '@type' attribute for DataByteString");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DataByteString.class,
                    "Missing or non-text 'value' for DataByteString");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        DataByteString dataByteString = DataByteString.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!dataByteString.isSupported()) {
            ctxt.reportInputMismatch(DataByteString.class, "DataByteString not supported for spec " + spec);
            return null;
        }

        return dataByteString;
    }
}
