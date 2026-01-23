package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.SignOpRequestPayload;

import java.io.IOException;

public class SignOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<SignOpRequestPayload, SignOpRequestPayload.SignOpRequestPayloadBuilder> {

    public SignOpRequestPayloadJsonDeserializer() {
        super(SignOpRequestPayload.kmipTag, SignOpRequestPayload.encodingType);
    }

    @Override
    protected SignOpRequestPayload.SignOpRequestPayloadBuilder createBuilder() {
        return SignOpRequestPayload.builder();
    }

    @Override
    protected void setValue(SignOpRequestPayload.SignOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SignOpRequestPayload build(SignOpRequestPayload.SignOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
