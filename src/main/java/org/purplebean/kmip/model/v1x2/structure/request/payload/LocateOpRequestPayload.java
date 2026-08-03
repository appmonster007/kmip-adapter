package org.purplebean.kmip.model.v1x2.structure.request.payload;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.MaximumItems;
import org.purplebean.kmip.model.core.type.StorageStatusMask;

/**
 * KMIP LocateOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class LocateOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.LOCATE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, LocateOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, LocateOpRequestPayload.class,
          LocateOpRequestPayload::of);
    }
  }

  private final MaximumItems maximumItems;
  private final StorageStatusMask storageStatusMask;
  private final ObjectGroupMember objectGroupMember;
  @Singular
  private final List<Attribute> attributes;

  @Builder
  private LocateOpRequestPayload(
      MaximumItems maximumItems,
      StorageStatusMask storageStatusMask,
      ObjectGroupMember objectGroupMember,
      List<Attribute> attributes
  ) {
    this.maximumItems = maximumItems;
    this.storageStatusMask = storageStatusMask;
    this.objectGroupMember = objectGroupMember;
    this.attributes = (attributes == null) ? Collections.emptyList() : attributes;
    validate();
  }

  /**
   * Returns the {@link LocateOpRequestPayload} instance wrapping the given value.
   */
  public static LocateOpRequestPayload of(List<KmipDataType> values) {
    var builder = LocateOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(MaximumItems.kmipTag)) {
      builder.maximumItems((MaximumItems) map
          .get(MaximumItems.kmipTag)
          .getFirst());
    }
    if (map.containsKey(StorageStatusMask.kmipTag)) {
      builder.storageStatusMask((StorageStatusMask) map
          .get(StorageStatusMask.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ObjectGroupMember.kmipTag)) {
      builder.objectGroupMember((ObjectGroupMember) map
          .get(ObjectGroupMember.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Attribute.kmipTag)) {
      map
          .get(Attribute.kmipTag)
          .forEach(item -> builder.attribute((Attribute) item));
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // Add validation logic here
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
            maximumItems,
            storageStatusMask,
            objectGroupMember,
            attributes)
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
