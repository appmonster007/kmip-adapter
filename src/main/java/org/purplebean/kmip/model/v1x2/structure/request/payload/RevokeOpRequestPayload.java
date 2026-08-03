package org.purplebean.kmip.model.v1x2.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP RevokeOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class RevokeOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.REVOKE;
  private static final Set<KmipSpec> supportedVersions = Set.of(
      KmipSpec.UnknownVersion,
      KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4,
      KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RevokeOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, RevokeOpRequestPayload.class,
          RevokeOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  @NonNull
  private final RevocationReason revocationReason;

  private final CompromiseOccurrenceDate compromiseOccurrenceDate;

  @Builder
  private RevokeOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      @NonNull RevocationReason revocationReason,
      CompromiseOccurrenceDate compromiseOccurrenceDate
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.revocationReason = revocationReason;
    this.compromiseOccurrenceDate = compromiseOccurrenceDate;
    validate();
  }

  /**
   * Returns the {@link RevokeOpRequestPayload} instance wrapping the given value.
   */
  public static RevokeOpRequestPayload of(List<KmipDataType> values) {
    var builder = RevokeOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(RevocationReason.kmipTag)) {
      builder.revocationReason((RevocationReason) map
          .get(RevocationReason.kmipTag)
          .getFirst());
    }
    if (map.containsKey(CompromiseOccurrenceDate.kmipTag)) {
      builder.compromiseOccurrenceDate((CompromiseOccurrenceDate) map
          .get(CompromiseOccurrenceDate.kmipTag)
          .getFirst());
    }
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
        .of(
            uniqueIdentifier,
            revocationReason,
            compromiseOccurrenceDate)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}