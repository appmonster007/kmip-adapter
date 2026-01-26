package org.purpleBean.kmip.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public interface KmipMaskType extends KmipDataType {

    Map<RegistryKey, Function<String, ? extends KmipMaskType>> FROM_MASK_STRING_REGISTRY = new ConcurrentHashMap<>();

    static void register(
            KmipSpec spec,
            KmipTag.Value kmipTagValue,
            Function<String, ? extends KmipMaskType> fromMaskString
    ) {
        FROM_MASK_STRING_REGISTRY.put(new RegistryKey(spec, kmipTagValue), fromMaskString);
    }

    static Function<String, ? extends KmipMaskType> getFromMaskString(KmipTag.Value kmipTagValue) {
        KmipSpec spec = KmipContext.getSpec();
        return FROM_MASK_STRING_REGISTRY.get(new RegistryKey(spec, kmipTagValue));
    }

    String getMaskString();

    Integer getValue();

    record RegistryKey(KmipSpec spec, KmipTag.Value kmipTagValue) {
    }
}
