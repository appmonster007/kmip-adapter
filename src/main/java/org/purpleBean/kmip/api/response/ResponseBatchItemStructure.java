package org.purpleBean.kmip.api.response;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.type.ResultMessage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents a single batch item within a KMIP (Key Management Interoperability Protocol) Response Message.
 * <p>
 * A batch item is a container that encapsulates the result of a single KMIP operation.
 * A {@link ResponseMessageStructure} can contain one or more batch items, allowing multiple operation results
 * to be sent from a KMIP server in a single response.
 *
 * <p><b>Structure:</b></p>
 * <p>A Batch Item is a {@link KmipStructure} that typically contains:</p>
 * <ul>
 *   <li><b>Operation:</b> (Required) An {@link Operation} enumeration value that specifies the action
 *       that was performed.</li>
 *   <li><b>Unique Batch Item ID:</b> (Optional) A unique identifier for this specific batch item,
 *       used for correlation with the request.</li>
 *   <li><b>Result Status:</b> (Required) A {@link ResultStatus} enumeration value indicating the outcome
 *       of the operation (e.g., Success, Failure).</li>
 *   <li><b>Result Reason:</b> (Conditional) An enumeration providing more detail if the operation failed.</li>
 *   <li><b>Result Message:</b> (Conditional) A string providing a human-readable message if the operation failed.</li>
 *   <li><b>Response Payload:</b> (Conditional) A {@link ResponsePayloadStructure} containing the data returned
 *       by the operation (present on success).</li>
 * </ul>
 *
 * <p><b>Dynamic Registration:</b></p>
 * <p>This interface includes a static registration mechanism to support different batch item structures across
 * various KMIP specification versions. Implementations for specific versions register themselves, allowing
 * the codec to dynamically instantiate the correct class based on the active {@link KmipContext}.</p>
 *
 * @see KmipStructure
 * @see ResponseMessageStructure
 * @see ResponsePayloadStructure
 * @see Operation
 */
public interface ResponseBatchItemStructure extends KmipStructure {
    /**
     * The standard KMIP tag for a Batch Item.
     */
    KmipTag kmipTag = KmipTag.Standard.BATCH_ITEM.inst();

    /**
     * A registry mapping a {@link RegistryKey} (containing a {@link KmipSpec}) to the specific
     * {@link ResponseBatchItemStructure} class implementation for that specification version.
     */
    Map<RegistryKey, Class<? extends ResponseBatchItemStructure>> REGISTRY = new ConcurrentHashMap<>();

    /**
     * A registry mapping a {@link RegistryKey} to a builder function that can construct a specific
     * {@link ResponseBatchItemStructure} instance from a list of its constituent {@link KmipDataType} values.
     */
    Map<RegistryKey, Function<List<KmipDataType>, ? extends ResponseBatchItemStructure>> BUILDER_REGISTRY = new ConcurrentHashMap<>();

    /**
     * Registers a {@link ResponseBatchItemStructure} implementation and its builder for a specific KMIP version.
     *
     * @param spec    The {@link KmipSpec} version for which this implementation is valid.
     * @param clazz   The {@link Class} that implements the batch item for the specified version.
     * @param builder A {@link Function} that constructs an instance of the class from a list of values.
     */
    static void register(
            KmipSpec spec,
            Class<? extends ResponseBatchItemStructure> clazz,
            Function<List<KmipDataType>, ? extends ResponseBatchItemStructure> builder
    ) {
        REGISTRY.put(new RegistryKey(spec), clazz);
        BUILDER_REGISTRY.put(new RegistryKey(spec), builder);
    }

    /**
     * Retrieves the appropriate {@link ResponseBatchItemStructure} class from the registry based on the
     * currently active {@link KmipContext}.
     *
     * @return The registered {@link Class} for the active KMIP specification, or {@code null} if none is found.
     */
    static Class<? extends ResponseBatchItemStructure> getClassFromRegistry() {
        KmipSpec spec = KmipContext.getSpec();
        return REGISTRY.get(new RegistryKey(spec));
    }

    /**
     * Retrieves the appropriate builder function from the registry based on the currently active {@link KmipContext}.
     *
     * @return The registered {@link Function} builder for the active KMIP specification, or {@code null} if none is found.
     */
    static Function<List<KmipDataType>, ? extends ResponseBatchItemStructure> getBuilderFromRegistry() {
        KmipSpec spec = KmipContext.getSpec();
        return BUILDER_REGISTRY.get(new RegistryKey(spec));
    }

    /**
     * A factory method that constructs a {@link ResponseBatchItemStructure} instance using the builder registered
     * for the currently active {@link KmipContext}.
     *
     * @param values The list of {@link KmipDataType} values that constitute the batch item.
     * @return A new instance of a {@link ResponseBatchItemStructure} implementation.
     */
    static ResponseBatchItemStructure of(List<KmipDataType> values) {
        return getBuilderFromRegistry().apply(values);
    }

    /**
     * Retrieves the {@link Operation} specified in this batch item.
     *
     * @return The {@link Operation} that was performed.
     */
    Operation getOperation();

    /**
     * Retrieves the {@link ResultStatus} of the operation.
     *
     * @return The {@link ResultStatus} indicating the outcome.
     */
    ResultStatus getResultStatus();

    /**
     * Retrieves the {@link ResultMessage} containing more information about the result, especially in case of failure.
     *
     * @return The {@link ResultMessage}, or {@code null} if not present.
     */
    ResultMessage getResultMessage();

    /**
     * Retrieves the {@link ResultReason} providing more detail if the operation failed.
     *
     * @return The {@link ResultReason}, or {@code null} if not present.
     */
    ResultReason getResultReason();

    /**
     * Retrieves the {@link ResponsePayloadStructure} for this batch item.
     *
     * @return The {@link ResponsePayloadStructure} containing the data returned by the operation, or {@code null} if not present.
     */
    ResponsePayloadStructure getResponsePayload();

    /**
     * A composite key for the registries, uniquely identifying an implementation by its KMIP specification version.
     *
     * @param spec The KMIP specification version.
     */
    record RegistryKey(KmipSpec spec) {
    }
}
