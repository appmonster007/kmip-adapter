package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;

import java.io.IOException;

public class SimpleResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SimpleResponsePayload, SimpleResponsePayload.SimpleResponsePayloadBuilder> {

    public SimpleResponsePayloadXmlDeserializer() {
        super(SimpleResponsePayload.kmipTag);
    }

    @Override
    protected SimpleResponsePayload.SimpleResponsePayloadBuilder createBuilder() {
        return SimpleResponsePayload.builder();
    }

    @Override
    protected void setValue(SimpleResponsePayload.SimpleResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields to deserialize
    }

    @Override
    protected SimpleResponsePayload build(SimpleResponsePayload.SimpleResponsePayloadBuilder builder) {
        return builder.build();
    }
}
