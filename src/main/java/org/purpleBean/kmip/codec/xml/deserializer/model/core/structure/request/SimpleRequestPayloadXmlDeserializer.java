package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;

import java.io.IOException;

public class SimpleRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SimpleRequestPayload, SimpleRequestPayload.SimpleRequestPayloadBuilder> {

    public SimpleRequestPayloadXmlDeserializer() {
        super(SimpleRequestPayload.kmipTag);
    }

    @Override
    protected SimpleRequestPayload.SimpleRequestPayloadBuilder createBuilder() {
        return SimpleRequestPayload.builder();
    }

    @Override
    protected void setValue(SimpleRequestPayload.SimpleRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields to set
    }

    @Override
    protected SimpleRequestPayload build(SimpleRequestPayload.SimpleRequestPayloadBuilder builder) {
        return builder.build();
    }
}