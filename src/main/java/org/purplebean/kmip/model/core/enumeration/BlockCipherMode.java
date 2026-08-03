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
 * mode of operation for a block cipher.
 * <p>
 * Block cipher modes define how a block cipher, which encrypts fixed-size blocks of
 * data, can be used to securely handle variable-length messages. This enumeration
 * includes common modes like CBC, CTR, and GCM.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code CBC}: Cipher Block Chaining</li>
 *   <li>{@code ECB}: Electronic Codebook</li>
 *   <li>{@code PCBC}: Propagating Cipher Block Chaining</li>
 *   <li>{@code CFB}: Cipher Feedback</li>
 *   <li>{@code OFB}: Output Feedback</li>
 *   <li>{@code CTR}: Counter</li>
 *   <li>{@code CMAC}: Cipher-based Message Authentication Code</li>
 *   <li>{@code CCM}: Counter with CBC-MAC</li>
 *   <li>{@code GCM}: Galois/Counter Mode</li>
 *   <li>{@code CBC_MAC}: Cipher Block Chaining Message Authentication Code</li>
 *   <li>{@code XTS}: XEX-based tweaked-codebook mode with ciphertext stealing</li>
 *   <li>{@code AES_KEY_WRAP_PADDING}: AES Key Wrap with Padding</li>
 *   <li>{@code NIST_KEY_WRAP}: NIST Key Wrap</li>
 *   <li>{@code X9_102_AESKW}: ANSI X9.102 AES Key Wrap</li>
 *   <li>{@code X9_102_TDKW}: ANSI X9.102 Triple-DES Key Wrap</li>
 *   <li>{@code X9_102_AKW1}: ANSI X9.102 AKW1</li>
 *   <li>{@code X9_102_AKW2}: ANSI X9.102 AKW2</li>
 *   <li>{@code AEAD}: Authenticated Encryption with Associated Data</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see CryptographicAlgorithm
 */
@Data
@Builder(toBuilder = true)
public class BlockCipherMode implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.BLOCK_CIPHER_MODE.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, BlockCipherMode.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), BlockCipherMode::fromName,
          BlockCipherMode::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private BlockCipherMode(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link BlockCipherMode} instance wrapping the given value.
   */
  public static BlockCipherMode of(@NonNull Value value) {
    return new BlockCipherMode(value);
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
            String.format("No BlockCipherMode value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No BlockCipherMode value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for BlockCipherMode is not supported for KMIP spec %s",
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
   * The standard enumeration of Block Cipher Modes.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    CBC(0x00000001, "CBC", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    ECB(0x00000002, "ECB", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    PCBC(0x00000003, "PCBC", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    CFB(0x00000004, "CFB", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    OFB(0x00000005, "OFB", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    CTR(0x00000006, "CTR", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    CMAC(0x00000007, "CMAC", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    CCM(0x00000008, "CCM", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    GCM(0x00000009, "GCM", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    CBC_MAC(0x0000000A, "CBCMAC", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    XTS(0x0000000B, "XTS", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    AES_KEY_WRAP_PADDING(0x0000000C, "AESKeyWrapPadding", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    NIST_KEY_WRAP(0x0000000D, "NISTKeyWrap", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    X9_102_AESKW(0x0000000E, "X9102AESKW", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    X9_102_TDKW(0x0000000F, "X9102TDKW", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    X9_102_AKW1(0x00000010, "X9102AKW1", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    X9_102_AKW2(0x00000011, "X9102AKW2", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    AEAD(0x00000012, "AEAD", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
    public BlockCipherMode inst() {
      return BlockCipherMode.of(this);
    }
  }

  /**
   * An interface representing a Block Cipher Mode value, which can be either a standard
   * value or a custom extension.
   */
  public interface Value extends KmipEnumeration.Value<BlockCipherMode> {
  }

  /**
   * Represents a custom, vendor-specific Block Cipher Mode.
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
    public BlockCipherMode inst() {
      return BlockCipherMode.of(this);
    }
  }
}
