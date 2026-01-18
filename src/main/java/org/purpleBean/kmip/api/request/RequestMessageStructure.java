package org.purpleBean.kmip.api.request;

import org.purpleBean.kmip.api.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Represents the top-level structure of a KMIP Request Message.
 * <p>
 * This interface defines the common components expected in any KMIP request message,
 * ensuring a consistent structure across different KMIP operations. A request message
 * typically consists of a header and one or more batch items, each representing a
 * specific KMIP operation.
 *
 * <p><b>Key Components:</b></p>
 * <ul>
 *   <li><b>Request Header:</b> Provides general information about the request, such as
 *       protocol version, batch count, and asynchronous indicators.</li>
 *   <li><b>Request Batch Items:</b> A list of individual KMIP operations to be performed.
 *       Each batch item encapsulates a single operation and its associated payload.</li>
 *   <li><b>Request Batch Item Errors:</b> A list to hold any exceptions or errors encountered
 *       during the processing of individual batch items.</li>
 * </ul>
 *
 * <p>Implementations of this interface will provide concrete representations of the
 * KMIP request message structure, facilitating serialization and deserialization
 * of KMIP requests.</p>
 *
 * @see KmipStructure
 * @see RequestHeaderStructure
 * @see RequestBatchItemStructure
 */
public interface RequestMessageStructure extends KmipStructure {

    /**
     * The standard KMIP tag for a Request message, which is always {@link KmipTag.Standard#REQUEST_MESSAGE}.
     */
    KmipTag kmipTag = KmipTag.Standard.REQUEST_MESSAGE.inst();

    Map<RegistryKey, Class<? extends RequestMessageStructure>> REGISTRY = new ConcurrentHashMap<>();
    Map<RegistryKey, BiFunction<List<KmipDataType>, List<Exception>, ? extends RequestMessageStructure>> BUILDER_REGISTRY = new ConcurrentHashMap<>();

    static void register(
            KmipSpec spec,
            Class<? extends RequestMessageStructure> clazz,
            BiFunction<List<KmipDataType>, List<Exception>, ? extends RequestMessageStructure> builder
    ) {
        REGISTRY.put(new RegistryKey(spec), clazz);
        BUILDER_REGISTRY.put(new RegistryKey(spec), builder);
    }

    static Class<? extends RequestMessageStructure> getClassFromRegistry() {
        KmipSpec spec = KmipContext.getSpec();
        return REGISTRY.get(new RegistryKey(spec));
    }

    static BiFunction<List<KmipDataType>, List<Exception>, ? extends RequestMessageStructure> getBuilderFromRegistry() {
        KmipSpec spec = KmipContext.getSpec();
        return BUILDER_REGISTRY.get(new RegistryKey(spec));
    }

    static RequestMessageStructure of(List<KmipDataType> values, List<Exception> errors) {
        return getBuilderFromRegistry().apply(values, errors);
    }

    /**
     * Retrieves the {@link RequestHeaderStructure} associated with this request message.
     * The request header contains metadata about the overall request.
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

    record RegistryKey(KmipSpec spec) {
    }
}
