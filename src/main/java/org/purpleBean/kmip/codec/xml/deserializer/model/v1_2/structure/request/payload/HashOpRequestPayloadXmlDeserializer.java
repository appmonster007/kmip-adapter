package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.HashOpRequestPayload;

import java.io.IOException;

public class HashOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<HashOpRequestPayload, HashOpRequestPayload.HashOpRequestPayloadBuilder> {

    public HashOpRequestPayloadXmlDeserializer() {
        super(HashOpRequestPayload.kmipTag);
    }

    @Override
    protected HashOpRequestPayload.HashOpRequestPayloadBuilder createBuilder() {
        return HashOpRequestPayload.builder();
    }

    @Override
    protected void setValue(HashOpRequestPayload.HashOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected HashOpRequestPayload build(HashOpRequestPayload.HashOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
