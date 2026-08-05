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
 * method used for client registration.
 *
 * <p>This enumeration is used in the server's capability information to indicate which
 * methods of client registration it supports.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code UNSPECIFIED}: The registration method is not specified.</li>
 *   <li>{@code SERVER_PRE_GENERATED}: The server pre-generates credentials for the client.</li>
 *   <li>{@code SERVER_ON_DEMAND}: The server generates credentials for the client on demand.</li>
 *   <li>{@code CLIENT_GENERATED}: The client generates its own credentials.</li>
 *   <li>{@code CLIENT_REGISTERED}: The client is already registered.</li>
 * </ul>
 *
 * @see KmipEnumeration
 */
@Data
@Builder(toBuilder = true)
public class ClientRegistrationMethod implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.CLIENT_REGISTRATION_METHOD.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ClientRegistrationMethod.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), ClientRegistrationMethod::fromName,
          ClientRegistrationMethod::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private ClientRegistrationMethod(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link ClientRegistrationMethod} instance wrapping the given value.
   */
  public static ClientRegistrationMethod of(@NonNull Value value) {
    return new ClientRegistrationMethod(value);
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
            String.format("No ClientRegistrationMethod value found for '%s' in KMIP spec %s", name,
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
            String.format("No ClientRegistrationMethod value found for %d in KMIP spec %s", value,
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
          String.format("Value '%s' for ClientRegistrationMethod is not supported for KMIP spec %s",
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

  @Override
  public String getDescription() {
    return value.getDescription();
  }

  /**
   * Returns whether this enumeration value is a custom vendor extension.
   */
  public boolean isCustom() {
    return value.isCustom();
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && value.isSupported();
  }

  @Override
  public int getIntValue() {
    return value.getValue();
  }

  /**
   * The standard enumeration of Client Registration Methods.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    UNSPECIFIED(0x00000001, "Unspecified", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SERVER_PRE_GENERATED(0x00000002, "ServerPreGenerated", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SERVER_ON_DEMAND(0x00000003, "ServerOnDemand", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CLIENT_GENERATED(0x00000004, "ClientGenerated", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CLIENT_REGISTERED(0x00000005, "ClientRegistered", KmipSpec.UnknownVersion, KmipSpec.V2_1,
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
    public ClientRegistrationMethod inst() {
      return ClientRegistrationMethod.of(this);
    }
  }

  /**
   * An interface representing a Client Registration Method value, which can be either a standard
   * value or a custom extension.
   */
  public interface Value extends KmipEnumeration.Value<ClientRegistrationMethod> {
  }

  /**
   * Represents a custom, vendor-specific Client Registration Method.
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
    public ClientRegistrationMethod inst() {
      return ClientRegistrationMethod.of(this);
    }
  }
}
