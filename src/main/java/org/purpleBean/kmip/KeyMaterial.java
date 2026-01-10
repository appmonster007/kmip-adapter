package org.purpleBean.kmip;

import org.purpleBean.kmip.common.enumeration.KeyFormatType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public interface KeyMaterial extends KmipDataType {
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_MATERIAL);

    // registry for mapping
    Map<KeyMaterial.RegistryKey, Class<? extends KmipDataType>> KEY_FORMAT_TYPE_REGISTRY = new ConcurrentHashMap<>();
    Map<KeyMaterial.RegistryKey, Function<KeyMaterial, ? extends KeyMaterial>> KEY_FORMAT_TYPE_BUILDER_REGISTRY = new ConcurrentHashMap<>();

    static void register(
            KmipSpec spec,
            EncodingType encodingType,
            KeyFormatType.Value keyFormatTypeValue,
            Class<? extends KmipDataType> clazz,
            Function<KeyMaterial, ? extends KeyMaterial> keyFormatTypeBuilder
    ) {
        KEY_FORMAT_TYPE_REGISTRY.put(new KeyMaterial.RegistryKey(spec, encodingType, keyFormatTypeValue), clazz);
        KEY_FORMAT_TYPE_BUILDER_REGISTRY.put(new KeyMaterial.RegistryKey(spec, encodingType, keyFormatTypeValue), keyFormatTypeBuilder);
    }

    static Class<? extends KmipDataType> getClassFromRegistry(KmipSpec spec, EncodingType encodingType, KeyFormatType.Value keyFormatTypeValue) {
        return KEY_FORMAT_TYPE_REGISTRY.get(new KeyMaterial.RegistryKey(spec, encodingType, keyFormatTypeValue));
    }

    static Function<KeyMaterial, ? extends KeyMaterial> getBuilderFromRegistry(KmipSpec spec, EncodingType encodingType, KeyFormatType.Value keyFormatTypeValue) {
        return KEY_FORMAT_TYPE_BUILDER_REGISTRY.get(new KeyMaterial.RegistryKey(spec, encodingType, keyFormatTypeValue));
    }

    record RegistryKey(KmipSpec spec, EncodingType encodingType, KeyFormatType.Value keyFormatTypeValue) {
    }
}
