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
 * KMIP Pkcs11Function enumeration.
 */
@Data
@Builder(toBuilder = true)
public class Pkcs11Function implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.PKCS_11_FUNCTION.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs11Function.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), Pkcs11Function::fromName,
          Pkcs11Function::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private Pkcs11Function(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link Pkcs11Function} instance wrapping the given value.
   */
  public static Pkcs11Function of(@NonNull Value value) {
    return new Pkcs11Function(value);
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
            String.format("No Pkcs11Function value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No Pkcs11Function value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for Pkcs11Function is not supported for KMIP spec %s",
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
   * Standard KMIP-defined values.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    INITIALIZE(1, "C_Initialize", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    FINALIZE(2, "C_Finalize", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_INFO(3, "C_GetInfo", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_FUNCTION_LIST(4, "C_GetFunctionList", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GET_SLOT_LIST(5, "C_GetSlotList", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_SLOT_INFO(6, "C_GetSlotInfo", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_TOKEN_INFO(7, "C_GetTokenInfo", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_MECHANISM_LIST(8, "C_GetMechanismList", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GET_MECHANISM_INFO(9, "C_GetMechanismInfo", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    INIT_TOKEN(10, "C_InitToken", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    INIT_PIN(11, "C_InitPIN", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SET_PIN(12, "C_SetPIN", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    OPEN_SESSION(13, "C_OpenSession", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    CLOSE_SESSION(14, "C_CloseSession", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    CLOSE_ALL_SESSIONS(15, "C_CloseAllSessions", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GET_SESSION_INFO(16, "C_GetSessionInfo", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_OPERATION_STATE(17, "C_GetOperationState", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SET_OPERATION_STATE(18, "C_SetOperationState", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    LOGIN(19, "C_Login", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    LOGOUT(20, "C_Logout", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    CREATE_OBJECT(21, "C_CreateObject", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    COPY_OBJECT(22, "C_CopyObject", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DESTROY_OBJECT(23, "C_DestroyObject", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_OBJECT_SIZE(24, "C_GetObjectSize", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_ATTRIBUTE_VALUE(25, "C_GetAttributeValue", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SET_ATTRIBUTE_VALUE(26, "C_SetAttributeValue", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    FIND_OBJECTS_INIT(27, "C_FindObjectsInit", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    FIND_OBJECTS(28, "C_FindObjects", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    FIND_OBJECTS_FINAL(29, "C_FindObjectsFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ENCRYPT_INIT(30, "C_EncryptInit", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    ENCRYPT(31, "C_Encrypt", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    ENCRYPT_UPDATE(32, "C_EncryptUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    ENCRYPT_FINAL(33, "C_EncryptFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DECRYPT_INIT(34, "C_DecryptInit", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DECRYPT(35, "C_Decrypt", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DECRYPT_UPDATE(36, "C_DecryptUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DECRYPT_FINAL(37, "C_DecryptFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DIGEST_INIT(38, "C_DigestInit", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DIGEST(39, "C_Digest", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DIGEST_UPDATE(40, "C_DigestUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DIGEST_KEY(41, "C_DigestKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DIGEST_FINAL(42, "C_DigestFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGN_INIT(43, "C_SignInit", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGN(44, "C_Sign", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGN_UPDATE(45, "C_SignUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGN_FINAL(46, "C_SignFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGN_RECOVER_INIT(47, "C_SignRecoverInit", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SIGN_RECOVER(48, "C_SignRecover", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    VERIFY_INIT(49, "C_VerifyInit", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    VERIFY(50, "C_Verify", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    VERIFY_UPDATE(51, "C_VerifyUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    VERIFY_FINAL(52, "C_VerifyFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    VERIFY_RECOVER_INIT(53, "C_VerifyRecoverInit", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    VERIFY_RECOVER(54, "C_VerifyRecover", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DIGEST_ENCRYPT_UPDATE(55, "C_DigestEncryptUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DECRYPT_DIGEST_UPDATE(56, "C_DecryptDigestUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SIGN_ENCRYPT_UPDATE(57, "C_SignEncryptUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DECRYPT_VERIFY_UPDATE(58, "C_DecryptVerifyUpdate", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GENERATE_KEY(59, "C_GenerateKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GENERATE_KEY_PAIR(60, "C_GenerateKeyPair", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    WRAP_KEY(61, "C_WrapKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    UNWRAP_KEY(62, "C_UnwrapKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DERIVE_KEY(63, "C_DeriveKey", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SEED_RANDOM(64, "C_SeedRandom", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GENERATE_RANDOM(65, "C_GenerateRandom", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_FUNCTION_STATUS(66, "C_GetFunctionStatus", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CANCEL_FUNCTION(67, "C_CancelFunction", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    WAIT_FOR_SLOT_EVENT(68, "C_WaitForSlotEvent", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GET_INTERFACE_LIST(69, "C_GetInterfaceList", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GET_INTERFACE(70, "C_GetInterface", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    LOGIN_USER(71, "C_LoginUser", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SESSION_CANCEL(72, "C_SessionCancel", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    MESSAGE_ENCRYPT_INIT(73, "C_MessageEncryptInit", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ENCRYPT_MESSAGE(74, "C_EncryptMessage", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    ENCRYPT_MESSAGE_BEGIN(75, "C_EncryptMessageBegin", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ENCRYPT_MESSAGE_NEXT(76, "C_EncryptMessageNext", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MESSAGE_ENCRYPT_FINAL(77, "C_MessageEncryptFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MESSAGE_DECRYPT_INIT(78, "C_MessageDecryptInit", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DECRYPT_MESSAGE(79, "C_DecryptMessage", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DECRYPT_MESSAGE_BEGIN(80, "C_DecryptMessageBegin", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DECRYPT_MESSAGE_NEXT(81, "C_DecryptMessageNext", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MESSAGE_DECRYPT_FINAL(82, "C_MessageDecryptFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MESSAGE_SIGN_INIT(83, "C_MessageSignInit", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SIGN_MESSAGE(84, "C_SignMessage", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGN_MESSAGE_BEGIN(85, "C_SignMessageBegin", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SIGN_MESSAGE_NEXT(86, "C_SignMessageNext", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MESSAGE_SIGN_FINAL(87, "C_MessageSignFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MESSAGE_VERIFY_INIT(88, "C_MessageVerifyInit", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    VERIFY_MESSAGE(89, "C_VerifyMessage", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    VERIFY_MESSAGE_BEGIN(90, "C_VerifyMessageBegin", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    VERIFY_MESSAGE_NEXT(91, "C_VerifyMessageNext", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MESSAGE_VERIFY_FINAL(92, "C_MessageVerifyFinal", KmipSpec.UnknownVersion, KmipSpec.V2_1,
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
    public Pkcs11Function inst() {
      return Pkcs11Function.of(this);
    }
  }

  // ----- Value hierarchy -----

  /**
   * Represents a specific value of the enclosing enumeration.
   */
  public interface Value extends KmipEnumeration.Value<Pkcs11Function> {
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
    public Pkcs11Function inst() {
      return Pkcs11Function.of(this);
    }
  }
}