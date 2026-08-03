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
 * compression type for an elliptic curve public key.
 * <p>
 * This enumeration is used to indicate how an elliptic curve point (representing a
 * public key) is compressed.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code EC_PUBLIC_KEY_TYPE_UNCOMPRESSED}: The public key is uncompressed.</li>
 *   <li>{@code EC_PUBLIC_KEY_TYPE_X9_62_COMPRESSED_PRIME}: The public key is compressed using
 *   the X9.62 prime curve format.</li>
 *   <li>{@code EC_PUBLIC_KEY_TYPE_X9_62_COMPRESSED_CHAR2}: The public key is compressed using
 *   the X9.62 characteristic-2 curve format.</li>
 *   <li>{@code EC_PUBLIC_KEY_TYPE_X9_62_HYBRID}: The public key uses the X9.62 hybrid format.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see CryptographicAlgorithm
 */
@Data
@Builder(toBuilder = true)
public class KeyCompressionType implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.KEY_COMPRESSION_TYPE.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyCompressionType.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), KeyCompressionType::fromName,
          KeyCompressionType::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private KeyCompressionType(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link KeyCompressionType} instance wrapping the given value.
   */
  public static KeyCompressionType of(@NonNull Value value) {
    return new KeyCompressionType(value);
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
            String.format("No KeyCompressionType value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No KeyCompressionType value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for KeyCompressionType is not supported for KMIP spec %s",
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
   * The standard enumeration of Key Compression Types.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    EC_PUBLIC_KEY_TYPE_UNCOMPRESSED(0x00000001, "EcPublicKeyTypeUncompressed",
        KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    EC_PUBLIC_KEY_TYPE_X9_62_COMPRESSED_PRIME(0x00000002, "EcPublicKeyTypeX962CompressedPrime",
        KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    EC_PUBLIC_KEY_TYPE_X9_62_COMPRESSED_CHAR2(0x00000003, "EcPublicKeyTypeX962CompressedChar2",
        KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    EC_PUBLIC_KEY_TYPE_X9_62_HYBRID(0x00000004, "EcPublicKeyTypeX962Hybrid",
        KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

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
    public KeyCompressionType inst() {
      return KeyCompressionType.of(this);
    }
  }

  /**
   * An interface representing a Key Compression Type value, which can be either a standard
   * value or a custom extension.
   */
  public interface Value extends KmipEnumeration.Value<KeyCompressionType> {
  }

  /**
   * Represents a custom, vendor-specific Key Compression Type.
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
    public KeyCompressionType inst() {
      return KeyCompressionType.of(this);
    }
  }
}
