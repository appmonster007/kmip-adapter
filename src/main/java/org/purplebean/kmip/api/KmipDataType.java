package org.purplebean.kmip.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents a fundamental data type within the Key Management Interoperability Protocol (KMIP)
 * framework.
 *
 * <p>This interface serves as the base for all specific KMIP data types, establishing a common
 * contract for identifying
 * and handling different kinds of data in KMIP messages. It provides a centralized registry
 * system to map protocol-specific
 * identifiers (like tags and encoding types) to their corresponding Java class representations.
 *
 * <p><b>Key Responsibilities:</b></p>
 * <ul>
 *   <li><b>Type Identification:</b> Every KMIP data type must be able to provide its
 *   {@link KmipTag} and
 *       {@link EncodingType}, which together uniquely identify it within a specific KMIP
 *       specification version.</li>
 *   <li><b>Dynamic Registration:</b> A static registry ({@code TAG_REGISTRY}) allows for the
 *   dynamic registration of
 *       KMIP data types. This mechanism maps a combination of {@link KmipSpec},
 *       {@link KmipTag.Value}, and
 *       {@link EncodingType} to the Java class that implements the data type. This is crucial
 *       for the codec to
 *       know which class to instantiate during deserialization.</li>
 *   <li><b>Compatibility Checking:</b> The {@link #isSupported()} method allows for runtime
 *   checks to determine if a
 *       given data type is supported under the currently active {@link KmipContext}.</li>
 * </ul>
 *
 * <p><b>RegistryKey:</b></p>
 * The internal {@code RegistryKey} record is used as the key for the {@code TAG_REGISTRY}. It
 * ensures that the mapping
 * is precise, taking into account the KMIP specification version, the tag of the data type, and
 * its encoding.
 *
 * @see KmipStructure
 * @see KmipEnumeration
 * @see KmipAttribute
 * @see KmipTag
 * @see EncodingType
 * @see KmipContext
 */
public interface KmipDataType {
  /**
   * A thread-safe registry that maps a unique combination of KMIP specification, tag, and
   * encoding type
   * to the corresponding Java class implementation of a {@link KmipDataType}. This is the core
   * mechanism
   * that enables the codec to dynamically deserialize incoming data into the correct objects.
   */
  Map<RegistryKey, Class<? extends KmipDataType>> TAG_REGISTRY = new ConcurrentHashMap<>();

  /**
   * Registers a KMIP data type class with the central registry.
   *
   * <p>This method should be called once for each supported KMIP specification when a data type
   * class is loaded.
   * It creates a unique key based on the provided parameters and maps it to the given class.
   *
   * @param spec         The {@link KmipSpec} version for which this mapping is valid.
   * @param kmipTagValue The {@link KmipTag.Value} that identifies the data type.
   * @param encodingType The {@link EncodingType} used for the data type.
   * @param clazz        The {@link Class} that implements the data type.
   */
  static void register(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType,
                       Class<? extends KmipDataType> clazz) {
    TAG_REGISTRY.put(new RegistryKey(spec, kmipTagValue, encodingType), clazz);
  }

  /**
   * Retrieves the corresponding {@link KmipDataType} class from the registry based on a tag and
   * encoding type.
   *
   * <p>This method uses the {@link KmipContext} to determine the currently active KMIP
   * specification and looks up
   * the appropriate class for the given tag and encoding.
   *
   * @param kmipTagValue The {@link KmipTag.Value} of the data type to look up.
   * @param encodingType The {@link EncodingType} of the data type.
   * @return The registered {@link Class} for the given parameters, or {@code null} if no mapping
   * is found.
   */
  static Class<? extends KmipDataType> getClassFromRegistry(KmipTag.Value kmipTagValue,
                                                            EncodingType encodingType) {
    KmipSpec spec = KmipContext.getSpec();
    return TAG_REGISTRY.get(new RegistryKey(spec, kmipTagValue, encodingType));
  }

  /**
   * Gets the official KMIP tag associated with this data type.
   *
   * @return The {@link KmipTag} of the data type.
   */
  KmipTag getKmipTag();

  /**
   * Gets the encoding type that specifies how this data type is represented in a KMIP message.
   *
   * @return The {@link EncodingType} of the data type.
   */
  EncodingType getEncodingType();

  /**
   * Gets the underlying value of the data type.
   *
   * @return The value of the data type.
   */
  Object getValue();

  /**
   * Checks if this data type is supported in the current KMIP specification context.
   *
   * @return {@code true} if the data type is supported under the active {@link KmipContext},
   * {@code false} otherwise.
   */
  boolean isSupported();

  /**
   * A composite key used for the {@link #TAG_REGISTRY}.
   *
   * <p>This record uniquely identifies a data type by combining its specification version, tag, and
   * encoding type.
   *
   * @param spec         The KMIP specification version.
   * @param kmipTagValue The KMIP tag.
   * @param encodingType The data's encoding type.
   */
  record RegistryKey(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType) {
  }
}
