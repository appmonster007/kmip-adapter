package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.AttributeReference;

import java.io.IOException;

public class AttributeReferenceJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeReference, AttributeReference.AttributeReferenceBuilder> {

    public AttributeReferenceJsonDeserializer() {
        super(AttributeReference.kmipTag, AttributeReference.encodingType);
    }

    @Override
    protected AttributeReference.AttributeReferenceBuilder createBuilder() {
        return AttributeReference.builder();
    }

    @Override
    protected void setValue(AttributeReference.AttributeReferenceBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.VENDOR_IDENTIFICATION -> builder.vendorIdentification(ctxt.readValue(p, VendorIdentification.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(ctxt.readValue(p, AttributeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected AttributeReference build(AttributeReference.AttributeReferenceBuilder builder) {
        return builder.build();
    }
}