package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.HashOpResponsePayload;

import java.io.IOException;

public class HashOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<HashOpResponsePayload, HashOpResponsePayload.HashOpResponsePayloadBuilder> {

    public HashOpResponsePayloadJsonDeserializer() {
        super(HashOpResponsePayload.kmipTag, HashOpResponsePayload.encodingType);
    }

    @Override
    protected HashOpResponsePayload.HashOpResponsePayloadBuilder createBuilder() {
        return HashOpResponsePayload.builder();
    }

    @Override
    protected void setValue(HashOpResponsePayload.HashOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.DATA)) {
            builder.data(ctxt.readValue(p, DataByteString.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected HashOpResponsePayload build(HashOpResponsePayload.HashOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
