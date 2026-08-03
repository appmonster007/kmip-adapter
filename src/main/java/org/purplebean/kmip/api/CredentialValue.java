package org.purplebean.kmip.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.purplebean.kmip.model.core.enumeration.CredentialType;

/**
 * Represents the CredentialValue data type in KMIP.
 *
 * <p>This interface extends {@link KmipDataType} and serves as a marker for all
 * KMIP CredentialValue objects. A CredentialValue contains authentication
 * information, such as a username and password, or a certificate.
 *
 * <p><b>Key Characteristics:</b></p>
 * <ul>
 * <li><b>KMIP Tag:</b> All CredentialValue objects are associated with the standard
 * {@code CredentialValue} tag from the KMIP specification.</li>
 * <li><b>Container Role:</b> This data type's primary purpose is to hold
 * authentication-related data.</li>
 * </ul>
 *
 * @see KmipDataType
 * @see org.purplebean.kmip.model.core.structure.Credential
 * @see org.purplebean.kmip.model.core.structure.UsernameAndPassword
 * @see org.purplebean.kmip.model.core.structure.X509Certificate
 */
public interface CredentialValue extends KmipDataType {
  /**
   * The standard KMIP tag for a CredentialValue, which is always
   * {@link KmipTag.Standard#CREDENTIAL_VALUE}.
   */
  KmipTag kmipTag = KmipTag.Standard.CREDENTIAL_VALUE.inst();

  /**
   * A registry mapping a unique key (KMIP spec, encoding type, credential type) to the
   * specific {@link KmipDataType} class that represents that credential format.
   */
  Map<RegistryKey, Class<? extends KmipDataType>> CREDENTIAL_TYPE_REGISTRY =
      new ConcurrentHashMap<>();

  /**
   * A registry mapping a unique key (KMIP spec, encoding type, credential type) to a
   * builder function that can construct a specific {@link CredentialValue} instance.
   */
  Map<RegistryKey, Function<CredentialValue, ? extends CredentialValue>>
      CREDENTIAL_TYPE_BUILDER_REGISTRY = new ConcurrentHashMap<>();

  /**
   * Registers a {@link CredentialValue} class and its builder function with the central registries.
   *
   * <p>This method should be called for each supported credential format to enable dynamic
   * handling by the codec.
   *
   * @param spec                  The {@link KmipSpec} version for which this mapping is valid.
   * @param encodingType          The {@link EncodingType} of the credential.
   * @param credentialTypeValue   The {@link CredentialType.Value} that specifies the format of
   *                              the credential.
   * @param clazz                 The {@link Class} that implements the specific credential format.
   * @param credentialTypeBuilder A {@link Function} that constructs an instance of the specific
   *                              credential format from a generic {@link CredentialValue} object.
   */
  static void register(
      KmipSpec spec,
      EncodingType encodingType,
      CredentialType.Value credentialTypeValue,
      Class<? extends KmipDataType> clazz,
      Function<CredentialValue, ? extends CredentialValue> credentialTypeBuilder
  ) {
    CREDENTIAL_TYPE_REGISTRY.put(new RegistryKey(spec, encodingType, credentialTypeValue), clazz);
    CREDENTIAL_TYPE_BUILDER_REGISTRY.put(new RegistryKey(spec, encodingType, credentialTypeValue),
        credentialTypeBuilder);
  }

  /**
   * Retrieves the corresponding {@link KmipDataType} class from the registry based on
   * the KMIP specification, encoding type, and credential type.
   *
   * @param encodingType        The {@link EncodingType} of the credential.
   * @param credentialTypeValue The {@link CredentialType.Value} of the credential.
   * @return The registered {@link Class}, or {@code null} if no mapping is found.
   */
  static Class<? extends KmipDataType> getClassFromRegistry(
      EncodingType encodingType, CredentialType.Value credentialTypeValue) {
    KmipSpec spec = KmipContext.getSpec();
    return CREDENTIAL_TYPE_REGISTRY.get(new RegistryKey(spec, encodingType, credentialTypeValue));
  }

  /**
   * Retrieves the builder function for a specific credential format from the registry.
   *
   * @param encodingType        The {@link EncodingType} of the credential.
   * @param credentialTypeValue The {@link CredentialType.Value} of the credential.
   * @return The registered {@link Function} builder, or {@code null} if no mapping is found.
   */
  static Function<CredentialValue, ? extends CredentialValue> getBuilderFromRegistry(
      EncodingType encodingType, CredentialType.Value credentialTypeValue) {
    KmipSpec spec = KmipContext.getSpec();
    return CREDENTIAL_TYPE_BUILDER_REGISTRY.get(
        new RegistryKey(spec, encodingType, credentialTypeValue));
  }

  /**
   * A composite key for the credential registries, uniquely identifying a credential
   * by its specification, encoding type, and credential type.
   *
   * @param spec                The KMIP specification version.
   * @param encodingType        The encoding type of the credential.
   * @param credentialTypeValue The type of the credential.
   */
  record RegistryKey(KmipSpec spec, EncodingType encodingType,
                     CredentialType.Value credentialTypeValue) {
  }
}
