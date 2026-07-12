package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.ProtectionStorageMasks;

import java.io.IOException;

public class ProtectionStorageMasksXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtectionStorageMasks, ProtectionStorageMasks.ProtectionStorageMasksBuilder> {

    public ProtectionStorageMasksXmlDeserializer() {
        super(ProtectionStorageMasks.kmipTag, ProtectionStorageMasks.encodingType);
    }

    @Override
    protected ProtectionStorageMasks.ProtectionStorageMasksBuilder createBuilder() {
        return ProtectionStorageMasks.builder();
    }

    @Override
    protected void setValue(ProtectionStorageMasks.ProtectionStorageMasksBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.PROTECTION_STORAGE_MASK -> builder.protectionStorageMask(ctxt.readValue(p, ProtectionStorageMask.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ProtectionStorageMasks build(ProtectionStorageMasks.ProtectionStorageMasksBuilder builder) {
        return builder.build();
    }
}