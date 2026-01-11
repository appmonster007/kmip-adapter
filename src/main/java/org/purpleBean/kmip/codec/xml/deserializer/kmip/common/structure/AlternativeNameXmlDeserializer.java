package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.common.structure.AlternativeName;

import java.io.IOException;

public class AlternativeNameXmlDeserializer extends KmipDataTypeXmlDeserializer<AlternativeName> {
    private final KmipTag kmipTag = AlternativeName.kmipTag;

    @Override
    public AlternativeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AlternativeName.class, "Invalid Tag for AlternativeName");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        AlternativeName.AlternativeNameBuilder builder = AlternativeName.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(AlternativeName.class, "Unexpected token: " + p.currentToken());
            }
        }

        AlternativeName alternativename = builder.build();

        if (!alternativename.isSupported()) {
            ctxt.reportInputMismatch(AlternativeName.class, "AlternativeName not supported for spec " + spec);
            return null;
        }

        return alternativename;
    }

    private void setValue(
            AlternativeName.AlternativeNameBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.ALTERNATIVE_NAME_VALUE ->
                    builder.alternativeNameValue(ctxt.readValue(p, AlternativeNameValue.class));
            case KmipTag.Standard.ALTERNATIVE_NAME_TYPE ->
                    builder.alternativeNameType(ctxt.readValue(p, AlternativeNameType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}