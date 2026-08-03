package org.purplebean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.QueryFunction;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;

/**
 * KMIP Query operation request payload (v2.1+, tag {@code 0x420079}).
 *
 * <p>Fields:
 * <ul>
 *   <li>{@code QueryFunction} (Enumeration, 1..N required) — what to query.</li>
 *   <li>{@code ObjectGroups} (Structure, optional) — filter defaults by group name (v2.1
 *   addition).</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class QueryOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.QUERY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, QueryOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, QueryOpRequestPayload.class,
          QueryOpRequestPayload::of);
    }
  }

  @NonNull
  @Singular
  private final List<QueryFunction> queryFunctions;

  private final ObjectGroups objectGroups;

  @Builder
  private QueryOpRequestPayload(
      @NonNull List<QueryFunction> queryFunctions,
      ObjectGroups objectGroups
  ) {
    this.queryFunctions = queryFunctions;
    this.objectGroups = objectGroups;
    validate();
  }

  /**
   * Returns the {@link QueryOpRequestPayload} instance wrapping the given value.
   */
  public static QueryOpRequestPayload of(List<KmipDataType> values) {
    var builder = QueryOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(QueryFunction.kmipTag)) {
      map
          .get(QueryFunction.kmipTag)
          .forEach(item -> builder.queryFunction((QueryFunction) item));
    }
    if (map.containsKey(ObjectGroups.kmipTag)) {
      builder.objectGroups((ObjectGroups) map
          .get(ObjectGroups.kmipTag)
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
        .of(queryFunctions, objectGroups)
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
