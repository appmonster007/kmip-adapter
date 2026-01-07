package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP AttributeValue dataType.
 */
public abstract class AttributeValue {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_VALUE);

    public interface Value extends KmipDataType {
    }

    @Data
    @Builder(toBuilder = true)
    public static class Integer implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.INTEGER;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, Integer.class);
            }
        }

        @NonNull
        private final java.lang.Integer value;

        public static Integer of(@NonNull java.lang.Integer value) {
            return Integer.builder().value(value).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class LongInteger implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.LONG_INTEGER;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, LongInteger.class);
            }
        }

        @NonNull
        private final java.lang.Long value;

        public static LongInteger of(@NonNull java.lang.Long value) {
            return LongInteger.builder().value(value).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class BigInteger implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.BIG_INTEGER;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, BigInteger.class);
            }
        }

        @NonNull
        private final java.math.BigInteger value;

        public static BigInteger of(@NonNull java.math.BigInteger value) {
            return BigInteger.builder().value(value).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class Enumeration implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.ENUMERATION;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, Enumeration.class);
            }
        }

        @NonNull
        private final java.lang.Integer value;

        public static Enumeration of(@NonNull java.lang.Integer value) {
            return Enumeration.builder().value(value).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class Boolean implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.BOOLEAN;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, Boolean.class);
            }
        }

        @NonNull
        private final java.lang.Boolean value;

        public static Boolean of(@NonNull java.lang.Boolean value) {
            return Boolean.builder().value(value).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class TextString implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.TEXT_STRING;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, TextString.class);
            }
        }

        @NonNull
        private final java.lang.String value;

        public static TextString of(@NonNull java.lang.String value) {
            return TextString.builder().value(value).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class ByteString implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.BYTE_STRING;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, ByteString.class);
            }
        }

        @NonNull
        private final ByteBuffer value;

        public static ByteString of(@NonNull ByteBuffer value) {
            return ByteString.builder().value(value).build();
        }

        public static ByteString of(byte[] value) {
            return ByteString.builder().value(ByteBuffer.wrap(value)).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class DateTime implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.DATE_TIME;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, DateTime.class);
            }
        }

        @NonNull
        private final OffsetDateTime value;

        public static DateTime of(@NonNull OffsetDateTime value) {
            return DateTime.builder().value(value).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DateTime that = (DateTime) o;
            // Compare OffsetDateTime up to seconds to avoid flakiness
            return this.value.withNano(0).equals(that.value.withNano(0));
        }

        @Override
        public int hashCode() {
            return Objects.hash(value.withNano(0));
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class Interval implements Value {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.INTERVAL;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, Interval.class);
            }
        }

        @NonNull
        private final java.lang.Integer value;

        public static Interval of(@NonNull java.lang.Integer value) {
            return Interval.builder().value(value).build();
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class Structure implements Value, KmipStructure {
        public static final KmipTag kmipTag = AttributeValue.kmipTag;
        public static final EncodingType encodingType = EncodingType.STRUCTURE;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, Structure.class);
            }
        }

        @NonNull
        private final List<KmipDataType> value;

        public static Structure of(@NonNull List<KmipDataType> value) {
            return Structure.builder().value(value).build();
        }

        public static Structure of(@NonNull KmipDataType... values) {
            return of(List.of(values));
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec)
                    && getValues().stream().allMatch(KmipDataType::isSupported);
        }

        @Override
        public List<KmipDataType> getValues() {
            return value.stream().filter(Objects::nonNull).toList();
        }
    }
}
