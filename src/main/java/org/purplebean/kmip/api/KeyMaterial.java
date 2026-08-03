package org.purplebean.kmip.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;

/**
 * Represents the Key Material data type in KMIP.
 *
 * <p>This interface extends {@link KmipDataType} and defines the contract for all KMIP Key Material
 * objects. Key Material refers to the actual cryptographic key data itself, which can be
 * represented in various formats (e.g., raw, PKCS#1, X.509).
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>KMIP Tag:</b> Defines the standard KMIP tag for Key Material.</li>
 *   <li><b>Dynamic Registration:</b> Provides a registry system to map specific
 *   {@link KeyFormatType}
 *       values and {@link EncodingType}s to their corresponding Java class implementations and
 *       builder functions. This allows the codec to dynamically handle different key material
 *       formats during serialization and deserialization.</li>
 * </ul>
 *
 * @see KmipDataType
 * @see KeyFormatType
 */
public interface KeyMaterial extends KmipDataType {
  /**
   * The standard KMIP tag for Key Material.
   */
  KmipTag kmipTag = KmipTag.Standard.KEY_MATERIAL.inst();

  /**
   * A registry mapping a unique key (KMIP spec, encoding type, key format type) to the
   * specific {@link KmipDataType} class that represents that key material format.
   */
  Map<RegistryKey, Class<? extends KmipDataType>> KEY_FORMAT_TYPE_REGISTRY =
      new ConcurrentHashMap<>();

  /**
   * A registry mapping a unique key (KMIP spec, encoding type, key format type) to a
   * builder function that can construct a specific {@link KeyMaterial} instance.
   */
  Map<RegistryKey, Function<KeyMaterial, ? extends KeyMaterial>> KEY_FORMAT_TYPE_BUILDER_REGISTRY =
      new ConcurrentHashMap<>();

  /**
   * Registers a {@link KeyMaterial} class and its builder function with the central registries.
   *
   * <p>This method should be called for each supported key material format to enable dynamic
   * handling by the codec.
   *
   * @param spec                 The {@link KmipSpec} version for which this mapping is valid.
   * @param encodingType         The {@link EncodingType} of the key material.
   * @param keyFormatTypeValue   The {@link KeyFormatType.Value} that specifies the format of the
   *                             key material.
   * @param clazz                The {@link Class} that implements the specific key material format.
   * @param keyFormatTypeBuilder A {@link Function} that constructs an instance of the specific
   *                             key material format from a generic {@link KeyMaterial} object.
   */
  static void register(
      KmipSpec spec,
      EncodingType encodingType,
      KeyFormatType.Value keyFormatTypeValue,
      Class<? extends KmipDataType> clazz,
      Function<KeyMaterial, ? extends KeyMaterial> keyFormatTypeBuilder
  ) {
    KEY_FORMAT_TYPE_REGISTRY.put(new RegistryKey(spec, encodingType, keyFormatTypeValue), clazz);
    KEY_FORMAT_TYPE_BUILDER_REGISTRY.put(new RegistryKey(spec, encodingType, keyFormatTypeValue),
        keyFormatTypeBuilder);
  }

  /**
   * Retrieves the corresponding {@link KmipDataType} class from the registry based on
   * the KMIP specification, encoding type, and key format type.
   *
   * @param encodingType       The {@link EncodingType} of the key material.
   * @param keyFormatTypeValue The {@link KeyFormatType.Value} of the key material.
   * @return The registered {@link Class}, or {@code null} if no mapping is found.
   */
  static Class<? extends KmipDataType> getClassFromRegistry(
      EncodingType encodingType, KeyFormatType.Value keyFormatTypeValue) {
    KmipSpec spec = KmipContext.getSpec();
    return KEY_FORMAT_TYPE_REGISTRY.get(new RegistryKey(spec, encodingType, keyFormatTypeValue));
  }

  /**
   * Retrieves the builder function for a specific key material format from the registry.
   *
   * @param encodingType       The {@link EncodingType} of the key material.
   * @param keyFormatTypeValue The {@link KeyFormatType.Value} of the key material.
   * @return The registered {@link Function} builder, or {@code null} if no mapping is found.
   */
  static Function<KeyMaterial, ? extends KeyMaterial> getBuilderFromRegistry(
      EncodingType encodingType, KeyFormatType.Value keyFormatTypeValue) {
    KmipSpec spec = KmipContext.getSpec();
    return KEY_FORMAT_TYPE_BUILDER_REGISTRY.get(
        new RegistryKey(spec, encodingType, keyFormatTypeValue));
  }

  /**
   * A composite key for the key material registries, uniquely identifying a key material
   * by its specification, encoding type, and key format type.
   *
   * @param spec               The KMIP specification version.
   * @param encodingType       The encoding type of the key material.
   * @param keyFormatTypeValue The format type of the key material.
   */
  record RegistryKey(KmipSpec spec, EncodingType encodingType,
                     KeyFormatType.Value keyFormatTypeValue) {
  }
}
