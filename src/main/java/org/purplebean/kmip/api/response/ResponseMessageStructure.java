package org.purplebean.kmip.api.response;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;

/**
 * Represents the top-level structure of a KMIP (Key Management Interoperability Protocol)
 * Response Message.
 *
 * <p>This interface defines the standard layout for any KMIP response message. A response message
 * acts as a
 * container, holding a header and one or more "batch items," where each batch item represents
 * the result of a single
 * KMIP operation.
 *
 * <p><b>Structure:</b></p>
 *
 * <p>A Response Message is a {@link KmipStructure} composed of:</p>
 * <ul>
 *   <li><b>Response Header:</b> (Required, 1) A {@link ResponseHeaderStructure} containing
 *   metadata for the
 *       entire response, such as the protocol version and batch count.</li>
 *   <li><b>Batch Item:</b> (Required, 1 or more) A {@link ResponseBatchItemStructure} for each
 *   operation's result
 *       included in the response.</li>
 * </ul>
 *
 * <p><b>Dynamic Registration:</b></p>
 *
 * <p>This interface includes a static registration mechanism to support different message
 * structures across
 * various KMIP specification versions. Implementations for specific versions register
 * themselves, allowing
 * the codec to dynamically instantiate the correct message class based on the active
 * {@link KmipContext}.</p>
 *
 * @see KmipStructure
 * @see ResponseHeaderStructure
 * @see ResponseBatchItemStructure
 */
public interface ResponseMessageStructure extends KmipStructure {

  /**
   * The standard KMIP tag for a Response Message.
   */
  KmipTag kmipTag = KmipTag.Standard.RESPONSE_MESSAGE.inst();

  /**
   * A registry mapping a {@link RegistryKey} (containing a {@link KmipSpec}) to the specific
   * {@link ResponseMessageStructure} class implementation for that specification version.
   */
  Map<RegistryKey, Class<? extends ResponseMessageStructure>> REGISTRY = new ConcurrentHashMap<>();

  /**
   * A registry mapping a {@link RegistryKey} to a builder function that can construct a specific
   * {@link ResponseMessageStructure} instance from its constituent parts.
   */
  Map<RegistryKey, BiFunction<List<KmipDataType>, List<Exception>, ?
      extends ResponseMessageStructure>>
      BUILDER_REGISTRY = new ConcurrentHashMap<>();

  /**
   * Registers a {@link ResponseMessageStructure} implementation and its builder for a specific
   * KMIP version.
   *
   * @param spec    The {@link KmipSpec} version for which this implementation is valid.
   * @param clazz   The {@link Class} that implements the response message for the specified
   *                version.
   * @param builder A {@link BiFunction} that constructs an instance of the class.
   */
  static void register(
      KmipSpec spec,
      Class<? extends ResponseMessageStructure> clazz,
      BiFunction<List<KmipDataType>, List<Exception>, ? extends ResponseMessageStructure> builder
  ) {
    REGISTRY.put(new RegistryKey(spec), clazz);
    BUILDER_REGISTRY.put(new RegistryKey(spec), builder);
  }

  /**
   * Retrieves the appropriate {@link ResponseMessageStructure} class from the registry based on the
   * currently active {@link KmipContext}.
   *
   * @return The registered {@link Class} for the active KMIP specification, or {@code null} if
   * none is found.
   */
  static Class<? extends ResponseMessageStructure> getClassFromRegistry() {
    KmipSpec spec = KmipContext.getSpec();
    return REGISTRY.get(new RegistryKey(spec));
  }

  /**
   * Retrieves the appropriate builder function from the registry based on the currently active
   * {@link KmipContext}.
   *
   * @return The registered {@link BiFunction} builder for the active KMIP specification, or
   * {@code null} if none is found.
   */
  static BiFunction<List<KmipDataType>, List<Exception>, ? extends ResponseMessageStructure>
      getBuilderFromRegistry() {
    KmipSpec spec = KmipContext.getSpec();
    return BUILDER_REGISTRY.get(new RegistryKey(spec));
  }

  /**
   * A factory method that constructs a {@link ResponseMessageStructure} instance using the
   * builder registered
   * for the currently active {@link KmipContext}.
   *
   * @param values The list of {@link KmipDataType} values that constitute the message.
   * @param errors A list of exceptions encountered during parsing, which may be relevant for
   *               constructing the object.
   * @return A new instance of a {@link ResponseMessageStructure} implementation.
   */
  static ResponseMessageStructure of(List<KmipDataType> values, List<Exception> errors) {
    return getBuilderFromRegistry().apply(values, errors);
  }

  /**
   * Retrieves the {@link ResponseHeaderStructure} associated with this response message.
   *
   * @return The {@link ResponseHeaderStructure} of this response.
   */
  ResponseHeaderStructure getResponseHeader();

  /**
   * Retrieves a list of {@link ResponseBatchItemStructure} objects, each representing
   * an individual KMIP operation's result within this response message.
   *
   * @return A list of {@link ResponseBatchItemStructure} instances.
   */
  List<? extends ResponseBatchItemStructure> getResponseBatchItems();

  /**
   * Retrieves a list of exceptions or errors that occurred during the processing
   * of individual {@link ResponseBatchItemStructure} objects within this response.
   * This list is typically populated during deserialization or validation.
   *
   * @return A list of {@link Exception} instances related to batch item processing.
   */
  List<? extends Exception> getResponseBatchItemErrors();

  /**
   * A composite key for the registries, uniquely identifying an implementation by its KMIP
   * specification version.
   *
   * @param spec The KMIP specification version.
   */
  record RegistryKey(KmipSpec spec) {
  }
}
