package org.purplebean.kmip.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents a KMIP Mask data type.
 * <p>
 * This interface extends {@link KmipDataType} and defines the contract for KMIP mask types.
 * A mask type in KMIP is typically a bitmask represented as an integer, where each bit
 * corresponds to a specific permission or capability.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Mask Representation:</b> Provides methods to get the mask as an integer value
 *       or as a string representation (if applicable).</li>
 *   <li><b>Dynamic Registration:</b> Includes a registry to map KMIP tags to functions
 *       that can parse a string representation into a specific {@link KmipMaskType} instance.</li>
 * </ul>
 *
 * @see KmipDataType
 */
public interface KmipMaskType extends KmipDataType {

  /**
   * A registry mapping a unique key (KMIP spec, KMIP tag) to a function that can
   * create a {@link KmipMaskType} from a string representation.
   */
  Map<RegistryKey, Function<String, ? extends KmipMaskType>> FROM_MASK_STRING_REGISTRY =
      new ConcurrentHashMap<>();

  /**
   * Registers a function to create a {@link KmipMaskType} from a string.
   *
   * @param spec           The {@link KmipSpec} version.
   * @param kmipTagValue   The {@link KmipTag.Value} of the mask type.
   * @param fromMaskString The function to create the mask type from a string.
   */
  static void register(
      KmipSpec spec,
      KmipTag.Value kmipTagValue,
      Function<String, ? extends KmipMaskType> fromMaskString
  ) {
    FROM_MASK_STRING_REGISTRY.put(new RegistryKey(spec, kmipTagValue), fromMaskString);
  }

  /**
   * Retrieves the registered function to create a {@link KmipMaskType} from a string.
   *
   * @param kmipTagValue The {@link KmipTag.Value} of the mask type.
   * @return The registered function, or {@code null} if not found.
   */
  static Function<String, ? extends KmipMaskType> getFromMaskString(KmipTag.Value kmipTagValue) {
    KmipSpec spec = KmipContext.getSpec();
    return FROM_MASK_STRING_REGISTRY.get(new RegistryKey(spec, kmipTagValue));
  }

  /**
   * Gets the string representation of the mask.
   *
   * @return The mask string.
   */
  String getMaskString();

  /**
   * A composite key for the mask string registry.
   *
   * @param spec         The KMIP specification version.
   * @param kmipTagValue The KMIP tag.
   */
  record RegistryKey(KmipSpec spec, KmipTag.Value kmipTagValue) {
  }
}
