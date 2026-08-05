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
 * lifecycle state of a managed object.
 *
 * <p>This enumeration is a fundamental attribute of every managed object, defining its
 * current status in the key lifecycle.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code PRE_ACTIVE}: The object has been created but is not yet active.</li>
 *   <li>{@code ACTIVE}: The object is active and can be used for cryptographic operations.</li>
 *   <li>{@code DEACTIVATED}: The object has been deactivated and can no longer be used for new
 *   operations.</li>
 *   <li>{@code COMPROMISED}: The object has been compromised and should not be used.</li>
 *   <li>{@code DESTROYED}: The object has been destroyed and is no longer recoverable.</li>
 *   <li>{@code DESTROYED_COMPROMISED}: The object was compromised and has been destroyed.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see KmipAttribute
 */
@SuppressWarnings({"checkstyle:Indentation", "checkstyle:NeedBraces", "checkstyle:LineLength"})
@Data
@Builder(toBuilder = true)
public class State implements KmipEnumeration, KmipAttribute {
  @SuppressWarnings("checkstyle:Indentation")
  public static final KmipTag kmipTag = KmipTag.Standard.STATE.inst();
  @SuppressWarnings("checkstyle:Indentation")
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);
  @SuppressWarnings("checkstyle:Indentation")
  private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
  @SuppressWarnings("checkstyle:Indentation")
  private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
  @SuppressWarnings("checkstyle:Indentation")
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, State.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, State.class, State::of);
      KmipEnumeration.register(spec, kmipTag.getValue(), State::fromName, State::fromValue);
    }
  }

  @SuppressWarnings("checkstyle:Indentation")
  @NonNull
  private final Value value;

  @SuppressWarnings("checkstyle:Indentation")
  @Builder
  private State(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link State} instance wrapping the given value.
   */
  @SuppressWarnings("checkstyle:Indentation")
  public static State of(@NonNull Value value) {
    return new State(value);
  }

  /**
   * Returns the {@link State} instance wrapping the given value.
   */
  @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength"})
  public static State of(@NonNull AttributeName attributeName,
                         @NonNull AttributeValue attributeValue) {
    if (!attributeName
        .getValue()
        .equals(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()))) {
      throw new IllegalArgumentException("Invalid attribute name");
    }
    if (attributeValue.getEncodingType() != encodingType
        || !(attributeValue.getValue() instanceof KmipEnumeration.Value<?> enumeration)) {
      throw new IllegalArgumentException("Invalid encoding type");
    }
    State.Value v = fromValue(enumeration.getValue());
    return new State(v);
  }

  @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength"})
  private static void checkValidExtensionValue(int value) {
    int extensionStart = 0x80000000;
    if (value < extensionStart || value > 0) {
      throw new IllegalArgumentException(
          String.format("Extension value %d must be in range 8XXXXXXX (hex)", value));
    }
  }

  /**
   * Register an extension value.
   */
  @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength"})
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
  @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength"})
  public static Value fromName(String name) {
    final String nameLowerCase = name.toLowerCase(Locale.ROOT);
    KmipSpec spec = KmipContext.getSpec();
    Value v = DESCRIPTION_REGISTRY.get(nameLowerCase);
    return Optional
        .ofNullable(v)
        .filter(Value::isSupported)
        .orElseThrow(() -> new NoSuchElementException(
            String.format("No State value found for '%s' in KMIP spec %s", name, spec)));
  }

  /**
   * Look up by value.
   */
  @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength"})
  public static Value fromValue(int value) {
    KmipSpec spec = KmipContext.getSpec();
    Value v = VALUE_REGISTRY.get(value);
    return Optional
        .ofNullable(v)
        .filter(Value::isSupported)
        .orElseThrow(() -> new NoSuchElementException(
            String.format("No State value found for %d in KMIP spec %s", value, spec)));
  }

  /**
   * Get registered values.
   */
  @SuppressWarnings("checkstyle:Indentation")
  public static Collection<Value> registeredValues() {
    return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
  }

  @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength"})
  private void validate() {
    // KMIP spec compatibility validation
    KmipSpec spec = KmipContext.getSpec();
    if (!value.isSupported()) {
      throw new IllegalArgumentException(
          String.format("Value '%s' for State is not supported for KMIP spec %s",
              value.getDescription(), spec));
    }
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public KmipTag getKmipTag() {
    return kmipTag;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public EncodingType getEncodingType() {
    return encodingType;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public String getDescription() {
    return value.getDescription();
  }

  /**
   * Returns whether this enumeration value is a custom vendor extension.
   */
  @SuppressWarnings("checkstyle:Indentation")
  public boolean isCustom() {
    return value.isCustom();
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && value.isSupported();
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public boolean isAlwaysPresent() {
    return true;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public boolean isServerInitializable() {
    return true;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public boolean isClientInitializable() {
    return false;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public boolean isServerModifiable(State state) {
    return true;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public boolean isClientModifiable(State state) {
    return false;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public boolean isClientDeletable() {
    return false;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public boolean isMultiInstanceAllowed() {
    return false;
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofEnumeration(value);
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public AttributeName getAttributeName() {
    return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public String getCanonicalName() {
    return kmipTag.getDescription();
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public int getIntValue() {
    return value.getValue();
  }

  /**
   * The standard enumeration of States.
   */
  @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength"})
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    PRE_ACTIVE(0x00000001, "PreActive", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ACTIVE(0x00000002, "Active", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DEACTIVATED(0x00000003, "Deactivated", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    COMPROMISED(0x00000004, "Compromised", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DESTROYED(0x00000005, "Destroyed", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DESTROYED_COMPROMISED(0x00000006, "DestroyedCompromised", KmipSpec.UnknownVersion,
        KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

    @SuppressWarnings("checkstyle:Indentation")
    private final int value;
    @SuppressWarnings("checkstyle:Indentation")
    private final String description;
    @SuppressWarnings("checkstyle:Indentation")
    private final Set<KmipSpec> supportedVersions;

    @SuppressWarnings("checkstyle:Indentation")
    private final boolean custom = false;

    @SuppressWarnings("checkstyle:Indentation")
    Standard(int value, String description, KmipSpec... supportedVersions) {
      this.value = value;
      this.description = description;
      this.supportedVersions = Set.of(supportedVersions);
    }

    @SuppressWarnings("checkstyle:Indentation")
    @Override
    public boolean isSupported() {
      KmipSpec spec = KmipContext.getSpec();
      return supportedVersions.contains(spec);
    }

    @SuppressWarnings("checkstyle:Indentation")
    @Override
    public State inst() {
      return State.of(this);
    }
  }

  /**
   * An interface representing a State value, which can be either a standard
   * value or a custom extension.
   */
  @SuppressWarnings("checkstyle:Indentation")
  public interface Value extends KmipEnumeration.Value<State> {
  }

  /**
   * Represents a custom, vendor-specific State.
   */
  @SuppressWarnings("checkstyle:Indentation")
  @Getter
  @AllArgsConstructor
  @ToString
  public static class Extension implements Value {
    @SuppressWarnings("checkstyle:Indentation")
    private final int value;
    @SuppressWarnings("checkstyle:Indentation")
    private final String description;
    @SuppressWarnings("checkstyle:Indentation")
    private final Set<KmipSpec> supportedVersions;

    @SuppressWarnings("checkstyle:Indentation")
    private final boolean custom = true;

    /**
     * Constructs a custom vendor extension value.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public Extension(int value, String description, KmipSpec... supportedVersions) {
      this.value = value;
      this.description = description;
      this.supportedVersions = Set.of(supportedVersions);
    }

    @SuppressWarnings("checkstyle:Indentation")
    @Override
    public boolean isSupported() {
      KmipSpec spec = KmipContext.getSpec();
      return supportedVersions.contains(spec);
    }

    @SuppressWarnings("checkstyle:Indentation")
    @Override
    public State inst() {
      return State.of(this);
    }
  }
}
