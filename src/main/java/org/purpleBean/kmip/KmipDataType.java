package org.purpleBean.kmip;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface KmipDataType {
    // registry for mapping
    Map<RegistryKey, Class<? extends KmipDataType>> TAG_REGISTRY = new ConcurrentHashMap<>();

    static void register(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType, Class<? extends KmipDataType> clazz) {
        TAG_REGISTRY.put(new RegistryKey(spec, kmipTagValue, encodingType), clazz);
    }

    static Class<? extends KmipDataType> getClassFromRegistry(KmipTag.Value kmipTagValue, EncodingType encodingType) {
        KmipSpec spec = KmipContext.getSpec();
        return TAG_REGISTRY.get(new RegistryKey(spec, kmipTagValue, encodingType));
    }

    KmipTag getKmipTag();

    EncodingType getEncodingType();

    boolean isSupported();

    record RegistryKey(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType) {
    }
}
