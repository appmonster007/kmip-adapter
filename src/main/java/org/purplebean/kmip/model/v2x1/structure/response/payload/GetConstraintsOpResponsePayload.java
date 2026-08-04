package org.purplebean.kmip.model.v2x1.structure.response.payload;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v2x1.structure.Constraints;

/**
 * KMIP GetConstraints Response Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1/v3.0 spec §6.1.26 (v2.1 §6.1.22), Constraints is marked Required in the
 * spec's Response Payload table; however, the official OASIS v2.1 interop test vector
 * (TC-MD-21-21.xml) exercises a GetConstraints response with an empty payload (no Constraints
 * present), so this field is modeled as optional here to preserve interoperability with that
 * corpus.
 * <ul>
 *   <li>Constraints — Optional (see note above)</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class GetConstraintsOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.GET_CONSTRAINTS;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          GetConstraintsOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, GetConstraintsOpResponsePayload.class,
          GetConstraintsOpResponsePayload::of);
    }
  }

  private final Constraints constraints;

  @Builder
  private GetConstraintsOpResponsePayload(Constraints constraints) {
    this.constraints = constraints;
    validate();
  }

  /**
   * Returns the {@link GetConstraintsOpResponsePayload} instance wrapping the given value.
   */
  public static GetConstraintsOpResponsePayload of(List<KmipDataType> values) {
    var builder = GetConstraintsOpResponsePayload.builder();
    values.forEach(value -> {
      if (value instanceof Constraints) {
        builder.constraints((Constraints) value);
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
        .of(constraints)
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
