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
 * algorithm used by a Random Number Generator (RNG).
 * <p>
 * This enumeration is used in the {@code RNGParameters} structure to indicate the
 * specific algorithm employed for generating random numbers.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code UNSPECIFIED}: The RNG algorithm is not specified.</li>
 *   <li>{@code FIPS_186_2}: FIPS 186-2 compliant RNG.</li>
 *   <li>{@code DRBG}: Deterministic Random Bit Generator.</li>
 *   <li>{@code NRBG}: Non-Deterministic Random Bit Generator.</li>
 *   <li>{@code ANSI_X9_31}: ANSI X9.31 compliant RNG.</li>
 *   <li>{@code ANSI_X9_62}: ANSI X9.62 compliant RNG.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see DrbgAlgorithm
 * @see org.purplebean.kmip.model.core.structure.RngParameters
 */
@Data
@Builder(toBuilder = true)
public class RngAlgorithm implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.RNG_ALGORITHM.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RngAlgorithm.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), RngAlgorithm::fromName,
          RngAlgorithm::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private RngAlgorithm(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link RngAlgorithm} instance wrapping the given value.
   */
  public static RngAlgorithm of(@NonNull Value value) {
    return new RngAlgorithm(value);
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
            String.format("No RngAlgorithm value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No RngAlgorithm value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for RngAlgorithm is not supported for KMIP spec %s",
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
   * The standard enumeration of RNG Algorithms.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    UNSPECIFIED(0x00000001, "Unspecified", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    FIPS_186_2(0x00000002, "FIPS186_2", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DRBG(0x00000003, "DRBG", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    NRBG(0x00000004, "NRBG", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    ANSI_X9_31(0x00000005, "ANSIX9_31", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    ANSI_X9_62(0x00000006, "ANSIX9_62", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

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
    public RngAlgorithm inst() {
      return RngAlgorithm.of(this);
    }
  }

  /**
   * An interface representing an RNG Algorithm value, which can be either a standard
   * value or a custom extension.
   */
  public interface Value extends KmipEnumeration.Value<RngAlgorithm> {
  }

  /**
   * Represents a custom, vendor-specific RNG Algorithm.
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
    public RngAlgorithm inst() {
      return RngAlgorithm.of(this);
    }
  }
}
