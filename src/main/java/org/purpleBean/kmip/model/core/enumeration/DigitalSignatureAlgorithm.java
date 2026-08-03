package org.purpleBean.kmip.model.core.enumeration;

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
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.util.StringUtils;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * algorithm to be used for digital signatures.
 * <p>
 * This enumeration lists various combinations of hashing algorithms and public-key
 * algorithms that can be used to create and verify digital signatures.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code MD2_WITH_RSA_ENCRYPTION}, {@code MD5_WITH_RSA_ENCRYPTION}: Older RSA-based
 *   signature schemes.</li>
 *   <li>{@code SHA_1_WITH_RSA_ENCRYPTION}, {@code SHA_224_WITH_RSA_ENCRYPTION}, etc.: RSA-based
 *   signatures with various SHA hashing algorithms.</li>
 *   <li>{@code RSASSA_PSS}: The RSA Signature Scheme with Appendix - Probabilistic Signature
 *   Scheme.</li>
 *   <li>{@code DSA_WITH_SHA_1}, {@code DSA_WITH_SHA224}, etc.: DSA-based signatures with various
 *   SHA hashing algorithms.</li>
 *   <li>{@code ECDSA_WITH_SHA_1}, {@code ECDSA_WITH_SHA224}, etc.: ECDSA-based signatures with
 *   various SHA hashing algorithms.</li>
 *   <li>{@code SHA3_256_WITH_RSA_ENCRYPTION}, etc.: RSA-based signatures with SHA-3 hashing
 *   algorithms.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see CryptographicAlgorithm
 * @see HashingAlgorithm
 */
@Data
@Builder(toBuilder = true)
public class DigitalSignatureAlgorithm implements KmipEnumeration, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.DIGITAL_SIGNATURE_ALGORITHM.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          DigitalSignatureAlgorithm.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType,
          DigitalSignatureAlgorithm.class, DigitalSignatureAlgorithm::of);
      KmipEnumeration.register(spec, kmipTag.getValue(), DigitalSignatureAlgorithm::fromName,
          DigitalSignatureAlgorithm::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private DigitalSignatureAlgorithm(@NonNull Value value) {
    this.value = value;
    validate();
  }

  public static DigitalSignatureAlgorithm of(@NonNull Value value) {
    return new DigitalSignatureAlgorithm(value);
  }

  public static DigitalSignatureAlgorithm of(@NonNull AttributeName attributeName,
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
    DigitalSignatureAlgorithm.Value v = fromValue(enumeration.getValue());
    return DigitalSignatureAlgorithm
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
            String.format("No DigitalSignatureAlgorithm value found for '%s' in KMIP spec %s", name,
                spec)
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
            String.format("No DigitalSignatureAlgorithm value found for %d in KMIP spec %s", value,
                spec)
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
          String.format(
              "Value '%s' for DigitalSignatureAlgorithm is not supported for KMIP spec %s",
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
    return true;
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
   * The standard enumeration of Digital Signature Algorithms.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    MD2_WITH_RSA_ENCRYPTION(0x00000001, "MD2WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    MD5_WITH_RSA_ENCRYPTION(0x00000002, "MD5WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA_1_WITH_RSA_ENCRYPTION(0x00000003, "SHA_1WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA_224_WITH_RSA_ENCRYPTION(0x00000004, "SHA_224WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA_256_WITH_RSA_ENCRYPTION(0x00000005, "SHA_256WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA_384_WITH_RSA_ENCRYPTION(0x00000006, "SHA_384WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA_512_WITH_RSA_ENCRYPTION(0x00000007, "SHA_512WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    RSASSA_PSS(0x00000008, "RSASSAPSS", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DSA_WITH_SHA_1(0x00000009, "DSAWithSHA1", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DSA_WITH_SHA224(0x0000000A, "DSAWithSHA224", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    DSA_WITH_SHA256(0x0000000B, "DSAWithSHA256", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ECDSA_WITH_SHA_1(0x0000000C, "ECDSAWithSHA1", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ECDSA_WITH_SHA224(0x0000000D, "ECDSAWithSHA224", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ECDSA_WITH_SHA256(0x0000000E, "ECDSAWithSHA256", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ECDSA_WITH_SHA384(0x0000000F, "ECDSAWithSHA384", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ECDSA_WITH_SHA512(0x00000010, "ECDSAWithSHA512", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    SHA3_256_WITH_RSA_ENCRYPTION(0x00000011, "SHA_3256WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    SHA3_384_WITH_RSA_ENCRYPTION(0x00000012, "SHA_3384WithRSAEncryption", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    SHA3_512_WITH_RSA_ENCRYPTION(0x00000013, "SHA_3512WithRSAEncryption", KmipSpec.UnknownVersion,
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
    public DigitalSignatureAlgorithm inst() {
      return DigitalSignatureAlgorithm.of(this);
    }
  }

  /**
   * An interface representing a Digital Signature Algorithm value, which can be either a standard
   * value or a custom extension.
   */
  public interface Value extends KmipEnumeration.Value<DigitalSignatureAlgorithm> {
  }

  /**
   * Represents a custom, vendor-specific Digital Signature Algorithm.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public static class Extension implements Value {
    private final int value;
    private final String description;
    private final Set<KmipSpec> supportedVersions;

    private final boolean custom = true;

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
    public DigitalSignatureAlgorithm inst() {
      return DigitalSignatureAlgorithm.of(this);
    }
  }
}
