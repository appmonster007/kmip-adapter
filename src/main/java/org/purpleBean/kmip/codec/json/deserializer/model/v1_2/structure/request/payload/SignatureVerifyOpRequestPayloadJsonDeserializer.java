package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.SignatureVerifyOpRequestPayload;

import java.io.IOException;

public class SignatureVerifyOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<SignatureVerifyOpRequestPayload, SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder> {

    public SignatureVerifyOpRequestPayloadJsonDeserializer() {
        super(SignatureVerifyOpRequestPayload.kmipTag, SignatureVerifyOpRequestPayload.encodingType);
    }

    @Override
    protected SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder createBuilder() {
        return SignatureVerifyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
            case KmipTag.Standard.SIGNATURE_DATA -> builder.signatureData(ctxt.readValue(p, SignatureData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SignatureVerifyOpRequestPayload build(SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
