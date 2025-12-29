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
import org.purpleBean.kmip.common.MachineIdentifier;

import java.io.IOException;

public class MachineIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<MachineIdentifier> {
    private final KmipTag kmipTag = MachineIdentifier.kmipTag;
    private final EncodingType encodingType = MachineIdentifier.encodingType;

    @Override
    public MachineIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(MachineIdentifier.class, "Expected XML object for MachineIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(MachineIdentifier.class, "Invalid Tag for MachineIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(MachineIdentifier.class, "Missing or invalid '@type' attribute for MachineIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MachineIdentifier.class,
                    "Missing or non-text 'value' for MachineIdentifier");
            return null;
        }

        String value = valueNode.asText();
        MachineIdentifier machineIdentifier = MachineIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!machineIdentifier.isSupported()) {
            ctxt.reportInputMismatch(MachineIdentifier.class, "MachineIdentifier not supported for spec " + spec);
            return null;
        }

        return machineIdentifier;
    }
}