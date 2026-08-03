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

/**
 * KMIP Data Enumeration (OASIS kmip-spec-v2.0 §11.13, Table 406 / v2.1 Table 406 / v3.0).
 *
 * <p>Discriminates the kind of data carried in operation payloads (Encrypt, Decrypt, Hash,
 * MAC, RNG Retrieve, Sign, Signature Verify). Shares KMIP tag {@code 0x4200C2} with
 * {@link org.purplebean.kmip.model.core.type.DataByteString}; the two are distinguished by
 * encoding type — {@code Enumeration} (this class) versus {@code ByteString} (DataByteString).</p>
 *
 * <p><b>Java-naming note:</b> the spec name is just "Data" but the bare identifier
 * collides with {@link lombok.Data}. We append {@code Enumeration} to disambiguate;
 * the {@code DataByteString} sibling uses {@code ByteString} suffix for the same reason.</p>
 */
@Data
@Builder(toBuilder = true)
public class DataEnumeration implements KmipEnumeration, org.purplebean.kmip.api.DataValue {
  public static final KmipTag kmipTag = KmipTag.Standard.DATA.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, DataEnumeration.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), DataEnumeration::fromName,
          DataEnumeration::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private DataEnumeration(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link DataEnumeration} instance wrapping the given value.
   */
  public static DataEnumeration of(@NonNull Value value) {
    return new DataEnumeration(value);
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
            String.format("No DataEnumeration value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No DataEnumeration value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for DataEnumeration is not supported for KMIP spec %s",
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
   * Standard KMIP-defined values.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    DECRYPT(0x00000001, "Decrypt", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    ENCRYPT(0x00000002, "Encrypt", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    HASH(0x00000003, "Hash", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    MAC_MAC_DATA(0x00000004, "MACMACData", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    RNG_RETRIEVE(0x00000005, "RNGRetrieve", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGN_SIGNATURE_DATA(0x00000006, "SignSignatureData", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SIGNATURE_VERIFY(0x00000007, "SignatureVerify", KmipSpec.UnknownVersion, KmipSpec.V2_1,
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
    public DataEnumeration inst() {
      return DataEnumeration.of(this);
    }
  }

  // ----- Value hierarchy -----

  /**
   * Represents a specific value of the enclosing enumeration.
   */
  public interface Value extends KmipEnumeration.Value<DataEnumeration> {
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
    public DataEnumeration inst() {
      return DataEnumeration.of(this);
    }
  }
}