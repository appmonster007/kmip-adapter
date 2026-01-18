package org.purpleBean.kmip.api.request;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * The {@code RequestPayloadStructure} interface represents the payload of a KMIP request.
 * It defines methods for registering and retrieving request payload classes and builders based on KMIP specification and operation.
 */
public interface RequestPayloadStructure extends KmipDataType {

    KmipTag kmipTag = KmipTag.Standard.REQUEST_PAYLOAD.inst();
    Map<RegistryKey, Class<? extends RequestPayloadStructure>> PAYLOAD_REGISTRY = new ConcurrentHashMap<>();
    Map<RegistryKey, Function<List<KmipDataType>, ? extends RequestPayloadStructure>> PAYLOAD_BUILDER_REGISTRY = new ConcurrentHashMap<>();

    static void register(
            /**
             * Registers a request payload class and its corresponding builder.
             *
             * @param spec The KMIP specification.
             * @param operationValue The KMIP operation value.
             * @param clazz The class representing the request payload.
             * @param payloadBuilder The function to build the request payload.
             */
            KmipSpec spec,
            Operation.Value operationValue,
            Class<? extends RequestPayloadStructure> clazz,
            Function<List<KmipDataType>, ? extends RequestPayloadStructure> payloadBuilder
    ) {
        PAYLOAD_REGISTRY.put(new RegistryKey(spec, operationValue), clazz);
        PAYLOAD_BUILDER_REGISTRY.put(new RegistryKey(spec, operationValue), payloadBuilder);
    }

    /**
     * Retrieves the request payload class from the registry based on the current KMIP specification and operation value.
     *
     * @param operationValue The KMIP operation value.
     * @return The class representing the request payload, or {@code null} if not found.
     */
    static Class<? extends RequestPayloadStructure> getClassFromRegistry(Operation.Value operationValue) {
        KmipSpec spec = KmipContext.getSpec();
        return PAYLOAD_REGISTRY.get(new RegistryKey(spec, operationValue));
    }

    /**
     * Retrieves the request payload builder function from the registry based on the current KMIP specification and operation value.
     *
     * @param operationValue The KMIP operation value.
     * @return The function to build the request payload, or {@code null} if not found.
     */
    static Function<List<KmipDataType>, ? extends RequestPayloadStructure> getBuilderFromRegistry(Operation.Value operationValue) {
        KmipSpec spec = KmipContext.getSpec();
        return PAYLOAD_BUILDER_REGISTRY.get(new RegistryKey(spec, operationValue));
    }

    /**
     * A record representing a key for the registry, composed of a KMIP specification and an operation value.
     *
     * @param spec           The KMIP specification.
     * @param operationValue The KMIP operation value.
     */
    record RegistryKey(KmipSpec spec, Operation.Value operationValue) {
    }
}