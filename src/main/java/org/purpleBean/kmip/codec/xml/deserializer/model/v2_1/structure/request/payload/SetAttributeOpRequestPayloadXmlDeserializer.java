package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetAttributeOpRequestPayload;

import java.io.IOException;

public class SetAttributeOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SetAttributeOpRequestPayload, SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder> {

    public SetAttributeOpRequestPayloadXmlDeserializer() {
        super(SetAttributeOpRequestPayload.kmipTag, SetAttributeOpRequestPayload.encodingType);
    }

    @Override
    protected SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder createBuilder() {
        return SetAttributeOpRequestPayload.builder();
    }

    @Override
    protected void setValue(SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.NEW_ATTRIBUTE -> builder.newAttribute(ctxt.readValue(p, NewAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SetAttributeOpRequestPayload build(SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}