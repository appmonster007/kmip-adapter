package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RngRetrieveOpResponsePayload;

import java.io.IOException;

public class RngRetrieveOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<RngRetrieveOpResponsePayload, RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder> {

    public RngRetrieveOpResponsePayloadXmlDeserializer() {
        super(RngRetrieveOpResponsePayload.kmipTag);
    }

    @Override
    protected RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder createBuilder() {
        return RngRetrieveOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.DATA)) {
            builder.data(ctxt.readValue(p, DataByteString.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RngRetrieveOpResponsePayload build(RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
