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

public class TtlvDataTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TtlvDataType, Object> {

    public TtlvDataTypeTtlvDeserializer() {
        super(null, null, null, null);
    }

    @Override
    public TtlvDataType deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject ttlvObject = TtlvObject.fromBuffer(ttlvBuffer);

        // Read Tag
        KmipTag.Value kmipTag;
        try {
            kmipTag = KmipTag.fromBytes(ttlvObject.getTag());
        } catch (NoSuchElementException e) {
            // Register Tag if unknown
            kmipTag = KmipTag.register(
                    ttlvObject.getTag(),
                    ttlvObject.getTagHexString(),
                    Stream.of(KmipSpec.UnknownVersion, KmipContext.getSpec()).collect(Collectors.toSet())
            );
        }

        // Read Type
        EncodingType encodingType = EncodingType.fromTypeValue(ttlvObject.getType()).orElseThrow(
                () -> new IllegalArgumentException("Unknown encoding type: " + ttlvObject.getType())
        );

        // Read Value based on EncodingType
        Object value = readValue(ByteBuffer.wrap(ttlvObject.getValue()), encodingType, kmipTag, mapper);

        return TtlvDataType.builder()
                .kmipTag(kmipTag.inst())
                .encodingType(encodingType)
                .value(value)
                .build();
    }

    private Object readValue(ByteBuffer buffer, EncodingType encodingType, KmipTag.Value kmipTag, TtlvMapper mapper) throws IOException {
        return switch (encodingType) {
            case INTEGER -> mapper.readValue(buffer, Integer.class);
            case LONG_INTEGER -> mapper.readValue(buffer, Long.class);
            case BIG_INTEGER -> mapper.readValue(buffer, BigInteger.class);
            case BOOLEAN -> mapper.readValue(buffer, Boolean.class);
            case TEXT_STRING -> mapper.readValue(buffer, String.class);
            case BYTE_STRING -> mapper.readValue(buffer, ByteBuffer.class);
            case DATE_TIME -> mapper.readValue(buffer, OffsetDateTime.class);
            case INTERVAL -> mapper.readValue(buffer, Integer.class);
            case ENUMERATION ->
                    mapper.readValue(buffer, KmipDataType.getClassFromRegistry(kmipTag, EncodingType.ENUMERATION));
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
}
