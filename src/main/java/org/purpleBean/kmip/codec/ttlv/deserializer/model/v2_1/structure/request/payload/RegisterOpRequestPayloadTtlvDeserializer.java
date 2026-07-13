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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.RegisterOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RegisterOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RegisterOpRequestPayload, RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder> {

    public RegisterOpRequestPayloadTtlvDeserializer() {
        super(RegisterOpRequestPayload.kmipTag, RegisterOpRequestPayload.encodingType);
    }

    @Override
    protected RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder createBuilder() {
        return RegisterOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> {
                ObjectType objectType = mapper.readValue(p, ObjectType.class);
                mapper.setAttribute("objectType", objectType.getDescription());
                builder.objectType(objectType);
            }
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
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
    protected RegisterOpRequestPayload build(RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}