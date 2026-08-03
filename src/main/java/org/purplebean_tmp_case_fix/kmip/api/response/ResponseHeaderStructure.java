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
import org.purplebean.kmip.model.core.structure.ProtocolVersion;

/**
 * Represents the header of a KMIP (Key Management Interoperability Protocol) Response Message.
 * <p>
 * This interface defines the structure and essential components of a KMIP response header. The
 * header
 * contains metadata about the response, such as the protocol version used by the server, a
 * timestamp,
 * and the number of batch items in the response.
 *
 * <p><b>Structure:</b></p>
 * <p>A Response Header is a {@link KmipStructure} that typically contains:</p>
 * <ul>
 *   <li><b>Protocol Version:</b> (Required) Specifies the version of the KMIP protocol the
 *   server is using.</li>
 *   <li><b>Time Stamp:</b> (Required) The date and time the response was generated.</li>
 *   <li><b>Batch Count:</b> (Required) An integer indicating the number of batch items in the
 *   response.</li>
 * </ul>
 *
 * <p><b>Dynamic Registration:</b></p>
 * <p>This interface includes a static registration mechanism to support different header
 * structures across
 * various KMIP specification versions. Implementations for specific versions register
 * themselves, allowing
 * the codec to dynamically instantiate the correct header class based on the active
 * {@link KmipContext}.</p>
 *
 * @see KmipStructure
 * @see ResponseMessageStructure
 * @see ProtocolVersion
 */
public interface ResponseHeaderStructure extends KmipStructure {
  /**
   * The standard KMIP tag for a Response Header.
   */
  KmipTag kmipTag = KmipTag.Standard.RESPONSE_HEADER.inst();

  /**
   * A registry mapping a {@link RegistryKey} (containing a {@link KmipSpec}) to the specific
   * {@link ResponseHeaderStructure} class implementation for that specification version.
   */
  Map<RegistryKey, Class<? extends ResponseHeaderStructure>> REGISTRY = new ConcurrentHashMap<>();

  /**
   * A registry mapping a {@link RegistryKey} to a builder function that can construct a specific
   * {@link ResponseHeaderStructure} instance from a list of its constituent {@link KmipDataType}
   * values.
   */
  Map<RegistryKey, Function<List<KmipDataType>, ? extends ResponseHeaderStructure>>
      BUILDER_REGISTRY = new ConcurrentHashMap<>();

  /**
   * Registers a {@link ResponseHeaderStructure} implementation and its builder for a specific
   * KMIP version.
   *
   * @param spec    The {@link KmipSpec} version for which this implementation is valid.
   * @param clazz   The {@link Class} that implements the response header for the specified version.
   * @param builder A {@link Function} that constructs an instance of the class from a list of
   *                values.
   */
  static void register(
      KmipSpec spec,
      Class<? extends ResponseHeaderStructure> clazz,
      Function<List<KmipDataType>, ? extends ResponseHeaderStructure> builder
  ) {
    REGISTRY.put(new RegistryKey(spec), clazz);
    BUILDER_REGISTRY.put(new RegistryKey(spec), builder);
  }

  /**
   * Retrieves the appropriate {@link ResponseHeaderStructure} class from the registry based on the
   * currently active {@link KmipContext}.
   *
   * @return The registered {@link Class} for the active KMIP specification, or {@code null} if
   * none is found.
   */
  static Class<? extends ResponseHeaderStructure> getClassFromRegistry() {
    KmipSpec spec = KmipContext.getSpec();
    return REGISTRY.get(new RegistryKey(spec));
  }

  /**
   * Retrieves the appropriate builder function from the registry based on the currently active
   * {@link KmipContext}.
   *
   * @return The registered {@link Function} builder for the active KMIP specification, or
   * {@code null} if none is found.
   */
  static Function<List<KmipDataType>, ? extends ResponseHeaderStructure> getBuilderFromRegistry() {
    KmipSpec spec = KmipContext.getSpec();
    return BUILDER_REGISTRY.get(new RegistryKey(spec));
  }

  /**
   * A factory method that constructs a {@link ResponseHeaderStructure} instance using the
   * builder registered
   * for the currently active {@link KmipContext}.
   *
   * @param values The list of {@link KmipDataType} values that constitute the header.
   * @return A new instance of a {@link ResponseHeaderStructure} implementation.
   */
  static ResponseHeaderStructure of(List<KmipDataType> values) {
    return getBuilderFromRegistry().apply(values);
  }

  /**
   * Retrieves the {@link ProtocolVersion} from the response header.
   *
   * @return The {@link ProtocolVersion} of the response.
   */
  ProtocolVersion getProtocolVersion();

  /**
   * A composite key for the registries, uniquely identifying an implementation by its KMIP
   * specification version.
   *
   * @param spec The KMIP specification version.
   */
  record RegistryKey(KmipSpec spec) {
  }
}
