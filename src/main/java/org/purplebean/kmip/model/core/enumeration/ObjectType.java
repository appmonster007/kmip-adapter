package org.purplebean.kmip.model.core.enumeration;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.util.StringUtils;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * type of a managed object.
 * <p>
 * This enumeration is a fundamental attribute of every managed object, defining its
 * category and intended use.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code CERTIFICATE}: A digital certificate.</li>
 *   <li>{@code SYMMETRIC_KEY}: A symmetric cryptographic key.</li>
 *   <li>{@code PUBLIC_KEY}: A public key of an asymmetric key pair.</li>
 *   <li>{@code PRIVATE_KEY}: A private key of an asymmetric key pair.</li>
 *   <li>{@code SPLIT_KEY}: A part of a split key.</li>
 *   <li>{@code TEMPLATE}: A template containing attributes for creating new objects.</li>
 *   <li>{@code SECRET_DATA}: Generic secret data.</li>
 *   <li>{@code OPAQUE_OBJECT}: An object whose type is not interpreted by the server.</li>
 *   <li>{@code PGP_KEY}: A Pretty Good Privacy (PGP) key.</li>
 *   <li>{@code CERTIFICATE_REQUEST}: A certificate signing request.</li>
 *   <li>{@code USER}: A user object.</li>
 *   <li>{@code GROUP}: A group object.</li>
 *   <li>{@code PASSWORD_CREDENTIAL}: A password-based credential.</li>
 *   <li>{@code DEVICE_CREDENTIAL}: A device-based credential.</li>
 *   <li>{@code ONE_TIME_PASSWORD_CREDENTIAL}: A one-time password credential.</li>
 *   <li>{@code HASHED_PASSWORD_CREDENTIAL}: A hashed password credential.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see KmipAttribute
 */
@Data
@Builder(toBuilder = true)
public class ObjectType implements KmipEnumeration, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.OBJECT_TYPE.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);
  private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
  private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
  private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY =
      new ConcurrentHashMap<>();

  static {
    for (Standard s : Standard.values()) {
      VALUE_REGISTRY.put(s.value, s);
      DESCRIPTION_REGISTRY.put(s.description.toLowerCase(Locale.ROOT), s);
    }

    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ObjectType.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ObjectType.class,
          ObjectType::of);
      KmipEnumeration.register(spec, kmipTag.getValue(), ObjectType::fromName,
          ObjectType::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private ObjectType(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link ObjectType} instance wrapping the given value.
   */
  public static ObjectType of(@NonNull Value value) {
    return new ObjectType(value);
  }

  /**
   * Returns the {@link ObjectType} instance wrapping the given value.
   */
  public static ObjectType of(@NonNull AttributeName attributeName,
                              @NonNull AttributeValue attributeValue) {
    if (!attributeName
        .getValue()
        .equals(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()))) {
      throw new IllegalArgumentException("Invalid attribute name");
    }
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof KmipEnumeration.Value<?> enumeration)) {
      throw new IllegalArgumentException("Invalid encoding type");
    }
    ObjectType.Value v = fromValue(enumeration.getValue());
    return ObjectType
        .builder()
        .value(v)
        .build();
  }

  private static void checkValidExtensionValue(int value) {
    int extensionStart = 0x80000000;
    if (value < extensionStart || value > 0) {
      throw new IllegalArgumentException(
          String.format("Extension value %d must be in range 8XXXXXXX (hex)", value)
      );
    }
  }

  /**
   * Register an extension value.
   */
  public static Value register(int value, @NonNull String description,
                               @NonNull Set<KmipSpec> supportedVersions) {
    checkValidExtensionValue(value);

    final String name = description.toLowerCase(Locale.ROOT);
    if (description
        .trim()
        .isEmpty()) {
      throw new IllegalArgumentException("Description cannot be empty");
    }
    if (supportedVersions.isEmpty()) {
      throw new IllegalArgumentException("At least one supported version must be specified");
    }
    Value existingEnumByValue = VALUE_REGISTRY.get(value);
    Value existingEnumByDescription = EXTENSION_DESCRIPTION_REGISTRY.get(name);
    if (existingEnumByValue != null || existingEnumByDescription != null) {
      return existingEnumByValue != null ? existingEnumByValue : existingEnumByDescription;
    }
    Extension custom = new Extension(value, description, supportedVersions);
    VALUE_REGISTRY.putIfAbsent(value, custom);
    DESCRIPTION_REGISTRY.putIfAbsent(name, custom);
    EXTENSION_DESCRIPTION_REGISTRY.putIfAbsent(name, custom);
    return custom;
  }

  /**
   * Look up by name.
   */
  public static Value fromName(String name) {
    final String nameLowerCase = name.toLowerCase(Locale.ROOT);
    KmipSpec spec = KmipContext.getSpec();
    Value v = DESCRIPTION_REGISTRY.get(nameLowerCase);
    return Optional
        .ofNullable(v)
        .filter(Value::isSupported)
        .orElseThrow(() -> new NoSuchElementException(
            String.format("No ObjectType value found for '%s' in KMIP spec %s", name, spec)
        ));
  }

  /**
   * Look up by value.
   */
  public static Value fromValue(int value) {
    KmipSpec spec = KmipContext.getSpec();
    Value v = VALUE_REGISTRY.get(value);
    return Optional
        .ofNullable(v)
        .filter(Value::isSupported)
        .orElseThrow(() -> new NoSuchElementException(
            String.format("No ObjectType value found for %d in KMIP spec %s", value, spec)
        ));
  }

  /**
   * Get registered values.
   */
  public static Collection<Value> registeredValues() {
    return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
  }

  private void validate() {
    // KMIP spec compatibility validation
    KmipSpec spec = KmipContext.getSpec();
    if (!value.isSupported()) {
      throw new IllegalArgumentException(
          String.format("Value '%s' for ObjectType is not supported for KMIP spec %s",
              value.getDescription(), spec)
      );
    }
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
  }

  @Override
  public KmipTag getKmipTag() {
    return kmipTag;
  }

  @Override
  public EncodingType getEncodingType() {
    return encodingType;
  }

  public String getDescription() {
    return value.getDescription();
  }

  public boolean isCustom() {
    return value.isCustom();
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && value.isSupported();
  }

  @Override
  public boolean isAlwaysPresent() {
    return true;
  }

  @Override
  public boolean isServerInitializable() {
    return true;
  }

  @Override
  public boolean isClientInitializable() {
    return false;
  }

  @Override
  public boolean isServerModifiable(State state) {
    return false;
  }

  @Override
  public boolean isClientModifiable(State state) {
    return false;
  }

  @Override
  public boolean isClientDeletable() {
    return false;
  }

  @Override
  public boolean isMultiInstanceAllowed() {
    return false;
  }

  @Override
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofEnumeration(value);
  }

  @Override
  public AttributeName getAttributeName() {
    return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
  }

  @Override
  public String getCanonicalName() {
    return kmipTag.getDescription();
  }

  public int getIntValue() {
    return value.getValue();
  }

  /**
   * The standard enumeration of Object Types.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    CERTIFICATE(0x00000001, "Certificate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SYMMETRIC_KEY(0x00000002, "SymmetricKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    PUBLIC_KEY(0x00000003, "PublicKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    PRIVATE_KEY(0x00000004, "PrivateKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SPLIT_KEY(0x00000005, "SplitKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    TEMPLATE(0x00000006, "Template", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
    SECRET_DATA(0x00000007, "SecretData", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    OPAQUE_OBJECT(0x00000008, "OpaqueObject", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    PGP_KEY(0x00000009, "PGPKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CERTIFICATE_REQUEST(0x0000000A, "CertificateRequest", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    USER(0x0000000B, "User", KmipSpec.UnknownVersion, KmipSpec.V3_0),
    GROUP(0x0000000C, "Group", KmipSpec.UnknownVersion, KmipSpec.V3_0),
    PASSWORD_CREDENTIAL(0x0000000D, "PasswordCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0),
    DEVICE_CREDENTIAL(0x0000000E, "DeviceCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0),
    ONE_TIME_PASSWORD_CREDENTIAL(0x0000000F, "OneTimePasswordCredential", KmipSpec.UnknownVersion,
        KmipSpec.V3_0),
    HASHED_PASSWORD_CREDENTIAL(0x00000010, "HashedPasswordCredential", KmipSpec.UnknownVersion,
        KmipSpec.V3_0);

    private final int value;
    private final String description;
    private final Set<KmipSpec> supportedVersions;

    private final boolean custom = false;

    Standard(int value, String description, KmipSpec... supportedVersions) {
      this.value = value;
      this.description = description;
      this.supportedVersions = Set.of(supportedVersions);
    }

    @Override
    public boolean isSupported() {
      KmipSpec spec = KmipContext.getSpec();
      return supportedVersions.contains(spec);
    }

    @Override
    public ObjectType inst() {
      return ObjectType.of(this);
    }
  }

  /**
   * An interface representing an Object Type value, which can be either a standard
   * value or a custom extension.
   */
  public interface Value extends KmipEnumeration.Value<ObjectType> {
  }

  /**
   * Represents a custom, vendor-specific Object Type.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public static class Extension implements Value {
    private final int value;
    private final String description;
    private final Set<KmipSpec> supportedVersions;

    private final boolean custom = true;

    /**
     * Constructs a custom vendor extension value.
     */
    public Extension(int value, String description, KmipSpec... supportedVersions) {
      this.value = value;
      this.description = description;
      this.supportedVersions = Set.of(supportedVersions);
    }

    @Override
    public boolean isSupported() {
      KmipSpec spec = KmipContext.getSpec();
      return supportedVersions.contains(spec);
    }

    @Override
    public ObjectType inst() {
      return ObjectType.of(this);
    }
  }
}
