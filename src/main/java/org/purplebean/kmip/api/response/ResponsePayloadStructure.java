package org.purplebean.kmip.api.response;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.Operation;

/**
 * Represents the payload of a single KMIP (Key Management Interoperability Protocol) operation
 * response.
 *
 * <p>This interface defines the structure for the payload associated with the result of a specific
 * KMIP operation.
 * The payload contains all the data returned by the server for that operation. Because the
 * content of the
 * payload is unique to each operation, this interface provides a dynamic registration mechanism
 * to map
 * specific operations to their corresponding payload implementations.
 *
 * <p><b>Structure:</b></p>
 *
 * <p>A Response Payload is a {@link KmipStructure} whose contents depend entirely on the KMIP
 * operation
 * it is associated with. For example:</p>
 * <ul>
 *   <li>A {@code Create} response payload contains a {@code UniqueIdentifier}.</li>
 *   <li>A {@code Get} response payload contains an {@code ObjectType} and a {@code
 *   UniqueIdentifier}.</li>
 * </ul>
 *
 * <p><b>Dynamic Registration:</b></p>
 *
 * <p>This interface includes a static registration system where each concrete payload
 * implementation
 * registers itself against a specific {@link KmipSpec} and {@link Operation}. This allows the
 * codec to
 * look up and instantiate the correct payload class when deserializing a response, based on the
 * operation specified in the corresponding request's batch item.</p>
 *
 * @see KmipStructure
 * @see ResponseBatchItemStructure
 * @see Operation
 */
public interface ResponsePayloadStructure extends KmipStructure {

  /**
   * The standard KMIP tag for a Response Payload.
   */
  KmipTag kmipTag = KmipTag.Standard.RESPONSE_PAYLOAD.inst();

  /**
   * A registry mapping a {@link RegistryKey} (containing a {@link KmipSpec} and
   * {@link Operation.Value})
   * to the specific {@link ResponsePayloadStructure} class implementation for that combination.
   */
  Map<RegistryKey, Class<? extends ResponsePayloadStructure>> PAYLOAD_REGISTRY =
      new ConcurrentHashMap<>();

  /**
   * A registry mapping a {@link RegistryKey} to a builder function that can construct a specific
   * {@link ResponsePayloadStructure} instance from a list of its constituent
   * {@link KmipDataType} values.
   */
  Map<RegistryKey, Function<List<KmipDataType>, ? extends ResponsePayloadStructure>>
      PAYLOAD_BUILDER_REGISTRY = new ConcurrentHashMap<>();

  /**
   * Registers a {@link ResponsePayloadStructure} implementation and its builder for a specific
   * KMIP version and operation.
   *
   * @param spec           The {@link KmipSpec} version for which this implementation is valid.
   * @param operationValue The {@link Operation.Value} this payload corresponds to.
   * @param clazz          The {@link Class} that implements the response payload.
   * @param payloadBuilder A {@link Function} that constructs an instance of the class from a
   *                       list of values.
   */
  static void register(
      KmipSpec spec,
      Operation.Value operationValue,
      Class<? extends ResponsePayloadStructure> clazz,
      Function<List<KmipDataType>, ? extends ResponsePayloadStructure> payloadBuilder
  ) {
    PAYLOAD_REGISTRY.put(new RegistryKey(spec, operationValue), clazz);
    PAYLOAD_BUILDER_REGISTRY.put(new RegistryKey(spec, operationValue), payloadBuilder);
  }

  /**
   * Retrieves the appropriate {@link ResponsePayloadStructure} class from the registry based on the
   * currently active {@link KmipContext} and the specified operation.
   *
   * @param operationValue The {@link Operation.Value} to look up.
   * @return The registered {@link Class} for the active KMIP spec and operation, or {@code null}
   * if none is found.
   */
  static Class<? extends ResponsePayloadStructure> getClassFromRegistry(
      Operation.Value operationValue) {
    KmipSpec spec = KmipContext.getSpec();
    return PAYLOAD_REGISTRY.get(new RegistryKey(spec, operationValue));
  }

  /**
   * Retrieves the appropriate builder function from the registry based on the currently active
   * {@link KmipContext} and the specified operation.
   *
   * @param operationValue The {@link Operation.Value} to look up.
   * @return The registered {@link Function} builder, or {@code null} if none is found.
   */
  static Function<List<KmipDataType>, ? extends ResponsePayloadStructure> getBuilderFromRegistry(
      Operation.Value operationValue) {
    KmipSpec spec = KmipContext.getSpec();
    return PAYLOAD_BUILDER_REGISTRY.get(new RegistryKey(spec, operationValue));
  }

  /**
   * Gets the KMIP {@link Operation} that this payload structure corresponds to.
   *
   * @return The corresponding {@link Operation}.
   */
  Operation getCorrespondingOperation();

  /**
   * A composite key for the registries, uniquely identifying a payload implementation by its
   * KMIP specification version and the operation it corresponds to.
   *
   * @param spec           The KMIP specification version.
   * @param operationValue The KMIP operation value.
   */
  record RegistryKey(KmipSpec spec, Operation.Value operationValue) {
  }
}
