package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.KeyMaterialStructure;

import java.io.IOException;

public class KeyMaterialStructureXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyMaterialStructure> {
    private final KmipTag kmipTag = KeyMaterialStructure.kmipTag;

    @Override
    public KeyMaterialStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(KeyMaterialStructure.class, "Invalid Tag for KeyMaterialStructure");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        KeyMaterialStructure.KeyMaterialStructureBuilder builder = KeyMaterialStructure.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(KeyMaterialStructure.class, "Unexpected token: " + p.currentToken());
            }
        }

        KeyMaterialStructure keyMaterialStructure = builder.build();

        if (!keyMaterialStructure.isSupported()) {
            ctxt.reportInputMismatch(KeyMaterialStructure.class, "KeyMaterialStructure not supported for spec " + spec);
            return null;
        }

        return keyMaterialStructure;
    }

    private void setValue(
            KeyMaterialStructure.KeyMaterialStructureBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        builder.value(ctxt.readValue(p, KmipDataType.class));
    }
}
