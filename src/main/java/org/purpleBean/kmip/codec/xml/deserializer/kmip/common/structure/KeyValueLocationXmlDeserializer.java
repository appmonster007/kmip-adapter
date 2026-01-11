package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.structure.KeyValueLocation;

import java.io.IOException;

public class KeyValueLocationXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyValueLocation> {
    private final KmipTag kmipTag = KeyValueLocation.kmipTag;

    @Override
    public KeyValueLocation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(KeyValueLocation.class, "Invalid Tag for KeyValueLocation");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        KeyValueLocation.KeyValueLocationBuilder builder = KeyValueLocation.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(KeyValueLocation.class, "Unexpected token: " + p.currentToken());
            }
        }

        KeyValueLocation keyvaluelocation = builder.build();

        if (!keyvaluelocation.isSupported()) {
            ctxt.reportInputMismatch(KeyValueLocation.class, "KeyValueLocation not supported for spec " + spec);
            return null;
        }

        return keyvaluelocation;
    }

    private void setValue(
            KeyValueLocation.KeyValueLocationBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.KEY_VALUE_LOCATION_TYPE ->
                    builder.keyValueLocationType(ctxt.readValue(p, KeyValueLocationType.class));
            case KmipTag.Standard.KEY_VALUE_LOCATION_VALUE ->
                    builder.keyValueLocationValue(ctxt.readValue(p, KeyValueLocationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}