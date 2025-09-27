package org.purpleBean.kmip;

import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public interface KmipAttribute extends KmipDataType {
    // registry for mapping
    Map<RegistryKey, Class<? extends KmipAttribute>> ATTRIBUTE_REGISTRY = new ConcurrentHashMap<>(); // TODO : remove attribute_registry ?
    Map<RegistryKey, Function<AttributeValue, ? extends KmipAttribute>> ATTRIBUTE_BUILDER_REGISTRY = new ConcurrentHashMap<>();

    static void register(
            KmipSpec spec,
            KmipTag.Value kmipTagValue,
            EncodingType encodingType,
            Class<? extends KmipAttribute> clazz,
            Function<AttributeValue, ? extends KmipAttribute> attributeBuilder
    ) {
        ATTRIBUTE_REGISTRY.put(new RegistryKey(spec, kmipTagValue, encodingType), clazz);
        ATTRIBUTE_BUILDER_REGISTRY.put(new RegistryKey(spec, kmipTagValue, encodingType), attributeBuilder);
    }

    static Class<? extends KmipAttribute> getClassFromRegistry(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType) {
        return ATTRIBUTE_REGISTRY.get(new RegistryKey(spec, kmipTagValue, encodingType));
    }

    static Function<AttributeValue, ? extends KmipAttribute> getAttributeBuilderFromRegistry(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType) {
        return ATTRIBUTE_BUILDER_REGISTRY.get(new RegistryKey(spec, kmipTagValue, encodingType));
    }

    boolean isAlwaysPresent();

    boolean isServerInitializable();

    boolean isClientInitializable();

    boolean isServerModifiable(State state);

    boolean isClientModifiable(State state);

    boolean isClientDeletable();

    boolean isMultiInstanceAllowed();

    AttributeValue getAttributeValue();

    AttributeName getAttributeName();

    record RegistryKey(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType) {
    }
}
