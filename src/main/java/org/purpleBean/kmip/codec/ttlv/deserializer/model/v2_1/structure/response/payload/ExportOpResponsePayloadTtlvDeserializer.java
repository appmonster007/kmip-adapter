package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ExportOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class ExportOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ExportOpResponsePayload, ExportOpResponsePayload.ExportOpResponsePayloadBuilder> {

    public ExportOpResponsePayloadTtlvDeserializer() {
        super(ExportOpResponsePayload.kmipTag, ExportOpResponsePayload.encodingType);
    }

    @Override
    protected ExportOpResponsePayload.ExportOpResponsePayloadBuilder createBuilder() {
        return ExportOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ExportOpResponsePayload.ExportOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> {
                if (ManagedObject.isManagedObject(nodeTag)) {
                    builder.object(mapper.readValue(p, ManagedObject.class));
                } else {
                    throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
                }
            }
        }
    }

    @Override
    protected ExportOpResponsePayload build(ExportOpResponsePayload.ExportOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}