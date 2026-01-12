package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.common.structure.AlternativeName;

import java.io.IOException;

public class AlternativeNameJsonDeserializer extends AbstractKmipStructureJsonDeserializer<AlternativeName, AlternativeName.AlternativeNameBuilder> {

    public AlternativeNameJsonDeserializer() {
        super(AlternativeName.kmipTag, AlternativeName.encodingType);
    }

    @Override
    protected AlternativeName.AlternativeNameBuilder createBuilder() {
        return AlternativeName.builder();
    }

    @Override
    protected void setValue(AlternativeName.AlternativeNameBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ALTERNATIVE_NAME_TYPE ->
                    builder.alternativeNameType(ctxt.readValue(p, AlternativeNameType.class));
            case KmipTag.Standard.ALTERNATIVE_NAME_VALUE ->
                    builder.alternativeNameValue(ctxt.readValue(p, AlternativeNameValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected AlternativeName build(AlternativeName.AlternativeNameBuilder builder) {
        return builder.build();
    }
}