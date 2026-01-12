package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.common.structure.Name;

import java.io.IOException;

public class NameJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Name, Name.NameBuilder> {

    public NameJsonDeserializer() {
        super(Name.kmipTag, Name.encodingType);
    }

    @Override
    protected Name.NameBuilder createBuilder() {
        return Name.builder();
    }

    @Override
    protected void setValue(Name.NameBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.NAME_VALUE -> builder.nameValue(ctxt.readValue(p, NameValue.class));
            case KmipTag.Standard.NAME_TYPE -> builder.nameType(ctxt.readValue(p, NameType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Name build(Name.NameBuilder builder) {
        return builder.build();
    }
}