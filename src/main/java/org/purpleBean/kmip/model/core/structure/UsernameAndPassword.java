package org.purpleBean.kmip.model.core.structure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;

@Data
@Builder(toBuilder = true)
public class UsernameAndPassword implements CredentialValue, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, UsernameAndPassword.class);
      CredentialValue.register(spec, encodingType, CredentialType.Standard.USERNAME_AND_PASSWORD,
          UsernameAndPassword.class, UsernameAndPassword::of);
    }
  }

  @NonNull
  private final Username username;
  @NonNull
  private final Password password;

  @Builder
  private UsernameAndPassword(
      @NonNull Username username,
      @NonNull Password password

  ) {
    this.username = username;
    this.password = password;
    validate();
  }

  public static UsernameAndPassword of(@NonNull String username, @NonNull String password) {
    return of(Username.of(username), Password.of(password));
  }

  public static UsernameAndPassword of(@NonNull Username username, @NonNull Password password) {
    return UsernameAndPassword
        .builder()
        .username(username)
        .password(password)
        .build();
  }

  public static UsernameAndPassword of(KmipDataType... values) {
    return of(List.of(values));
  }

  public static UsernameAndPassword of(List<KmipDataType> values) {
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    UsernameAndPassword.UsernameAndPasswordBuilder builder = UsernameAndPassword.builder();
    if (map.containsKey(Username.kmipTag)) {
      builder.username((Username) map
          .get(Username.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Password.kmipTag)) {
      builder.password((Password) map
          .get(Password.kmipTag)
          .getFirst());
    }
    return builder.build();
  }

  public static UsernameAndPassword of(CredentialValue value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid credential value: " + value);
    }
    return of(structure.getValue());
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(username, "Username cannot be null");
    Objects.requireNonNull(password, "Password cannot be null");
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
        .of(username, password)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}