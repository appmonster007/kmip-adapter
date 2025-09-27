package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.KmipCodecManager;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Set;

/**
 * KMIP AttributeValue dataType.
 */
@Data
@Builder
public class AttributeValue implements KmipDataType {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_VALUE);
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    @NonNull
    private EncodingType encodingType;
    @NonNull
    private Object value;

    private AttributeValue(@NonNull EncodingType encodingType, @NonNull Object value) {
        if (encodingType == EncodingType.STRUCTURE
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

    public static AttributeValue of(@NonNull Object value) {
        EncodingType encodingType;
        encodingType = switch (value) {
            case Integer i -> EncodingType.INTEGER;
            case Long l -> EncodingType.LONG_INTEGER;
            case Boolean b -> EncodingType.BOOLEAN;
            case String s -> EncodingType.TEXT_STRING;
            case OffsetDateTime odt -> EncodingType.DATE_TIME;
            case BigInteger bi -> EncodingType.BIG_INTEGER;
            case ByteBuffer bb -> EncodingType.BYTE_STRING;
            default -> {
                try {
                    value = KmipCodecManager.serialize(value);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Unsupported encoding type: " + value);
                }
                yield EncodingType.TEXT_STRING;
            }
        };
        return AttributeValue.builder().encodingType(encodingType).value(value).build();
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }
}
