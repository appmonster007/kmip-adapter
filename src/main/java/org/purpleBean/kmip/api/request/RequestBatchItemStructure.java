package org.purpleBean.kmip.api.request;

import org.purpleBean.kmip.api.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents a single batch item within a KMIP Request Message.
 * <p>
 * This interface serves as a marker for all structures that can be included as a
 * batch item in a KMIP request. A batch item encapsulates a single KMIP operation
 * (e.g., Create, Get, Destroy) and its associated payload.
 *
 * <p>A {@link RequestMessageStructure} contains a list of these batch items, allowing
 * multiple operations to be sent in a single request to the KMIP server.</p>
 *
 * <p>Implementations of this interface will define the specific structure and content
 * required for a particular KMIP operation.</p>
 *
 * @see KmipStructure
 * @see RequestMessageStructure
 */
public interface RequestBatchItemStructure extends KmipStructure {
    /**
     * The standard KMIP tag for a Batch Item, which is always {@link KmipTag.Standard#BATCH_ITEM}.
     */
    KmipTag kmipTag = KmipTag.Standard.BATCH_ITEM.inst();

    Map<RegistryKey, Class<? extends RequestBatchItemStructure>> REGISTRY = new ConcurrentHashMap<>();
    Map<RegistryKey, Function<List<KmipDataType>, ? extends RequestBatchItemStructure>> BUILDER_REGISTRY = new ConcurrentHashMap<>();

    static void register(
            KmipSpec spec,
            EncodingType encodingType,
            Class<? extends RequestBatchItemStructure> clazz,
            Function<List<KmipDataType>, ? extends RequestBatchItemStructure> builder
    ) {
        REGISTRY.put(new RegistryKey(spec, encodingType), clazz);
        BUILDER_REGISTRY.put(new RegistryKey(spec, encodingType), builder);
    }

    static Class<? extends RequestBatchItemStructure> getClassFromRegistry(KmipSpec spec, EncodingType encodingType) {
        return REGISTRY.get(new RegistryKey(spec, encodingType));
    }

    static Function<List<KmipDataType>, ? extends RequestBatchItemStructure> getBuilderFromRegistry(KmipSpec spec, EncodingType encodingType) {
        return BUILDER_REGISTRY.get(new RegistryKey(spec, encodingType));
    }

    static RequestBatchItemStructure of(List<KmipDataType> values) {
        KmipSpec spec = KmipContext.getSpec();
        return getBuilderFromRegistry(spec, encodingType).apply(values);
    }

    record RegistryKey(KmipSpec spec, EncodingType encodingType) {
    }
}
