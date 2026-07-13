package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DecryptOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionAdditionalData;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionTag;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

import java.io.IOException;

public class DecryptOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DecryptOpRequestPayload, DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder> {

    public DecryptOpRequestPayloadXmlDeserializer() {
        super(DecryptOpRequestPayload.kmipTag, DecryptOpRequestPayload.encodingType);
    }

    @Override
    protected DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder createBuilder() {
        return DecryptOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS -> builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
            case KmipTag.Standard.IV_COUNTER_NONCE -> builder.ivCounterNonce(ctxt.readValue(p, IVCounterNonce.class));
            case KmipTag.Standard.CORRELATION_VALUE -> builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
            case KmipTag.Standard.INIT_INDICATOR -> builder.initIndicator(ctxt.readValue(p, InitIndicator.class));
            case KmipTag.Standard.FINAL_INDICATOR -> builder.finalIndicator(ctxt.readValue(p, FinalIndicator.class));
            case KmipTag.Standard.AUTHENTICATED_ENCRYPTION_ADDITIONAL_DATA -> builder.authenticatedEncryptionAdditionalData(ctxt.readValue(p, AuthenticatedEncryptionAdditionalData.class));
            case KmipTag.Standard.AUTHENTICATED_ENCRYPTION_TAG -> builder.authenticatedEncryptionTag(ctxt.readValue(p, AuthenticatedEncryptionTag.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DecryptOpRequestPayload build(DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
