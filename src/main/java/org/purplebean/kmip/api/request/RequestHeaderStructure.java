package org.purplebean.kmip.api.request;

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
 * Represents the header of a KMIP (Key Management Interoperability Protocol) Request Message.
 *
 * <p>This interface defines the structure and essential components of a KMIP request header. The
 * header
 * contains metadata about the request, with the most critical piece being the protocol version,
 * which
 * dictates how the rest of the message should be interpreted.
 *
 * <p><b>Structure:</b></p>
 *
 * <p>A Request Header is a {@link KmipStructure} that typically contains:</p>
 * <ul>
 *   <li><b>Protocol Version:</b> (Required) Specifies the version of the KMIP protocol the
 *   client is using.</li>
 *   <li><b>Maximum Response Size:</b> (Optional) An integer indicating the maximum size of the
 *   response the
 *       client is prepared to accept.</li>
 *   <li><b>Authentication:</b> (Optional) A credential used to authenticate the client to the
 *   server.</li>
 *   <li><b>Batch Count:</b> (Required) An integer indicating the number of batch items in the
 *   request.</li>
 * </ul>
 *
 * <p><b>Dynamic Registration:</b></p>
 *
 * <p>This interface includes a static registration mechanism to support different header
 * structures across
 * various KMIP specification versions. Implementations for specific versions register
 * themselves, allowing
 * the codec to dynamically instantiate the correct header class based on the active
 * {@link KmipContext}.</p>
 *
 * @see KmipStructure
 * @see RequestMessageStructure
 * @see ProtocolVersion
 */
public interface RequestHeaderStructure extends KmipStructure {
  /**
   * The standard KMIP tag for a Request Header.
   */
  KmipTag kmipTag = KmipTag.Standard.REQUEST_HEADER.inst();

  /**
   * A registry mapping a {@link RegistryKey} (containing a {@link KmipSpec}) to the specific
   * {@link RequestHeaderStructure} class implementation for that specification version.
   */
  Map<RegistryKey, Class<? extends RequestHeaderStructure>> REGISTRY = new ConcurrentHashMap<>();

  /**
   * A registry mapping a {@link RegistryKey} to a builder function that can construct a specific
   * {@link RequestHeaderStructure} instance from a list of its constituent {@link KmipDataType}
   * values.
   */
  Map<RegistryKey, Function<List<KmipDataType>, ? extends RequestHeaderStructure>>
      BUILDER_REGISTRY = new ConcurrentHashMap<>();

  /**
   * Registers a {@link RequestHeaderStructure} implementation and its builder for a specific
   * KMIP version.
   *
   * <p>This method is called by concrete implementation classes in their static initializers to
   * make themselves
   * discoverable by the framework.
   *
   * @param spec    The {@link KmipSpec} version for which this implementation is valid.
   * @param clazz   The {@link Class} that implements the request header for the specified version.
   * @param builder A {@link Function} that constructs an instance of the class from a list of
   *                values.
   */
  static void register(
      KmipSpec spec,
      Class<? extends RequestHeaderStructure> clazz,
      Function<List<KmipDataType>, ? extends RequestHeaderStructure> builder
  ) {
    REGISTRY.put(new RegistryKey(spec), clazz);
    BUILDER_REGISTRY.put(new RegistryKey(spec), builder);
  }

  /**
   * Retrieves the appropriate {@link RequestHeaderStructure} class from the registry based on the
   * currently active {@link KmipContext}.
   *
   * @return The registered {@link Class} for the active KMIP specification, or {@code null} if
   * none is found.
   */
  static Class<? extends RequestHeaderStructure> getClassFromRegistry() {
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
  static Function<List<KmipDataType>, ? extends RequestHeaderStructure> getBuilderFromRegistry() {
    KmipSpec spec = KmipContext.getSpec();
    return BUILDER_REGISTRY.get(new RegistryKey(spec));
  }

  /**
   * A factory method that constructs a {@link RequestHeaderStructure} instance using the builder
   * registered
   * for the currently active {@link KmipContext}.
   *
   * @param values The list of {@link KmipDataType} values that constitute the header.
   * @return A new instance of a {@link RequestHeaderStructure} implementation.
   */
  static RequestHeaderStructure of(List<KmipDataType> values) {
    return getBuilderFromRegistry().apply(values);
  }

  /**
   * Retrieves the {@link ProtocolVersion} from the request header.
   *
   * <p>The protocol version indicates which version of the KMIP specification the
   * request conforms to.
   *
   * @return The {@link ProtocolVersion} of the request.
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
