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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TtlvDataTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TtlvDataType, TtlvDataType.TtlvDataTypeBuilder> {

    private final Stack<KmipTag.Value> kmipTagStack = new Stack<>();
    private final Stack<EncodingType> encodingTypeStack = new Stack<>();
    private final Stack<Object> valueStack = new Stack<>();

    public TtlvDataTypeTtlvDeserializer() {
        super(null, null);
    }

    @Override
    protected TtlvDataType.TtlvDataTypeBuilder createBuilder() {
        return TtlvDataType.builder();
    }

    @Override
    protected void setValue(TtlvDataType.TtlvDataTypeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        EncodingType encodingType = encodingTypeStack.peek();
        switch (encodingType) {
            case INTEGER -> valueStack.push(mapper.readValue(p, Integer.class));
            case LONG_INTEGER -> valueStack.push(mapper.readValue(p, Long.class));
            case BIG_INTEGER -> valueStack.push(mapper.readValue(p, BigInteger.class));
            case BOOLEAN -> valueStack.push(mapper.readValue(p, Boolean.class));
            case TEXT_STRING -> valueStack.push(mapper.readValue(p, String.class));
            case BYTE_STRING -> valueStack.push(mapper.readValue(p, ByteBuffer.class));
            case DATE_TIME -> valueStack.push(mapper.readValue(p, OffsetDateTime.class));
            case INTERVAL -> valueStack.push(mapper.readValue(p, Integer.class));
            case ENUMERATION -> {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
                var factory = KmipEnumeration.getFromValue(nodeTag);
                Integer value = mapper.readValue(p, Integer.class);
                if (factory == null) {
                    throw new IllegalArgumentException(String.format("Invalid value [%d] for enumeration tag %s", value, nodeTag.getDescription()));
                }
                valueStack.push(factory.apply(value));
            }
            case STRUCTURE -> {
                if (valueStack.peek() instanceof List<?>) {
                    List<KmipDataType> valueList = (List<KmipDataType>) valueStack.peek();
                    valueList.add(mapper.readValue(p, TtlvDataType.class));
                }
            }
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        }
    }

    @Override
    protected TtlvDataType build(TtlvDataType.TtlvDataTypeBuilder builder) {
        KmipTag.Value nodeTag = kmipTagStack.pop();
        EncodingType encodingType = encodingTypeStack.pop();
        Object value = valueStack.pop();
        if (encodingType == EncodingType.STRUCTURE && value instanceof List<?> valueList) {
            builder.value(valueList.toArray(KmipDataType[]::new));
        } else {
            builder.value(value);
        }
        return builder.build();
    }

    @Override
    protected byte[] verifyTag(TtlvObject obj, TtlvMapper mapper, TtlvDataType.TtlvDataTypeBuilder builder) {
        byte[] tag = obj.getTag();
        KmipTag.Value nodeTag;
        try {
            nodeTag = KmipTag.fromBytes(tag);
        } catch (NoSuchElementException e) {
            nodeTag = KmipTag.register(
                    tag,
                    null,
                    Stream.of(KmipSpec.UnknownVersion, KmipContext.getSpec()).collect(Collectors.toSet())
            );
        }
        kmipTagStack.push(nodeTag);
        builder.kmipTag(nodeTag.inst());

        return obj.getTag();
    }

    @Override
    protected byte verifyType(TtlvObject obj, TtlvMapper mapper, TtlvDataType.TtlvDataTypeBuilder builder) {
        byte type = obj.getType();
        EncodingType encodingType = EncodingType.fromTypeValue(type).orElseThrow(
                () -> new IllegalArgumentException("Unknown encoding type: " + HexFormat.of().toHexDigits(type))
        );

        encodingTypeStack.push(encodingType);
        if (encodingType == EncodingType.STRUCTURE) {
            valueStack.push(new ArrayList<KmipDataType>());
        }
        builder.encodingType(encodingType);
        return obj.getType();
    }
}
