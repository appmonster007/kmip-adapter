package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.KeyValueStructure;

import java.io.IOException;

public class KeyValueStructureXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyValueStructure> {
    private final KmipTag kmipTag = KeyValueStructure.kmipTag;

    @Override
    public KeyValueStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(KeyValueStructure.class, "Invalid Tag for KeyValueStructure");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        KeyValueStructure.KeyValueStructureBuilder builder = KeyValueStructure.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(KeyValueStructure.class, "Unexpected token: " + p.currentToken());
            }
        }

        KeyValueStructure keyValueStructure = builder.build();

        if (!keyValueStructure.isSupported()) {
            ctxt.reportInputMismatch(KeyValueStructure.class, "KeyValueStructure not supported for spec " + spec);
            return null;
        }

        return keyValueStructure;
    }

    private void setValue(KeyValueStructure.KeyValueStructureBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.KEY_MATERIAL -> builder.keyMaterial(ctxt.readValue(p, KeyMaterial.class));
            default -> builder.attribute(ctxt.readValue(p, KmipAttribute.class));
        }
    }
}