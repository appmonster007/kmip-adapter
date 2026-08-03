package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v2_1.type.ServerCorrelationValue;
import org.purpleBean.kmip.model.v3_0.type.ServerHashedPassword;
import org.purpleBean.kmip.model.v3_0.structure.response.ResponseHeader;

import java.io.IOException;

public class ResponseHeaderJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ResponseHeader, ResponseHeader.ResponseHeaderBuilder> {

    public ResponseHeaderJsonDeserializer() {
        super(ResponseHeader.kmipTag, ResponseHeader.encodingType);
    }

    @Override
    protected ResponseHeader.ResponseHeaderBuilder createBuilder() {
        return ResponseHeader.builder();
    }

    @Override
    protected void setValue(ResponseHeader.ResponseHeaderBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION -> builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
            case KmipTag.Standard.TIME_STAMP -> builder.timeStamp(ctxt.readValue(p, TimeStamp.class));
            case KmipTag.Standard.NONCE -> builder.nonce(ctxt.readValue(p, Nonce.class));
            case KmipTag.Standard.SERVER_HASHED_PASSWORD -> builder.serverHashedPassword(ctxt.readValue(p, ServerHashedPassword.class));
            case KmipTag.Standard.SERVER_CORRELATION_VALUE -> builder.serverCorrelationValue(ctxt.readValue(p, ServerCorrelationValue.class));
            case KmipTag.Standard.ATTESTATION_TYPE -> builder.attestationType(ctxt.readValue(p, AttestationType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ResponseHeader build(ResponseHeader.ResponseHeaderBuilder builder) {
        return builder.build();
    }
}