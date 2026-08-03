package org.purplebean.kmip.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents a KMIP (Key Management Interoperability Protocol) Enumeration data type.
 * <p>
 * This interface extends {@link KmipDataType} and serves as the base for all KMIP
 * enumerations. An enumeration in KMIP is a set of named integer constants that
 * represent specific values for a given attribute or parameter (e.g., Result Status,
 * Cryptographic Algorithm).
 *
 * <p><b>Key Characteristics:</b></p>
 * <ul>
 *   <li><b>Fixed Encoding:</b> All KMIP enumerations have a predefined {@link EncodingType}
 *       of {@code ENUMERATION}.</li>
 *   <li><b>Integer Value:</b> Each enumeration constant has an underlying integer value
 *       that is used in the TTLV encoding.</li>
 *   <li><b>Descriptive Name:</b> Each enumeration constant also has a human-readable
 *       description or name.</li>
 * </ul>
 *
 * <p>Implementations of this interface will provide the specific set of constants for a
 * particular KMIP enumeration, along with methods to access their values and descriptions.</p>
 *
 * @see KmipDataType
 * @see EncodingType
 */
public interface KmipEnumeration extends KmipDataType {

  /**
   * The fixed encoding type for all KMIP Enumerations, which is always
   * {@link EncodingType#ENUMERATION}.
   */
  EncodingType encodingType = EncodingType.ENUMERATION;

  /**
   * A registry mapping a unique key (KMIP spec, KMIP tag) to a function that can
   * resolve an enumeration value from its name.
   */
  Map<RegistryKey, Function<String, Value<?>>> FROM_NAME_REGISTRY = new ConcurrentHashMap<>();

  /**
   * A registry mapping a unique key (KMIP spec, KMIP tag) to a function that can
   * resolve an enumeration value from its integer value.
   */
  Map<RegistryKey, Function<Integer, Value<?>>> FROM_VALUE_REGISTRY = new ConcurrentHashMap<>();

  /**
   * Registers functions to resolve enumeration values from names and integer values.
   *
   * @param spec         The {@link KmipSpec} version.
   * @param kmipTagValue The {@link KmipTag.Value} of the enumeration.
   * @param fromName     The function to resolve from name.
   * @param fromValue    The function to resolve from integer value.
   */
  static void register(
      KmipSpec spec,
      KmipTag.Value kmipTagValue,
      Function<String, Value<?>> fromName,
      Function<Integer, Value<?>> fromValue
  ) {
    FROM_NAME_REGISTRY.put(new RegistryKey(spec, kmipTagValue), fromName);
    FROM_VALUE_REGISTRY.put(new RegistryKey(spec, kmipTagValue), fromValue);
  }

  /**
   * Retrieves the registered function to resolve an enumeration value from its name.
   *
   * @param kmipTagValue The {@link KmipTag.Value} of the enumeration.
   * @return The registered function, or {@code null} if not found.
   */
  static Function<String, Value<?>> getFromName(KmipTag.Value kmipTagValue) {
    KmipSpec spec = KmipContext.getSpec();
    return FROM_NAME_REGISTRY.get(new RegistryKey(spec, kmipTagValue));
  }

  /**
   * Retrieves the registered function to resolve an enumeration value from its integer value.
   *
   * @param kmipTagValue The {@link KmipTag.Value} of the enumeration.
   * @return The registered function, or {@code null} if not found.
   */
  static Function<Integer, Value<?>> getFromValue(KmipTag.Value kmipTagValue) {
    KmipSpec spec = KmipContext.getSpec();
    return FROM_VALUE_REGISTRY.get(new RegistryKey(spec, kmipTagValue));
  }

  /**
   * Gets the integer value of the enumeration constant.
   * <p>
   * This is the value that is used in the TTLV encoding of the KMIP message.
   *
   * @return The integer value of the enumeration.
   */
  int getIntValue();

  /**
   * Gets the human-readable description of the enumeration constant.
   *
   * @return The string description of the enumeration.
   */
  String getDescription();

  /**
   * Represents a specific value of a KMIP enumeration.
   *
   * @param <T> the type of the enumeration implementation.
   */
  interface Value<T> {
    /**
     * @return the integer value of the enumeration.
     */
    int getValue();

    /**
     * @return the description of the enumeration.
     */
    String getDescription();

    /**
     * @return true if the enumeration is supported in the current KMIP context, false otherwise.
     */
    boolean isSupported();

    /**
     * @return true if the enumeration is a custom extension, false otherwise.
     */
    boolean isCustom();

    /**
     * @return a new instance of the {@link T} with the current value.
     */
    T inst();
  }

  /**
   * A composite key for the enumeration registries.
   *
   * @param spec         The KMIP specification version.
   * @param kmipTagValue The KMIP tag.
   */
  record RegistryKey(KmipSpec spec, KmipTag.Value kmipTagValue) {
  }
}
