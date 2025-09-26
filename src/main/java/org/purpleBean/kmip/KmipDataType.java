package org.purpleBean.kmip;

import lombok.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface KmipDataType {
    // registry for mapping
    Map<RegistryKey, Class<? extends KmipAttribute>> TAG_REGISTRY = new ConcurrentHashMap<>();

    static void register(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType, Class<? extends KmipAttribute> clazz) {
        TAG_REGISTRY.put(new RegistryKey(spec, kmipTagValue, encodingType), clazz);
    }

    static Class<? extends KmipAttribute> getClassFromRegistry(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType) {
        return TAG_REGISTRY.get(new RegistryKey(spec, kmipTagValue, encodingType));
    }

    KmipTag getKmipTag();

    EncodingType getEncodingType();

    boolean isSupportedFor(@NonNull KmipSpec spec);

    record RegistryKey(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType) {
    }
}
