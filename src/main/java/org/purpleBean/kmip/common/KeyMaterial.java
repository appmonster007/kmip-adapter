package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * KMIP KeyMaterial dataType.
 */
public abstract class KeyMaterial {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_MATERIAL);

    public interface Value extends KmipDataType {
        // registry for mapping
        Map<RegistryKey, Class<? extends KmipDataType>> KEY_FORMAT_TYPE_REGISTRY = new ConcurrentHashMap<>();
        Map<RegistryKey, Function<KeyMaterial.Value, ? extends KeyMaterial.Value>> KEY_FORMAT_TYPE_BUILDER_REGISTRY = new ConcurrentHashMap<>();

        static void register(
                KmipSpec spec,
                EncodingType encodingType,
                KeyFormatType.Value keyFormatTypeValue,
                Class<? extends KmipDataType> clazz,
                Function<Value, ? extends Value> keyFormatTypeBuilder
        ) {
            KEY_FORMAT_TYPE_REGISTRY.put(new RegistryKey(spec, encodingType, keyFormatTypeValue), clazz);
            KEY_FORMAT_TYPE_BUILDER_REGISTRY.put(new RegistryKey(spec, encodingType, keyFormatTypeValue), keyFormatTypeBuilder);
        }

        static Class<? extends KmipDataType> getClassFromRegistry(KmipSpec spec, EncodingType encodingType, KeyFormatType.Value keyFormatTypeValue) {
            return KEY_FORMAT_TYPE_REGISTRY.get(new RegistryKey(spec, encodingType, keyFormatTypeValue));
        }

        static Function<KeyMaterial.Value, ? extends KeyMaterial.Value> getBuilderFromRegistry(KmipSpec spec, EncodingType encodingType, KeyFormatType.Value keyFormatTypeValue) {
            return KEY_FORMAT_TYPE_BUILDER_REGISTRY.get(new RegistryKey(spec, encodingType, keyFormatTypeValue));
        }

        record RegistryKey(KmipSpec spec, EncodingType encodingType, KeyFormatType.Value keyFormatTypeValue) {
        }
    }

    public interface Structure extends Value, KmipStructure {
        KmipTag kmipTag = KeyMaterial.kmipTag;
        EncodingType encodingType = EncodingType.STRUCTURE;
    }

    @Data
    @Builder(toBuilder = true)
    public static class ByteString implements Value {

        public static final KmipTag kmipTag = KeyMaterial.kmipTag;
        public static final EncodingType encodingType = EncodingType.BYTE_STRING;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            ArrayList<KeyFormatType.Value> byteStringKeyFormatTypes = new ArrayList<>(Arrays.asList(
                    KeyFormatType.Standard.RAW, KeyFormatType.Standard.OPAQUE,
                    KeyFormatType.Standard.PKCS_1, KeyFormatType.Standard.PKCS_8,
                    KeyFormatType.Standard.EC_PRIVATE_KEY
            ));
            byteStringKeyFormatTypes.addAll(KeyFormatType.registeredValues());

            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, ByteString.class);
                for (KeyFormatType.Value keyFormatType : byteStringKeyFormatTypes) {
                    KeyMaterial.Value.register(spec, encodingType, keyFormatType, ByteString.class, ByteString::of);
                }
            }
        }

        @NonNull
        private final ByteBuffer value;

        public static ByteString of(@NonNull KeyMaterial.Value value) {
            if (!(value instanceof KeyMaterial.ByteString byteString)) {
                throw new IllegalArgumentException("Invalid key material: " + value);
            }
            return byteString;
        }

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
}
