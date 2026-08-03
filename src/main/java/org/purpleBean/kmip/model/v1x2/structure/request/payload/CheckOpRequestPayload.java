package org.purplebean.kmip.model.v1x2.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;

@Data
@Builder(toBuilder = true)
public class CheckOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CHECK;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, CheckOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, CheckOpRequestPayload.class,
          CheckOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;
  private final UsageLimitsCount usageLimitsCount;
  private final CryptographicUsageMask cryptographicUsageMask;
  private final LeaseTime leaseTime;

  @Builder
  private CheckOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      UsageLimitsCount usageLimitsCount,
      CryptographicUsageMask cryptographicUsageMask,
      LeaseTime leaseTime
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.usageLimitsCount = usageLimitsCount;
    this.cryptographicUsageMask = cryptographicUsageMask;
    this.leaseTime = leaseTime;
    validate();
  }

  public static CheckOpRequestPayload of(List<KmipDataType> values) {
    var builder = CheckOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(UsageLimitsCount.kmipTag)) {
      builder.usageLimitsCount((UsageLimitsCount) map
          .get(UsageLimitsCount.kmipTag)
          .getFirst());
    }
    if (map.containsKey(CryptographicUsageMask.kmipTag)) {
      builder.cryptographicUsageMask((CryptographicUsageMask) map
          .get(CryptographicUsageMask.kmipTag)
          .getFirst());
    }
    if (map.containsKey(LeaseTime.kmipTag)) {
      builder.leaseTime((LeaseTime) map
          .get(LeaseTime.kmipTag)
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
            usageLimitsCount,
            cryptographicUsageMask,
            leaseTime)
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