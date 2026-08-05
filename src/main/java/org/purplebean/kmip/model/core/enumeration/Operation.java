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
 * cryptographic or administrative operation to be performed.
 *
 * <p>This enumeration lists all the standard operations defined in the KMIP specifications,
 * such as creating keys, encrypting data, and managing attributes.
 *
 * @see KmipEnumeration
 */
@Data
@Builder(toBuilder = true)
public class Operation implements KmipEnumeration {
  public static final KmipTag kmipTag = KmipTag.Standard.OPERATION.inst();
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
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Operation.class);
      KmipEnumeration.register(spec, kmipTag.getValue(), Operation::fromName, Operation::fromValue);
    }
  }

  @NonNull
  private final Value value;

  @Builder
  private Operation(@NonNull Value value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link Operation} instance wrapping the given value.
   */
  public static Operation of(@NonNull Value value) {
    return new Operation(value);
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
            String.format("No Operation value found for '%s' in KMIP spec %s", name, spec)
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
            String.format("No Operation value found for %d in KMIP spec %s", value, spec)
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
          String.format("Value '%s' for Operation is not supported for KMIP spec %s",
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
   * The standard enumeration of Operations.
   */
  @Getter
  @AllArgsConstructor
  @ToString
  public enum Standard implements Value {
    CREATE(0x00000001, "Create", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CREATE_KEY_PAIR(0x00000002, "CreateKeyPair", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    REGISTER(0x00000003, "Register", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    RE_KEY(0x00000004, "ReKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DERIVE_KEY(0x00000005, "DeriveKey", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CERTIFY(0x00000006, "Certify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    RE_CERTIFY(0x00000007, "ReCertify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    LOCATE(0x00000008, "Locate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CHECK(0x00000009, "Check", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GET(0x0000000A, "Get", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    GET_ATTRIBUTES(0x0000000B, "GetAttributes", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    GET_ATTRIBUTE_LIST(0x0000000C, "GetAttributeList", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ADD_ATTRIBUTE(0x0000000D, "AddAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    MODIFY_ATTRIBUTE(0x0000000E, "ModifyAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    DELETE_ATTRIBUTE(0x0000000F, "DeleteAttribute", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    OBTAIN_LEASE(0x00000010, "ObtainLease", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GET_USAGE_ALLOCATION(0x00000011, "GetUsageAllocation", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ACTIVATE(0x00000012, "Activate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    REVOKE(0x00000013, "Revoke", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DESTROY(0x00000014, "Destroy", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ARCHIVE(0x00000015, "Archive", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    RECOVER(0x00000016, "Recover", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    VALIDATE(0x00000017, "Validate", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    QUERY(0x00000018, "Query", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    CANCEL(0x00000019, "Cancel", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    POLL(0x0000001A, "Poll", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    NOTIFY(0x0000001B, "Notify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    PUT(0x0000001C, "Put", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    RE_KEY_KEY_PAIR(0x0000001D, "ReKeyKeyPair", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    DISCOVER_VERSIONS(0x0000001E, "DiscoverVersions", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    ENCRYPT(0x0000001F, "Encrypt", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    DECRYPT(0x00000020, "Decrypt", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SIGN(0x00000021, "Sign", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    SIGNATURE_VERIFY(0x00000022, "SignatureVerify", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    MAC(0x00000023, "MAC", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    MAC_VERIFY(0x00000024, "MACVerify", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    RNG_RETRIEVE(0x00000025, "RNGRetrieve", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    RNG_SEED(0x00000026, "RNGSeed", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1,
        KmipSpec.V3_0),
    HASH(0x00000027, "Hash", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
    CREATE_SPLIT_KEY(0x00000028, "CreateSplitKey", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    JOIN_SPLIT_KEY(0x00000029, "JoinSplitKey", KmipSpec.UnknownVersion, KmipSpec.V1_2,
        KmipSpec.V2_1, KmipSpec.V3_0),
    IMPORT(0x0000002A, "Import", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    EXPORT(0x0000002B, "Export", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    LOG(0x0000002C, "Log", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    LOGIN(0x0000002D, "Login", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    LOGOUT(0x0000002E, "Logout", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    DELEGATED_LOGIN(0x0000002F, "DelegatedLogin", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    ADJUST_ATTRIBUTE(0x00000030, "AdjustAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SET_ATTRIBUTE(0x00000031, "SetAttribute", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    SET_ENDPOINT_ROLE(0x00000032, "SetEndpointRole", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    PKCS_11(0x00000033, "PKCS_11", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    INTEROP(0x00000034, "Interop", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    RE_PROVISION(0x00000035, "ReProvision", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SET_DEFAULTS(0x00000036, "SetDefaults", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    SET_CONSTRAINTS(0x00000037, "SetConstraints", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    GET_CONSTRAINTS(0x00000038, "GetConstraints", KmipSpec.UnknownVersion, KmipSpec.V2_1,
        KmipSpec.V3_0),
    QUERY_ASYNCHRONOUS_REQUESTS(0x00000039, "QueryAsynchronousRequests", KmipSpec.UnknownVersion,
        KmipSpec.V2_1, KmipSpec.V3_0),
    PROCESS(0x0000003A, "Process", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    PING(0x0000003B, "Ping", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
    CREATE_GROUP(0x0000003C, "CreateGroup", KmipSpec.UnknownVersion, KmipSpec.V3_0),
    OBLITERATE(0x0000003D, "Obliterate", KmipSpec.UnknownVersion, KmipSpec.V3_0),
    CREATE_USER(0x0000003E, "CreateUser", KmipSpec.UnknownVersion, KmipSpec.V3_0),
    CREATE_CREDENTIAL(0x0000003F, "CreateCredential", KmipSpec.UnknownVersion, KmipSpec.V3_0),
    DEACTIVATE(0x00000040, "Deactivate", KmipSpec.UnknownVersion, KmipSpec.V3_0);

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
    public Operation inst() {
      return Operation.of(this);
    }
  }

  /**
   * An interface representing an Operation value, which can be either a standard
   * value or a custom extension.
   */
  public interface Value extends KmipEnumeration.Value<Operation> {
  }

  /**
   * Represents a custom, vendor-specific Operation.
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
    public Operation inst() {
      return Operation.of(this);
    }
  }
}