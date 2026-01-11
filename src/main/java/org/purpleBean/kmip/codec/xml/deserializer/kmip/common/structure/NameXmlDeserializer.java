package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.common.structure.Name;

import java.io.IOException;

public class NameXmlDeserializer extends KmipDataTypeXmlDeserializer<Name> {
    private final KmipTag kmipTag = Name.kmipTag;
    private final EncodingType encodingType = Name.encodingType;

    @Override
    public Name deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(Name.class, "Invalid Tag for Name");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        Name.NameBuilder builder = Name.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);

            if (p.currentToken() == JsonToken.FIELD_NAME) {
                p.nextToken(); // Move to the value token
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(Name.class, "Unexpected token: " + p.currentToken());
            }
        }

        Name name = builder.build();

        if (!name.isSupported()) {
            ctxt.reportInputMismatch(Name.class, "Name not supported for spec " + spec);
            return null;
        }

        return name;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            Name.NameBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.NAME_VALUE -> builder.nameValue(ctxt.readValue(p, NameValue.class));
            case KmipTag.Standard.NAME_TYPE -> builder.nameType(ctxt.readValue(p, NameType.class));
            default -> throw new IllegalArgumentException("Unsupported tag in Name structure: " + nodeTag);
        }
    }
}
