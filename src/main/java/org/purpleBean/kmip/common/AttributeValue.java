package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.KmipCodecManager;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

/**
 * KMIP AttributeValue dataType.
 */
@Data
@Builder
public class AttributeValue implements KmipStructure, KmipDataType {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_VALUE);
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            for (EncodingType encodingType : EncodingType.values()) {
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeValue.class);
            }
        }
    }

    @NonNull
    private EncodingType encodingType;
    @NonNull
    private Object value;

    private AttributeValue(@NonNull EncodingType encodingType, @NonNull Object value) {
        if (encodingType == EncodingType.STRUCTURE && !(isValidStructureAttributeValue(value))
                || encodingType == EncodingType.INTEGER && !(value instanceof Integer)
                || encodingType == EncodingType.LONG_INTEGER && !(value instanceof Long)
                || encodingType == EncodingType.BIG_INTEGER && !(value instanceof BigInteger)
                || encodingType == EncodingType.ENUMERATION && !(value instanceof Integer)
                || encodingType == EncodingType.BOOLEAN && !(value instanceof Boolean)
                || encodingType == EncodingType.TEXT_STRING && !(value instanceof String)
                || encodingType == EncodingType.BYTE_STRING && !(value instanceof ByteBuffer)
                || encodingType == EncodingType.DATE_TIME && !(value instanceof OffsetDateTime)
                || encodingType == EncodingType.INTERVAL && !(value instanceof Integer)
        ) {
            throw new IllegalArgumentException("Invalid attribute value: " + value);
        }
        this.encodingType = encodingType;
        this.value = value;
    }

    public static boolean isValidStructureAttributeValue(@NonNull Object value) {
        if (value instanceof List<?> values) {
            return values.stream().allMatch(v -> v instanceof KmipDataType);
        }
        return false;
    }

    public static AttributeValue of(@NonNull EncodingType encodingType, @NonNull Object value) {
        return AttributeValue.builder().encodingType(encodingType).value(value).build();
    }

    public static AttributeValue of(@NonNull KmipDataType... values) {
        return AttributeValue.of(EncodingType.STRUCTURE, List.of(values));
    }

    public static AttributeValue of(@NonNull Object value) {
        return switch (value) {
            case KmipDataType av -> AttributeValue.of(EncodingType.STRUCTURE, List.of(av));
            case Integer i -> AttributeValue.of(EncodingType.INTEGER, i);
            case Long l -> AttributeValue.of(EncodingType.LONG_INTEGER, l);
            case Boolean b -> AttributeValue.of(EncodingType.BOOLEAN, b);
            case String s -> AttributeValue.of(EncodingType.TEXT_STRING, s);
            case OffsetDateTime odt -> AttributeValue.of(EncodingType.DATE_TIME, odt);
            case BigInteger bi -> AttributeValue.of(EncodingType.BIG_INTEGER, bi);
            case ByteBuffer bb -> AttributeValue.of(EncodingType.BYTE_STRING, bb);
            case List<?> list when isValidStructureAttributeValue(list) ->
                    AttributeValue.of(EncodingType.STRUCTURE, list);
            default -> {
                try {
                    value = KmipCodecManager.serialize(value);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Unsupported encoding type: " + value);
                }

                if (value instanceof String str) {
                    yield AttributeValue.of(EncodingType.TEXT_STRING, str);
                } else if (value instanceof ByteBuffer bb) {
                    yield AttributeValue.of(EncodingType.BYTE_STRING, bb);
                } else {
                    throw new IllegalArgumentException("Unsupported encoded value type: " + value);
                }
            }
        };
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec);
    }

    @SuppressWarnings("unchecked")
    public List<KmipDataType> getValues() {
        if (encodingType == EncodingType.STRUCTURE && !(isValidStructureAttributeValue(value))) {
            throw new IllegalArgumentException("Invalid encoding type");
        }
        return (List<KmipDataType>) value;
    }
}
