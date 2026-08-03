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
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * hashing algorithm to be used in cryptographic operations.
 *
 * <p>Hashing algorithms are used to produce a fixed-size digest of a message, which is
 * essential for digital signatures, message authentication codes (MACs), and other
 * cryptographic mechanisms.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code MD2}, {@code MD4}, {@code MD5}: Older hashing algorithms, generally not
 *   recommended for new applications.</li>
 *   <li>{@code SHA_1}: The Secure Hash Algorithm 1.</li>
 *   <li>{@code SHA_224}, {@code SHA_256}, {@code SHA_384}, {@code SHA_512}: The SHA-2 family of
 *   algorithms.</li>
 *   <li>{@code RIPEMD_160}: The RACE Integrity Primitives Evaluation Message Digest algorithm.</li>
 *   <li>{@code TIGER}: The Tiger hash function.</li>
 *   <li>{@code WHIRLPOOL}: The Whirlpool hash function.</li>
 *   <li>{@code SHA_512_224}, {@code SHA_512_256}: Truncated versions of SHA-512.</li>
 *   <li>{@code SHA3_224}, {@code SHA3_256}, {@code SHA3_384}, {@code SHA3_512}: The SHA-3 family
 *   of algorithms.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see DigitalSignatureAlgorithm
 */
@Data
@Builder(toBuilder = true)
public class HashingAlgorithm implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.HASHING_ALGORITHM.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, HashingAlgorithm.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), HashingAlgorithm::fromName,
          HashingAlgorithm::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private HashingAlgorithm(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link HashingAlgorithm} instance wrapping the given value.
   */
  public static HashingAlgorithm of(@NonNull Value value) {
    return new HashingAlgorithm(value);
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
            String.format("No HashingAlgorithm value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No HashingAlgorithm value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for HashingAlgorithm is not supported for KMIP spec %s",
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

  /**
   * The standard enumeration of Hashing Algorithms.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    MD2(0x00000001, "MD_2", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    MD4(0x00000002, "MD_4", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    MD5(0x00000003, "MD_5", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA_1(0x00000004, "SHA_1", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SHA_224(0x00000005, "SHA_224", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SHA_256(0x00000006, "SHA_256", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SHA_384(0x00000007, "SHA_384", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SHA_512(0x00000008, "SHA_512", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    RIPEMD_160(0x00000009, "RIPEMD_160", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    TIGER(0x0000000A, "TIGER", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    WHIRLPOOL(0x0000000B, "WHIRLPOOL", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SHA_512_224(0x0000000C, "SHA_512_224", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SHA_512_256(0x0000000D, "SHA_512_256", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SHA3_224(0x0000000E, "SHA3_224", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA3_256(0x0000000F, "SHA3_256", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA3_384(0x00000010, "SHA3_384", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SHA3_512(0x00000011, "SHA3_512", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
    public HashingAlgorithm inst() {
      return HashingAlgorithm.of(this);
    }
  }

  /**
   * An interface representing a Hashing Algorithm value, which can be either a standard
   * value or a custom extension.
   */
  public interface Value extends KmipEnumeration.Value<HashingAlgorithm> {
  }

  /**
   * Represents a custom, vendor-specific Hashing Algorithm.
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
    public HashingAlgorithm inst() {
      return HashingAlgorithm.of(this);
    }
  }
}
