package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;

/**
 * KMIP DelegatedLogin Response Payload (V2.1+, §6.1.12).
 *
 * <p>Fields:
 * <ul>
 *   <li>Ticket — Required — the delegated ticket returned to the requester</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class DelegatedLoginOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.DELEGATED_LOGIN;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          DelegatedLoginOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, DelegatedLoginOpResponsePayload.class,
          DelegatedLoginOpResponsePayload::of);
    }
  }

  @NonNull
  private final Ticket ticket;

  @Builder
  private DelegatedLoginOpResponsePayload(@NonNull Ticket ticket) {
    this.ticket = ticket;
    validate();
  }

  public static DelegatedLoginOpResponsePayload of(List<KmipDataType> values) {
    var builder = DelegatedLoginOpResponsePayload.builder();
    values.forEach(value -> {
      if (value instanceof Ticket) {
        builder.ticket((Ticket) value);
      }
    });
    return builder.build();
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
        .of(ticket)
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
