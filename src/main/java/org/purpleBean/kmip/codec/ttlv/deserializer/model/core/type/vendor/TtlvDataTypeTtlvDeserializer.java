package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type.vendor;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TtlvDataTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TtlvDataType, TtlvDataType.TtlvDataTypeBuilder> {

    public TtlvDataTypeTtlvDeserializer() {
        super(null, null);
    }

    @Override
    protected TtlvDataType.TtlvDataTypeBuilder createBuilder() {
        return TtlvDataType.builder();
    }

    @Override
    protected void setValue(TtlvDataType.TtlvDataTypeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag;
        try {
            nodeTag = KmipTag.fromBytes(tagBytes);
        } catch (NoSuchElementException e) {
            // Register Tag if unknown
            nodeTag = KmipTag.register(
                    tagBytes,
                    null,
                    Stream.of(KmipSpec.UnknownVersion, KmipContext.getSpec()).collect(Collectors.toSet())
            );
        }
        EncodingType encodingType = EncodingType.fromTypeValue(type).orElseThrow(
                () -> new IllegalArgumentException("Unknown encoding type: " + type)
        );
        builder.kmipTag(nodeTag.inst()).encodingType(encodingType).value(readValue(nodeTag, encodingType, p, mapper));
    }

    @Override
    protected TtlvDataType build(TtlvDataType.TtlvDataTypeBuilder builder) {
        return builder.build();
    }

    private Object readValue(KmipTag.Value kmipTag, EncodingType encodingType, ByteBuffer buffer, TtlvMapper mapper) throws IOException {
        return switch (encodingType) {
            case INTEGER -> mapper.readValue(buffer, Integer.class);
            case LONG_INTEGER -> mapper.readValue(buffer, Long.class);
            case BIG_INTEGER -> mapper.readValue(buffer, BigInteger.class);
            case BOOLEAN -> mapper.readValue(buffer, Boolean.class);
            case TEXT_STRING -> mapper.readValue(buffer, String.class);
            case BYTE_STRING -> mapper.readValue(buffer, ByteBuffer.class);
            case DATE_TIME -> mapper.readValue(buffer, OffsetDateTime.class);
            case INTERVAL -> mapper.readValue(buffer, Integer.class);
            case ENUMERATION -> {
                Class<?> clazz = KmipDataType.getClassFromRegistry(kmipTag, EncodingType.ENUMERATION);
                yield mapper.readValue(buffer, clazz);
            }
            case STRUCTURE -> {
                List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(buffer.array());
                List<KmipDataType> values = new ArrayList<>();
                for (TtlvObject ttlvObject : nestedObjects) {
                    values.add(mapper.readValue(ttlvObject.toByteBuffer(), TtlvDataType.class));
                }
                yield values;
            }
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        };
    }

    @Override
    protected byte[] verifyTag(TtlvObject obj, TtlvMapper mapper) {
        return obj.getTag();
    }

    @Override
    protected byte verifyType(TtlvObject obj, TtlvMapper mapper) {
        return obj.getType();
    }
}
