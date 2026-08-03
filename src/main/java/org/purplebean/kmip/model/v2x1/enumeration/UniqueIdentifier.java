package org.purplebean.kmip.model.v2x1.enumeration;

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
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.type.AttributeValue;

/**
 * KMIP Unique Identifier Enumeration (OASIS kmip-spec-v2.0+ §11.58 / v2.1 Table 489).
 *
 * <p>Identifies an object that the server should resolve to a managed object in operation
 * payloads. Shares KMIP tag {@code 0x420094} with
 * {@link org.purplebean.kmip.model.core.type.UniqueIdentifier} (the TextString form used in
 * v1.x); the two are distinguished by encoding type — {@code Enumeration} (this class) versus
 * {@code TextString} (the type-package sibling).</p>
 *
 * <p>The class name matches the type-package peer (the
 * {@link org.purplebean.kmip.model.core.type.AsynchronousIndicator}
 * / {@link org.purplebean.kmip.model.v2x1.enumeration.AsynchronousIndicator} precedent);
 * consumers disambiguate by import.</p>
 *
 * <p>{@link org.purplebean.kmip.model.core.type.UniqueIdentifier} is a concrete class (not an
 * interface), so this class cannot implement it — it only shares the KMIP tag value with it, not
 * a Java type relationship. The two are independently registered under different
 * {@link org.purplebean.kmip.api.EncodingType} keys ({@code Enumeration} here vs.
 * {@code TextString}/{@code Integer}/{@code Identifier}/{@code Reference}/{@code NameReference}
 * there), so generic {@code KmipDataType} dispatch never needs to cross-cast between them.</p>
 */
@Data
@Builder(toBuilder = true)
public class UniqueIdentifier implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.UNIQUE_IDENTIFIER.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, UniqueIdentifier.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), UniqueIdentifier::fromName,
          UniqueIdentifier::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private UniqueIdentifier(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link UniqueIdentifier} instance wrapping the given value.
   */
  public static UniqueIdentifier of(@NonNull Value value) {
    return new UniqueIdentifier(value);
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
            String.format("No UniqueIdentifier value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No UniqueIdentifier value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for UniqueIdentifier is not supported for KMIP spec %s",
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

  public int getIntValue() {
    return value.getValue();
  }

  // This class is a plain KmipEnumeration/KmipDataType, not a KmipAttribute: it is not
  // registered against the KmipAttribute registry (see the static block above, which only calls
  // KmipDataType.register / KmipEnumeration.register), so no KmipAttribute capability-flag
  // methods (isAlwaysPresent, getAttributeName, getCanonicalName, etc.) are declared here.

  public AttributeValue getAttributeValue() {
    return AttributeValue.ofEnumeration(value);
  }

  /**
   * Standard KMIP-defined values.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    ID_PLACEHOLDER(0x00000001, "IDPlaceholder", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CERTIFY(0x00000002, "Certify", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    CREATE(0x00000003, "Create", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    CREATE_KEY_PAIR(0x00000004, "CreateKeyPair", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CREATE_KEY_PAIR_PRIVATE_KEY(0x00000005, "CreateKeyPairPrivateKey", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    CREATE_KEY_PAIR_PUBLIC_KEY(0x00000006, "CreateKeyPairPublicKey", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    CREATE_SPLIT_KEY(0x00000007, "CreateSplitKey", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DERIVE_KEY(0x00000008, "DeriveKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    IMPORT(0x00000009, "Import", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    JOIN_SPLIT_KEY(0x0000000A, "JoinSplitKey", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    LOCATE(0x0000000B, "Locate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    REGISTER(0x0000000C, "Register", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    RE_KEY(0x0000000D, "ReKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    RE_CERTIFY(0x0000000E, "ReCertify", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    RE_KEY_KEY_PAIR(0x0000000F, "ReKeyKeyPair", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    RE_KEY_KEY_PAIR_PRIVATE_KEY(0x00000010, "ReKeyKeyPairPrivateKey", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    RE_KEY_KEY_PAIR_PUBLIC_KEY(0x00000011, "ReKeyKeyPairPublicKey", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0);

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
    public UniqueIdentifier inst() {
      return UniqueIdentifier.of(this);
    }
  }

  // ----- Value hierarchy -----
  /**
   * Represents a specific value of the enclosing enumeration.
   */
  public interface Value extends KmipEnumeration.Value<UniqueIdentifier> {
  }

  /**
   * Vendor-defined extension value.
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
    public UniqueIdentifier inst() {
      return UniqueIdentifier.of(this);
    }
  }
}