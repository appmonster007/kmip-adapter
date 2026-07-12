package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ImportOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.type.ReplaceExisting;

public class ImportOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ImportOpRequestPayload, ImportOpRequestPayload.ImportOpRequestPayloadBuilder> {

    public ImportOpRequestPayloadTtlvDeserializer() {
        super(ImportOpRequestPayload.kmipTag, ImportOpRequestPayload.encodingType);
    }

    @Override
    protected ImportOpRequestPayload.ImportOpRequestPayloadBuilder createBuilder() {
        return ImportOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ImportOpRequestPayload.ImportOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.REPLACE_EXISTING -> builder.replaceExisting(mapper.readValue(p, ReplaceExisting.class));
            case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION -> builder.keyWrappingSpecification(mapper.readValue(p, KeyWrappingSpecification.class));
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
    protected ImportOpRequestPayload build(ImportOpRequestPayload.ImportOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}