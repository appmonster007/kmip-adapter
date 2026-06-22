package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetAttributeOpResponsePayload;

import java.io.IOException;

public class SetAttributeOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SetAttributeOpResponsePayload, SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder> {

    public SetAttributeOpResponsePayloadXmlDeserializer() {
        super(SetAttributeOpResponsePayload.kmipTag, SetAttributeOpResponsePayload.encodingType);
    }

    @Override
    protected SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder createBuilder() {
        return SetAttributeOpResponsePayload.builder();
    }

    @Override
    protected void setValue(SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SetAttributeOpResponsePayload build(SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}