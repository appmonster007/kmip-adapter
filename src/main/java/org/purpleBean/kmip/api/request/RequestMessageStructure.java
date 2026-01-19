package org.purpleBean.kmip.api.request;

import org.purpleBean.kmip.api.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Represents the top-level structure of a KMIP (Key Management Interoperability Protocol) Request Message.
 * <p>
 * This interface defines the standard layout for any KMIP request message. A request message acts as a
 * container, holding a header and one or more "batch items," where each batch item represents a single
 * KMIP operation (e.g., Create, Get, Destroy).
 *
 * <p><b>Structure:</b></p>
 * <p>A Request Message is a {@link KmipStructure} composed of:</p>
 * <ul>
 *   <li><b>Request Header:</b> (Required, 1) A {@link RequestHeaderStructure} containing metadata for the
 *       entire request, such as the protocol version and batch count.</li>
 *   <li><b>Batch Item:</b> (Required, 1 or more) A {@link RequestBatchItemStructure} for each operation
 *       included in the request.</li>
 * </ul>
 *
 * <p><b>Dynamic Registration:</b></p>
 * <p>This interface includes a static registration mechanism to support different message structures across
 * various KMIP specification versions. Implementations for specific versions register themselves, allowing
 * the codec to dynamically instantiate the correct message class based on the active {@link KmipContext}.</p>
 *
 * @see KmipStructure
 * @see RequestHeaderStructure
 * @see RequestBatchItemStructure
 */
public interface RequestMessageStructure extends KmipStructure {

    /**
     * The standard KMIP tag for a Request Message.
     */
    KmipTag kmipTag = KmipTag.Standard.REQUEST_MESSAGE.inst();

    /**
     * A registry mapping a {@link RegistryKey} (containing a {@link KmipSpec}) to the specific
     * {@link RequestMessageStructure} class implementation for that specification version.
     */
    Map<RegistryKey, Class<? extends RequestMessageStructure>> REGISTRY = new ConcurrentHashMap<>();

    /**
     * A registry mapping a {@link RegistryKey} to a builder function that can construct a specific
     * {@link RequestMessageStructure} instance from its constituent parts.
     */
    Map<RegistryKey, BiFunction<List<KmipDataType>, List<Exception>, ? extends RequestMessageStructure>> BUILDER_REGISTRY = new ConcurrentHashMap<>();

    /**
     * Registers a {@link RequestMessageStructure} implementation and its builder for a specific KMIP version.
     *
     * @param spec    The {@link KmipSpec} version for which this implementation is valid.
     * @param clazz   The {@link Class} that implements the request message for the specified version.
     * @param builder A {@link BiFunction} that constructs an instance of the class.
     */
    static void register(
            KmipSpec spec,
            Class<? extends RequestMessageStructure> clazz,
            BiFunction<List<KmipDataType>, List<Exception>, ? extends RequestMessageStructure> builder
    ) {
        REGISTRY.put(new RegistryKey(spec), clazz);
        BUILDER_REGISTRY.put(new RegistryKey(spec), builder);
    }

    /**
     * Retrieves the appropriate {@link RequestMessageStructure} class from the registry based on the
     * currently active {@link KmipContext}.
     *
     * @return The registered {@link Class} for the active KMIP specification, or {@code null} if none is found.
     */
    static Class<? extends RequestMessageStructure> getClassFromRegistry() {
        KmipSpec spec = KmipContext.getSpec();
        return REGISTRY.get(new RegistryKey(spec));
    }

    /**
     * Retrieves the appropriate builder function from the registry based on the currently active {@link KmipContext}.
     *
     * @return The registered {@link BiFunction} builder for the active KMIP specification, or {@code null} if none is found.
     */
    static BiFunction<List<KmipDataType>, List<Exception>, ? extends RequestMessageStructure> getBuilderFromRegistry() {
        KmipSpec spec = KmipContext.getSpec();
        return BUILDER_REGISTRY.get(new RegistryKey(spec));
    }

    /**
     * A factory method that constructs a {@link RequestMessageStructure} instance using the builder registered
     * for the currently active {@link KmipContext}.
     *
     * @param values The list of {@link KmipDataType} values that constitute the message.
     * @param errors A list of exceptions encountered during parsing, which may be relevant for constructing the object.
     * @return A new instance of a {@link RequestMessageStructure} implementation.
     */
    static RequestMessageStructure of(List<KmipDataType> values, List<Exception> errors) {
        return getBuilderFromRegistry().apply(values, errors);
    }

    /**
     * Retrieves the {@link RequestHeaderStructure} associated with this request message.
     *
     * @return The {@link RequestHeaderStructure} of this request.
     */
    RequestHeaderStructure getRequestHeader();

    /**
     * Retrieves a list of {@link RequestBatchItemStructure} objects, each representing
     * an individual KMIP operation within this request message.
     *
     * @return A list of {@link RequestBatchItemStructure} instances.
     */
    List<? extends RequestBatchItemStructure> getRequestBatchItems();

    /**
     * Retrieves a list of exceptions or errors that occurred during the processing
     * of individual {@link RequestBatchItemStructure} objects within this request.
     * This list is typically populated during deserialization or validation.
     *
     * @return A list of {@link Exception} instances related to batch item processing.
     */
    List<? extends Exception> getRequestBatchItemErrors();

    /**
     * A composite key for the registries, uniquely identifying an implementation by its KMIP specification version.
     *
     * @param spec The KMIP specification version.
     */
    record RegistryKey(KmipSpec spec) {
    }
}
