package org.purpleBean.kmip.api.request;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents a single batch item within a KMIP (Key Management Interoperability Protocol) Request Message.
 * <p>
 * A batch item is a container that encapsulates a single KMIP operation and its corresponding payload.
 * A {@link RequestMessageStructure} can contain one or more batch items, allowing multiple operations
 * to be sent to a KMIP server in a single request.
 *
 * <p><b>Structure:</b></p>
 * <p>A Batch Item is a {@link KmipStructure} that typically contains:</p>
 * <ul>
 *   <li><b>Operation:</b> (Required) An {@link Operation} enumeration value that specifies the action
 *       to be performed (e.g., Create, Get, Destroy).</li>
 *   <li><b>Unique Batch Item ID:</b> (Optional) A unique identifier for this specific batch item within
 *       the request, used for correlation in asynchronous responses.</li>
 *   <li><b>Request Payload:</b> (Required) A {@link RequestPayloadStructure} containing the parameters
 *       and attributes for the specified operation.</li>
 * </ul>
 *
 * <p><b>Dynamic Registration:</b></p>
 * <p>This interface includes a static registration mechanism to support different batch item structures across
 * various KMIP specification versions. Implementations for specific versions register themselves, allowing
 * the codec to dynamically instantiate the correct class based on the active {@link KmipContext}.</p>
 *
 * @see KmipStructure
 * @see RequestMessageStructure
 * @see RequestPayloadStructure
 * @see Operation
 */
public interface RequestBatchItemStructure extends KmipStructure {
    /**
     * The standard KMIP tag for a Batch Item.
     */
    KmipTag kmipTag = KmipTag.Standard.BATCH_ITEM.inst();

    /**
     * A registry mapping a {@link RegistryKey} (containing a {@link KmipSpec}) to the specific
     * {@link RequestBatchItemStructure} class implementation for that specification version.
     */
    Map<RegistryKey, Class<? extends RequestBatchItemStructure>> REGISTRY = new ConcurrentHashMap<>();

    /**
     * A registry mapping a {@link RegistryKey} to a builder function that can construct a specific
     * {@link RequestBatchItemStructure} instance from a list of its constituent {@link KmipDataType} values.
     */
    Map<RegistryKey, Function<List<KmipDataType>, ? extends RequestBatchItemStructure>> BUILDER_REGISTRY = new ConcurrentHashMap<>();

    /**
     * Registers a {@link RequestBatchItemStructure} implementation and its builder for a specific KMIP version.
     *
     * @param spec    The {@link KmipSpec} version for which this implementation is valid.
     * @param clazz   The {@link Class} that implements the batch item for the specified version.
     * @param builder A {@link Function} that constructs an instance of the class from a list of values.
     */
    static void register(
            KmipSpec spec,
            Class<? extends RequestBatchItemStructure> clazz,
            Function<List<KmipDataType>, ? extends RequestBatchItemStructure> builder
    ) {
        REGISTRY.put(new RegistryKey(spec), clazz);
        BUILDER_REGISTRY.put(new RegistryKey(spec), builder);
    }

    /**
     * Retrieves the appropriate {@link RequestBatchItemStructure} class from the registry based on the
     * currently active {@link KmipContext}.
     *
     * @return The registered {@link Class} for the active KMIP specification, or {@code null} if none is found.
     */
    static Class<? extends RequestBatchItemStructure> getClassFromRegistry() {
        KmipSpec spec = KmipContext.getSpec();
        return REGISTRY.get(new RegistryKey(spec));
    }

    /**
     * Retrieves the appropriate builder function from the registry based on the currently active {@link KmipContext}.
     *
     * @return The registered {@link Function} builder for the active KMIP specification, or {@code null} if none is found.
     */
    static Function<List<KmipDataType>, ? extends RequestBatchItemStructure> getBuilderFromRegistry() {
        KmipSpec spec = KmipContext.getSpec();
        return BUILDER_REGISTRY.get(new RegistryKey(spec));
    }

    /**
     * A factory method that constructs a {@link RequestBatchItemStructure} instance using the builder registered
     * for the currently active {@link KmipContext}.
     *
     * @param values The list of {@link KmipDataType} values that constitute the batch item.
     * @return A new instance of a {@link RequestBatchItemStructure} implementation.
     */
    static RequestBatchItemStructure of(List<KmipDataType> values) {
        return getBuilderFromRegistry().apply(values);
    }

    /**
     * Retrieves the {@link Operation} specified in this batch item.
     *
     * @return The {@link Operation} to be performed.
     */
    Operation getOperation();

    /**
     * Retrieves the {@link RequestPayloadStructure} for this batch item.
     *
     * @return The {@link RequestPayloadStructure} containing the parameters for the operation.
     */
    RequestPayloadStructure getRequestPayload();

    /**
     * A composite key for the registries, uniquely identifying an implementation by its KMIP specification version.
     *
     * @param spec The KMIP specification version.
     */
    record RegistryKey(KmipSpec spec) {
    }
}
