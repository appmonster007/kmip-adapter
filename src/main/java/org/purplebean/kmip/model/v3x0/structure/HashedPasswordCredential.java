package org.purplebean.kmip.model.v3x0.structure;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.CredentialValue;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v3x0.type.HashedPasswordUsername;
import org.purplebean.kmip.model.v3x0.type.HashedUsernamePassword;

/**
 * KMIP HashedPasswordCredential structure (KMIP v3.0).
 *
 * <p>Encodes a hashed-password credential per KMIP v3.0 spec.</p>
 *
 * <ul>
 *   <li>{@code cryptographicAlgorithm} — optional Enumeration (tag CRYPTOGRAPHIC_ALGORITHM
 *   0x420028)</li>
 *   <li>{@code hashedUsernamePassword}  — required ByteString (tag HASHED_USERNAME_PASSWORD
 *   0x4201B0)</li>
 *   <li>{@code hashedPasswordUsername}  — required ByteString (tag HASHED_PASSWORD_USERNAME
 *   0x4201B1)</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class HashedPasswordCredential implements CredentialValue, KmipStructure {

  public static final KmipTag kmipTag = KmipTag.Standard.HASHED_PASSWORD_CREDENTIAL.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, HashedPasswordCredential.class);
    }
  }

  private final CryptographicAlgorithm cryptographicAlgorithm;

  @NonNull
  private final HashedUsernamePassword hashedUsernamePassword;

  @NonNull
  private final HashedPasswordUsername hashedPasswordUsername;

  @Builder
  private HashedPasswordCredential(
      CryptographicAlgorithm cryptographicAlgorithm,
      @NonNull HashedUsernamePassword hashedUsernamePassword,
      @NonNull HashedPasswordUsername hashedPasswordUsername) {
    this.cryptographicAlgorithm = cryptographicAlgorithm;
    this.hashedUsernamePassword = hashedUsernamePassword;
    this.hashedPasswordUsername = hashedPasswordUsername;
    validate();
  }

  /**
   * Returns the {@link HashedPasswordCredential} instance wrapping the given value.
   */
  public static HashedPasswordCredential of(
      CryptographicAlgorithm cryptographicAlgorithm,
      @NonNull HashedUsernamePassword hashedUsernamePassword,
      @NonNull HashedPasswordUsername hashedPasswordUsername) {
    return HashedPasswordCredential
        .builder()
        .cryptographicAlgorithm(cryptographicAlgorithm)
        .hashedUsernamePassword(hashedUsernamePassword)
        .hashedPasswordUsername(hashedPasswordUsername)
        .build();
  }

  /**
   * Returns the {@link HashedPasswordCredential} instance wrapping the given value.
   */
  public static HashedPasswordCredential of(
      @NonNull HashedUsernamePassword hashedUsernamePassword,
      @NonNull HashedPasswordUsername hashedPasswordUsername) {
    return HashedPasswordCredential
        .builder()
        .hashedUsernamePassword(hashedUsernamePassword)
        .hashedPasswordUsername(hashedPasswordUsername)
        .build();
  }

  private void validate() {
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
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(cryptographicAlgorithm, hashedUsernamePassword, hashedPasswordUsername)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
