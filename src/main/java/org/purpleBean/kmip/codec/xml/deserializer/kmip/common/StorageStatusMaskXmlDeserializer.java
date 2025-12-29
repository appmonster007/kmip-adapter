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
import org.purpleBean.kmip.common.StorageStatusMask;

import java.io.IOException;

public class StorageStatusMaskXmlDeserializer extends KmipDataTypeXmlDeserializer<StorageStatusMask> {
    private final KmipTag kmipTag = StorageStatusMask.kmipTag;
    private final EncodingType encodingType = StorageStatusMask.encodingType;

    @Override
    public StorageStatusMask deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(StorageStatusMask.class, "Expected XML object for StorageStatusMask");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(StorageStatusMask.class, "Invalid Tag for StorageStatusMask");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(StorageStatusMask.class, "Missing or invalid '@type' attribute for StorageStatusMask");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(StorageStatusMask.class,
                    "Missing or non-number 'value' for StorageStatusMask");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        StorageStatusMask storageStatusMask = StorageStatusMask.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!storageStatusMask.isSupported()) {
            ctxt.reportInputMismatch(StorageStatusMask.class, "StorageStatusMask not supported for spec " + spec);
            return null;
        }

        return storageStatusMask;
    }
}