package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;

import java.io.IOException;

public class SplitKeyPartsXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SplitKeyParts, SplitKeyParts.SplitKeyPartsBuilder> {

    public SplitKeyPartsXmlDeserializer() {
        super(SplitKeyParts.kmipTag, SplitKeyParts.encodingType);
    }

    @Override
    protected SplitKeyParts.SplitKeyPartsBuilder createBuilder() {
        return SplitKeyParts.builder();
    }

    @Override
    protected void setValue(SplitKeyParts.SplitKeyPartsBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected SplitKeyParts build(SplitKeyParts.SplitKeyPartsBuilder builder) {
        return builder.build();
    }
}