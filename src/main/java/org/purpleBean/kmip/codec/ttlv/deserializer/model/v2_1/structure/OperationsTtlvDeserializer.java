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
import org.purpleBean.kmip.model.v2_1.structure.Operations;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OperationsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Operations, Operations.OperationsBuilder> {

    public OperationsTtlvDeserializer() {
        super(Operations.kmipTag, Operations.encodingType);
    }

    @Override
    protected Operations.OperationsBuilder createBuilder() {
        return Operations.builder();
    }

    @Override
    protected void setValue(Operations.OperationsBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OPERATION -> builder.operation(mapper.readValue(p, Operation.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Operations build(Operations.OperationsBuilder builder) {
        return builder.build();
    }
}