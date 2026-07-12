package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.Pkcs11OpRequestPayload;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.enumeration.Pkcs11Function;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11InputParameters;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11OutputParameters;

public class Pkcs11OpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Pkcs11OpRequestPayload, Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder> {

    public Pkcs11OpRequestPayloadJsonDeserializer() {
        super(Pkcs11OpRequestPayload.kmipTag, Pkcs11OpRequestPayload.encodingType);
    }

    @Override
    protected Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder createBuilder() {
        return Pkcs11OpRequestPayload.builder();
    }

    @Override
    protected void setValue(Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.PKCS_11_FUNCTION -> builder.pkcs11Function(ctxt.readValue(p, Pkcs11Function.class));
            case KmipTag.Standard.PKCS_11_INPUT_PARAMETERS -> builder.pkcs11InputParameters(ctxt.readValue(p, Pkcs11InputParameters.class));
            case KmipTag.Standard.PKCS_11_OUTPUT_PARAMETERS -> builder.pkcs11OutputParameters(ctxt.readValue(p, Pkcs11OutputParameters.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Pkcs11OpRequestPayload build(Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder) {
        return builder.build();
    }
}