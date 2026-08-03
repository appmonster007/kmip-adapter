package org.purplebean.kmip.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;

/**
 * Represents a KMIP (Key Management Interoperability Protocol) attribute.
 *
 * <p>This interface extends {@link KmipDataType} and defines the contract for all KMIP attributes.
 * Attributes are used to describe the properties of managed objects (e.g., cryptographic keys,
 * certificates). This interface provides methods to query the characteristics and capabilities
 * of an attribute, such as whether it is server-initializable, client-modifiable, or always
 * present.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Capability Flags:</b> Defines a set of methods (e.g., {@code isServerInitializable},
 *       {@code isClientModifiable}) that describe the behavior and constraints of the attribute
 *       as defined by the KMIP specification.</li>
 *   <li><b>State-Dependent Behavior:</b> Some attribute capabilities, like modifiability, can
 *       depend on the {@link State} of the managed object. This interface provides methods
 *       to check these capabilities against a given state.</li>
 *   <li><b>Attribute Representation:</b> Provides methods to get the generic {@link AttributeName}
 *       and {@link AttributeValue} of the attribute, facilitating its inclusion in a generic
 *       {@code Attribute} structure.</li>
 *   <li><b>Dynamic Registration:</b> Includes a static registry system to map KMIP tags and
 *       encoding types to their corresponding Java class implementations and builder functions.
 *       This allows the codec to dynamically construct specific attribute objects from their
 *       generic representation.</li>
 * </ul>
 *
 * @see KmipDataType
 * @see AttributeName
 * @see AttributeValue
 * @see State
 */
public interface KmipAttribute extends KmipDataType {
  /**
   * A registry mapping a key to the specific {@link KmipAttribute} class.
   */
  Map<RegistryKey, Class<? extends KmipAttribute>> ATTRIBUTE_REGISTRY = new ConcurrentHashMap<>();

  /**
   * A registry mapping a key to a builder function that can construct a specific
   * {@link KmipAttribute} instance.
   */
  Map<RegistryKey, BiFunction<AttributeName, AttributeValue, ? extends KmipAttribute>>
      ATTRIBUTE_BUILDER_REGISTRY = new ConcurrentHashMap<>();

  /**
   * Registers a KMIP attribute class and its builder function with the central registries.
   *
   * @param spec             The {@link KmipSpec} version for which this mapping is valid.
   * @param kmipTagValue     The {@link KmipTag.Value} that identifies the attribute.
   * @param encodingType     The {@link EncodingType} of the attribute's value.
   * @param clazz            The {@link Class} that implements the attribute.
   * @param attributeBuilder A {@link BiFunction} that constructs an instance of the attribute
   *                         from an {@link AttributeName} and {@link AttributeValue}.
   */
  static void register(
      KmipSpec spec,
      KmipTag.Value kmipTagValue,
      EncodingType encodingType,
      Class<? extends KmipAttribute> clazz,
      BiFunction<AttributeName, AttributeValue, ? extends KmipAttribute> attributeBuilder
  ) {
    ATTRIBUTE_REGISTRY.put(new RegistryKey(spec, kmipTagValue, encodingType), clazz);
    ATTRIBUTE_BUILDER_REGISTRY.put(new RegistryKey(spec, kmipTagValue, encodingType),
        attributeBuilder);
  }

  /**
   * Retrieves the corresponding {@link KmipAttribute} class from the registry.
   *
   * @param kmipTagValue The {@link KmipTag.Value} of the attribute.
   * @param encodingType The {@link EncodingType} of the attribute.
   * @return The registered {@link Class}, or {@code null} if not found.
   */
  static Class<? extends KmipAttribute> getClassFromRegistry(KmipTag.Value kmipTagValue,
                                                             EncodingType encodingType) {
    KmipSpec spec = KmipContext.getSpec();
    return ATTRIBUTE_REGISTRY.get(new RegistryKey(spec, kmipTagValue, encodingType));
  }

  /**
   * Retrieves the builder function for a specific attribute from the registry.
   *
   * @param kmipTagValue The {@link KmipTag.Value} of the attribute.
   * @param encodingType The {@link EncodingType} of the attribute.
   * @return The registered {@link BiFunction} builder, or {@code null} if not found.
   */
  static BiFunction<AttributeName, AttributeValue, ? extends KmipAttribute>
      getAttributeBuilderFromRegistry(KmipTag.Value kmipTagValue, EncodingType encodingType) {
    KmipSpec spec = KmipContext.getSpec();
    return ATTRIBUTE_BUILDER_REGISTRY.get(new RegistryKey(spec, kmipTagValue, encodingType));
  }

  /**
   * Indicates if the attribute must always be present on a managed object.
   *
   * @return {@code true} if the attribute is mandatory, {@code false} otherwise.
   */
  boolean isAlwaysPresent();

  /**
   * Indicates if the attribute can be initialized by the server.
   *
   * @return {@code true} if the server can initialize the attribute, {@code false} otherwise.
   */
  boolean isServerInitializable();

  /**
   * Indicates if the attribute can be initialized by a client.
   *
   * @return {@code true} if a client can initialize the attribute, {@code false} otherwise.
   */
  boolean isClientInitializable();

  /**
   * Indicates if the attribute can be modified by the server, potentially depending on the
   * object's state.
   *
   * @param state The current {@link State} of the managed object.
   * @return {@code true} if the server can modify the attribute, {@code false} otherwise.
   */
  boolean isServerModifiable(State state);

  /**
   * Indicates if the attribute can be modified by a client, potentially depending on the
   * object's state.
   *
   * @param state The current {@link State} of the managed object.
   * @return {@code true} if a client can modify the attribute, {@code false} otherwise.
   */
  boolean isClientModifiable(State state);

  /**
   * Indicates if a client is allowed to delete this attribute from a managed object.
   *
   * @return {@code true} if the client can delete the attribute, {@code false} otherwise.
   */
  boolean isClientDeletable();

  /**
   * Indicates if multiple instances of this attribute are allowed on a single managed object.
   *
   * @return {@code true} if multiple instances are allowed, {@code false} otherwise.
   */
  boolean isMultiInstanceAllowed();

  /**
   * Gets the value of this attribute, wrapped in an {@link AttributeValue} object.
   *
   * @return The {@link AttributeValue} representation of this attribute's value.
   */
  AttributeValue getAttributeValue();

  /**
   * Gets the name of this attribute, wrapped in an {@link AttributeName} object.
   *
   * @return The {@link AttributeName} representation of this attribute's name.
   */
  AttributeName getAttributeName();

  /**
   * Gets the canonical, string-based name of the attribute as defined by the KMIP specification.
   *
   * @return The canonical name of the attribute.
   */
  String getCanonicalName();

  /**
   * A composite key for the attribute registries, uniquely identifying an attribute by its
   * specification, tag, and encoding.
   */
  record RegistryKey(KmipSpec spec, KmipTag.Value kmipTagValue, EncodingType encodingType) {
  }
}
