package org.purplebean.kmip.model.v2x1.structure.request.payload;

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
import org.purplebean.kmip.model.v2x1.structure.Constraints;

/**
 * KMIP SetConstraints Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP spec §6.1.57, the request payload defines a single field:
 * <ul>
 *   <li>Constraints — the set of Constraints to apply during operations. Although the
 *   spec table marks this field REQUIRED, OASIS conformance test case TC-MD-22-21
 *   demonstrates a SetConstraints request with an empty payload (used to clear all
 *   constraints), so it is modeled here as optional to match observed wire behavior.</li>
 * </ul>
 *
 * <p>There is no Unique Identifier field in this payload; SetConstraints applies globally,
 * not to a specific Managed Object.
 */
@Data
@Builder(toBuilder = true)
public class SetConstraintsOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.SET_CONSTRAINTS;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          SetConstraintsOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, SetConstraintsOpRequestPayload.class,
          SetConstraintsOpRequestPayload::of);
    }
  }

  private final Constraints constraints;

  @Builder
  private SetConstraintsOpRequestPayload(Constraints constraints) {
    this.constraints = constraints;
    validate();
  }

  /**
   * Returns the {@link SetConstraintsOpRequestPayload} instance wrapping the given value.
   */
  public static SetConstraintsOpRequestPayload of(List<KmipDataType> values) {
    var builder = SetConstraintsOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(Constraints.kmipTag)) {
      builder.constraints((Constraints) map
          .get(Constraints.kmipTag)
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
        .of(constraints)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
