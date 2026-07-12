package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.AttributeReference;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeReferenceTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeReference, AttributeReference.AttributeReferenceBuilder> {

    public AttributeReferenceTtlvDeserializer() {
        super(AttributeReference.kmipTag, AttributeReference.encodingType);
    }

    @Override
    protected AttributeReference.AttributeReferenceBuilder createBuilder() {
        return AttributeReference.builder();
    }

    @Override
    protected void setValue(AttributeReference.AttributeReferenceBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.VENDOR_IDENTIFICATION -> builder.vendorIdentification(mapper.readValue(p, VendorIdentification.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(mapper.readValue(p, AttributeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected AttributeReference build(AttributeReference.AttributeReferenceBuilder builder) {
        return builder.build();
    }
}