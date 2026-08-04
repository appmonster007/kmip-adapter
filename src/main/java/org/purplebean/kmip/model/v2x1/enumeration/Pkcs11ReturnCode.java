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
 * KMIP Pkcs11ReturnCode enumeration.
 */
@Data
@Builder(toBuilder = true)
public class Pkcs11ReturnCode implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.PKCS_11_RETURN_CODE.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs11ReturnCode.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), Pkcs11ReturnCode::fromName,
          Pkcs11ReturnCode::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private Pkcs11ReturnCode(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link Pkcs11ReturnCode} instance wrapping the given value.
   */
  public static Pkcs11ReturnCode of(@NonNull Value value) {
    return new Pkcs11ReturnCode(value);
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
            String.format("No Pkcs11ReturnCode value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No Pkcs11ReturnCode value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for Pkcs11ReturnCode is not supported for KMIP spec %s",
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
    OK(0x00000000, "OK", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    CANCEL(0x00000001, "CANCEL", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    HOST_MEMORY(0x00000002, "HOST_MEMORY", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SLOT_ID_INVALID(0x00000003, "SLOT_ID_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GENERAL_ERROR(0x00000005, "GENERAL_ERROR", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    FUNCTION_FAILED(0x00000006, "FUNCTION_FAILED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ARGUMENTS_BAD(0x00000007, "ARGUMENTS_BAD", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    NO_EVENT(0x00000008, "NO_EVENT", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    NEED_TO_CREATE_THREADS(0x00000009, "NEED_TO_CREATE_THREADS", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    CANT_LOCK(0x0000000A, "CANT_LOCK", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    ATTRIBUTE_READ_ONLY(0x00000010, "ATTRIBUTE_READ_ONLY", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ATTRIBUTE_SENSITIVE(0x00000011, "ATTRIBUTE_SENSITIVE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ATTRIBUTE_TYPE_INVALID(0x00000012, "ATTRIBUTE_TYPE_INVALID", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ATTRIBUTE_VALUE_INVALID(0x00000013, "ATTRIBUTE_VALUE_INVALID", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ACTION_PROHIBITED(0x0000001B, "ACTION_PROHIBITED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DATA_INVALID(0x00000020, "DATA_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DATA_LEN_RANGE(0x00000021, "DATA_LEN_RANGE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DEVICE_ERROR(0x00000030, "DEVICE_ERROR", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DEVICE_MEMORY(0x00000031, "DEVICE_MEMORY", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DEVICE_REMOVED(0x00000032, "DEVICE_REMOVED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ENCRYPTED_DATA_INVALID(0x00000040, "ENCRYPTED_DATA_INVALID", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ENCRYPTED_DATA_LEN_RANGE(0x00000041, "ENCRYPTED_DATA_LEN_RANGE", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    AEAD_DECRYPT_FAILED(0x00000042, "AEAD_DECRYPT_FAILED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    FUNCTION_CANCELED(0x00000050, "FUNCTION_CANCELED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    FUNCTION_NOT_PARALLEL(0x00000051, "FUNCTION_NOT_PARALLEL", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    FUNCTION_NOT_SUPPORTED(0x00000054, "FUNCTION_NOT_SUPPORTED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    KEY_HANDLE_INVALID(0x00000060, "KEY_HANDLE_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    KEY_SIZE_RANGE(0x00000062, "KEY_SIZE_RANGE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    KEY_TYPE_INCONSISTENT(0x00000063, "KEY_TYPE_INCONSISTENT", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    KEY_NOT_NEEDED(0x00000064, "KEY_NOT_NEEDED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    KEY_CHANGED(0x00000065, "KEY_CHANGED", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    KEY_NEEDED(0x00000066, "KEY_NEEDED", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    KEY_INDIGESTIBLE(0x00000067, "KEY_INDIGESTIBLE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    KEY_FUNCTION_NOT_PERMITTED(0x00000068, "KEY_FUNCTION_NOT_PERMITTED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    KEY_NOT_WRAPPABLE(0x00000069, "KEY_NOT_WRAPPABLE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    KEY_UNEXTRACTABLE(0x0000006A, "KEY_UNEXTRACTABLE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MECHANISM_INVALID(0x00000070, "MECHANISM_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MECHANISM_PARAM_INVALID(0x00000071, "MECHANISM_PARAM_INVALID", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    OBJECT_HANDLE_INVALID(0x00000082, "OBJECT_HANDLE_INVALID", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    OPERATION_ACTIVE(0x00000090, "OPERATION_ACTIVE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    OPERATION_NOT_INITIALIZED(0x00000091, "OPERATION_NOT_INITIALIZED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    PIN_INCORRECT(0x000000A0, "PIN_INCORRECT", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    PIN_INVALID(0x000000A1, "PIN_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    PIN_LEN_RANGE(0x000000A2, "PIN_LEN_RANGE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    PIN_EXPIRED(0x000000A3, "PIN_EXPIRED", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    PIN_LOCKED(0x000000A4, "PIN_LOCKED", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SESSION_CLOSED(0x000000B0, "SESSION_CLOSED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SESSION_COUNT(0x000000B1, "SESSION_COUNT", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SESSION_HANDLE_INVALID(0x000000B3, "SESSION_HANDLE_INVALID", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    SESSION_PARALLEL_NOT_SUPPORTED(0x000000B4, "SESSION_PARALLEL_NOT_SUPPORTED",
        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SESSION_READ_ONLY(0x000000B5, "SESSION_READ_ONLY", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SESSION_EXISTS(0x000000B6, "SESSION_EXISTS", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SESSION_READ_ONLY_EXISTS(0x000000B7, "SESSION_READ_ONLY_EXISTS", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    SESSION_READ_WRITE_SO_EXISTS(0x000000B8, "SESSION_READ_WRITE_SO_EXISTS",
        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGNATURE_INVALID(0x000000C0, "SIGNATURE_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SIGNATURE_LEN_RANGE(0x000000C1, "SIGNATURE_LEN_RANGE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    TEMPLATE_INCOMPLETE(0x000000D0, "TEMPLATE_INCOMPLETE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    TEMPLATE_INCONSISTENT(0x000000D1, "TEMPLATE_INCONSISTENT", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    TOKEN_NOT_PRESENT(0x000000E0, "TOKEN_NOT_PRESENT", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    TOKEN_NOT_RECOGNIZED(0x000000E1, "TOKEN_NOT_RECOGNIZED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    TOKEN_WRITE_PROTECTED(0x000000E2, "TOKEN_WRITE_PROTECTED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    UNWRAPPING_KEY_HANDLE_INVALID(0x000000F0, "UNWRAPPING_KEY_HANDLE_INVALID",
        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    UNWRAPPING_KEY_SIZE_RANGE(0x000000F1, "UNWRAPPING_KEY_SIZE_RANGE", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    UNWRAPPING_KEY_TYPE_INCONSISTENT(0x000000F2, "UNWRAPPING_KEY_TYPE_INCONSISTENT",
        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    USER_ALREADY_LOGGED_IN(0x00000100, "USER_ALREADY_LOGGED_IN", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    USER_NOT_LOGGED_IN(0x00000101, "USER_NOT_LOGGED_IN", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    USER_PIN_NOT_INITIALIZED(0x00000102, "USER_PIN_NOT_INITIALIZED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    USER_TYPE_INVALID(0x00000103, "USER_TYPE_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    USER_ANOTHER_ALREADY_LOGGED_IN(0x00000104, "USER_ANOTHER_ALREADY_LOGGED_IN",
        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    USER_TOO_MANY_TYPES(0x00000105, "USER_TOO_MANY_TYPES", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    WRAPPED_KEY_INVALID(0x00000110, "WRAPPED_KEY_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    WRAPPED_KEY_LEN_RANGE(0x00000112, "WRAPPED_KEY_LEN_RANGE", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    WRAPPING_KEY_HANDLE_INVALID(0x00000113, "WRAPPING_KEY_HANDLE_INVALID", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    WRAPPING_KEY_SIZE_RANGE(0x00000114, "WRAPPING_KEY_SIZE_RANGE", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    WRAPPING_KEY_TYPE_INCONSISTENT(0x00000115, "WRAPPING_KEY_TYPE_INCONSISTENT",
        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    RANDOM_SEED_NOT_SUPPORTED(0x00000120, "RANDOM_SEED_NOT_SUPPORTED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    RANDOM_NO_RNG(0x00000121, "RANDOM_NO_RNG", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DOMAIN_PARAMS_INVALID(0x00000130, "DOMAIN_PARAMS_INVALID", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    CURVE_NOT_SUPPORTED(0x00000140, "CURVE_NOT_SUPPORTED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    BUFFER_TOO_SMALL(0x00000150, "BUFFER_TOO_SMALL", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SAVED_STATE_INVALID(0x00000160, "SAVED_STATE_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    INFORMATION_SENSITIVE(0x00000170, "INFORMATION_SENSITIVE", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    STATE_UNSAVEABLE(0x00000180, "STATE_UNSAVEABLE", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CRYPTOKI_NOT_INITIALIZED(0x00000190, "CRYPTOKI_NOT_INITIALIZED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    CRYPTOKI_ALREADY_INITIALIZED(0x00000191, "CRYPTOKI_ALREADY_INITIALIZED",
        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    MUTEX_BAD(0x000001A0, "MUTEX_BAD", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    MUTEX_NOT_LOCKED(0x000001A1, "MUTEX_NOT_LOCKED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    NEW_PIN_MODE(0x000001B0, "NEW_PIN_MODE", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    NEXT_OTP(0x000001B1, "NEXT_OTP", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    EXCEEDED_MAX_ITERATIONS(0x000001B5, "EXCEEDED_MAX_ITERATIONS", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    FIPS_SELF_TEST_FAILED(0x000001B6, "FIPS_SELF_TEST_FAILED", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    LIBRARY_LOAD_FAILED(0x000001B7, "LIBRARY_LOAD_FAILED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    PIN_TOO_WEAK(0x000001B8, "PIN_TOO_WEAK", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    PUBLIC_KEY_INVALID(0x000001B9, "PUBLIC_KEY_INVALID", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    FUNCTION_REJECTED(0x00000200, "FUNCTION_REJECTED", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    TOKEN_RESOURCE_EXCEEDED(0x00000201, "TOKEN_RESOURCE_EXCEEDED", KmipSpec.UnknownVersion,
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
    public Pkcs11ReturnCode inst() {
      return Pkcs11ReturnCode.of(this);
    }
  }

  // ----- Value hierarchy -----

  /**
   * Represents a specific value of the enclosing enumeration.
   */
  public interface Value extends KmipEnumeration.Value<Pkcs11ReturnCode> {
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
    public Pkcs11ReturnCode inst() {
      return Pkcs11ReturnCode.of(this);
    }
  }
}