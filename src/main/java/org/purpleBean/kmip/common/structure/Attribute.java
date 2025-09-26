package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.common.enumeration.State;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * KMIP Attribute structure.
 */
@Data
@Builder
public class Attribute implements KmipStructure {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE);
    public static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    @NonNull
    private final AttributeName attributeName;
    private final AttributeIndex attributeIndex;
    @NonNull
    private final AttributeValue<?> attributeValue;

    public static boolean isCustomAttribute(@NonNull String name) {
        return CustomAttribute.isCustomServerAttribute(name) || CustomAttribute.isCustomClientAttribute(name);
    }

    public static Attribute of(KmipAttribute kmipAttribute) {
        return of(kmipAttribute, 0);
    }

    public static Attribute of(KmipAttribute kmipAttribute, int index) {
        String name;
        if (kmipAttribute instanceof CustomAttribute customAttribute) {
            name = customAttribute.getName();
        } else {
            name = kmipAttribute.getKmipTag().getValue().getDescription();
        }
        // convert name to Title Case from PascalCase
        name = StringUtils.covertPascalToTitleCase(name);
        return new Attribute(AttributeName.of(name), AttributeIndex.of(index), AttributeValue.of(kmipAttribute));
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }


    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(attributeName);
        values.add(attributeIndex);
        values.add(attributeValue);
        return values;
    }

    /**
     * KMIP AttributeName.
     */
    @Data
    @Builder
    public static class AttributeName implements KmipDataType {
        private static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_NAME);
        private static final EncodingType encodingType = EncodingType.TEXT_STRING;

        @NonNull
        private final String name;

        public static AttributeName of(String name) {
            return AttributeName.builder().name(name).build();
        }

        @Override
        public KmipTag getKmipTag() {
            return kmipTag;
        }

        @Override
        public EncodingType getEncodingType() {
            return encodingType;
        }

        @Override
        public boolean isSupportedFor(@NonNull KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }


    /**
     * KMIP AttributeIndex.
     */
    @Data
    @Builder
    public static class AttributeIndex implements KmipDataType {
        public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_INDEX);
        public static final EncodingType encodingType = EncodingType.INTEGER;

        private final int index;

        public static AttributeIndex of(int index) {
            return AttributeIndex.builder().index(index).build();
        }

        @Override
        public KmipTag getKmipTag() {
            return kmipTag;
        }

        @Override
        public EncodingType getEncodingType() {
            return encodingType;
        }

        @Override
        public boolean isSupportedFor(@NonNull KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    /**
     * KMIP AttributeValue structure.
     */
    @Data
    @Builder
    public static class AttributeValue<T extends KmipDataType> implements KmipDataType {

        private static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_VALUE);
        @NonNull
        private T value;

        private AttributeValue(T value) {
            this.value = value;
        }

        public static <T extends KmipDataType> AttributeValue<T> of(T value) {
            return new AttributeValue<>(value);
        }

        @Override
        public KmipTag getKmipTag() {
            return kmipTag;
        }

        @Override
        public EncodingType getEncodingType() {
            return value.getEncodingType();
        }

        @Override
        public boolean isSupportedFor(@NonNull KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder
    public static class CustomAttribute implements KmipAttribute {
        // Capability flags — adjust based on attribute semantics
        private final boolean alwaysPresent = false;
        private final boolean serverInitializable = true;
        private final boolean clientInitializable = true;
        private final boolean multiInstanceAllowed = true;

        @NonNull
        public String name;
        @NonNull
        public EncodingType encodingType;
        @NonNull
        public Object value;

        public static Attribute.CustomAttribute ofStructureString(@NonNull String name,
                                                                  @NonNull Object value
        ) throws IOException {
            if (!isCustomAttribute(name)) {
                throw new IllegalArgumentException("Invalid custom attribute name: " + name);
            }
            EncodingType encodingType;
            // TODO: Add TTLV support
//            if (KmipCodecRegistry.getDefaultType() == KmipCodecRegistry.MapperType.TTLV) {
//                encodingType = EncodingType.BYTE_STRING;
//                value = (ByteBuffer) KmipCodecRegistry.serialize(value);
//            } else {
            encodingType = EncodingType.TEXT_STRING;
            value = KmipCodecManager.serialize(value);
//            }

            return Attribute.CustomAttribute.of(name, encodingType, value);
        }

        public static Attribute.CustomAttribute of(@NonNull String name,
                                                   @NonNull EncodingType encodingType,
                                                   @NonNull Object value
        ) throws IOException {
            if (!isCustomAttribute(name)) {
                throw new IllegalArgumentException("Invalid custom attribute name: " + name);
            }
            boolean invalid = switch (encodingType) {
                case STRUCTURE -> true;
                case INTEGER, ENUMERATION, INTERVAL -> !(value instanceof Integer);
                case LONG_INTEGER -> !(value instanceof Long);
                case BIG_INTEGER -> !(value instanceof BigInteger);
                case BOOLEAN -> !(value instanceof Boolean);
                case TEXT_STRING -> !(value instanceof String);
                case BYTE_STRING -> !(value instanceof ByteBuffer);
                case DATE_TIME -> !(value instanceof OffsetDateTime);
                default -> true;
            };
            if (invalid) {
                throw new IllegalArgumentException("Invalid custom attribute value: " + value);
            }

            return new Attribute.CustomAttribute(name, encodingType, value);
        }

        public static boolean isCustomServerAttribute(@NonNull String name) {
            Pattern pattern = Pattern.compile("^y-.*?", Pattern.CASE_INSENSITIVE);
            return pattern.matcher(name).matches();
        }

        public static boolean isCustomClientAttribute(@NonNull String name) {
            Pattern pattern = Pattern.compile("^x-.*?", Pattern.CASE_INSENSITIVE);
            return pattern.matcher(name).matches();
        }

        @Override
        public KmipTag getKmipTag() {
            return null;
        }

        @Override
        public boolean isSupportedFor(@NonNull KmipSpec spec) {
            return supportedVersions.contains(spec);
        }

        @Override
        public boolean isClientModifiable(@NonNull State state) {
            return Attribute.CustomAttribute.isCustomClientAttribute(name);
        }

        @Override
        public boolean isServerModifiable(@NonNull State state) {
            return isCustomServerAttribute(name);
        }

        @Override
        public boolean isClientDeletable() {
            return Attribute.CustomAttribute.isCustomClientAttribute(name);
        }
    }

}
