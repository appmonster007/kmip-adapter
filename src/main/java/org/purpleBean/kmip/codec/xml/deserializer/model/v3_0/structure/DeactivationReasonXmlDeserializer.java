package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.DeactivationReason;

import java.io.IOException;

public class DeactivationReasonXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DeactivationReason, DeactivationReason.DeactivationReasonBuilder> {

    public DeactivationReasonXmlDeserializer() {
        super(DeactivationReason.kmipTag, DeactivationReason.encodingType);
    }

    @Override
    protected DeactivationReason.DeactivationReasonBuilder createBuilder() {
        return DeactivationReason.builder();
    }

    @Override
    protected void setValue(DeactivationReason.DeactivationReasonBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        if (nodeTag == DeactivationReasonCode.kmipTag.getValue()) {
            builder.deactivationReasonCode(ctxt.readValue(p, DeactivationReasonCode.class));
        } else if (nodeTag == DeactivationMessage.kmipTag.getValue()) {
            builder.deactivationMessage(ctxt.readValue(p, DeactivationMessage.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeactivationReason build(DeactivationReason.DeactivationReasonBuilder builder) {
        return builder.build();
    }
}