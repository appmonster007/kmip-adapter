package org.purpleBean.kmip.model.v2_1.structure;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;
import org.purpleBean.kmip.model.core.enumeration.RngMode;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;
import org.purpleBean.kmip.model.v2_1.type.AsynchronousCapability;
import org.purpleBean.kmip.model.v2_1.type.AttestationCapability;
import org.purpleBean.kmip.model.v2_1.type.BatchContinueCapability;
import org.purpleBean.kmip.model.v2_1.type.BatchUndoCapability;
import org.purpleBean.kmip.model.v2_1.type.QuantumSafeCapability;
import org.purpleBean.kmip.model.v2_1.type.StreamingCapability;

@Data
@Builder(toBuilder = true)
public class CapabilityInformation implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.CAPABILITY_INFORMATION.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, CapabilityInformation.class);
    }
  }

  private final StreamingCapability streamingCapability;
  private final AsynchronousCapability asynchronousCapability;
  private final AttestationCapability attestationCapability;
  private final BatchUndoCapability batchUndoCapability;
  private final BatchContinueCapability batchContinueCapability;
  private final UnwrapMode unwrapMode;
  private final DestroyAction destroyAction;
  private final ShreddingAlgorithm shreddingAlgorithm;
  private final RngMode rngMode;
  private final QuantumSafeCapability quantumSafeCapability;

  @Builder
  private CapabilityInformation(StreamingCapability streamingCapability,
                                AsynchronousCapability asynchronousCapability,
                                AttestationCapability attestationCapability,
                                BatchUndoCapability batchUndoCapability,
                                BatchContinueCapability batchContinueCapability,
                                UnwrapMode unwrapMode,
                                DestroyAction destroyAction, ShreddingAlgorithm shreddingAlgorithm,
                                RngMode rngMode, QuantumSafeCapability quantumSafeCapability) {
    this.streamingCapability = streamingCapability;
    this.asynchronousCapability = asynchronousCapability;
    this.attestationCapability = attestationCapability;
    this.batchUndoCapability = batchUndoCapability;
    this.batchContinueCapability = batchContinueCapability;
    this.unwrapMode = unwrapMode;
    this.destroyAction = destroyAction;
    this.shreddingAlgorithm = shreddingAlgorithm;
    this.rngMode = rngMode;
    this.quantumSafeCapability = quantumSafeCapability;
    validate();
  }

  public static CapabilityInformation of(@lombok.NonNull KmipDataType value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid value: " + value);
    }
    var builder = CapabilityInformation.builder();
    for (KmipDataType field : structure.getValue()) {
      if (field instanceof StreamingCapability c) {
        builder.streamingCapability(c);
      } else if (field instanceof AsynchronousCapability c) {
        builder.asynchronousCapability(c);
      } else if (field instanceof AttestationCapability c) {
        builder.attestationCapability(c);
      } else if (field instanceof BatchUndoCapability c) {
        builder.batchUndoCapability(c);
      } else if (field instanceof BatchContinueCapability c) {
        builder.batchContinueCapability(c);
      } else if (field instanceof UnwrapMode m) {
        builder.unwrapMode(m);
      } else if (field instanceof DestroyAction a) {
        builder.destroyAction(a);
      } else if (field instanceof ShreddingAlgorithm a) {
        builder.shreddingAlgorithm(a);
      } else if (field instanceof RngMode m) {
        builder.rngMode(m);
      } else if (field instanceof QuantumSafeCapability c) {
        builder.quantumSafeCapability(c);
      }
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
        .of(streamingCapability, asynchronousCapability, attestationCapability,
            batchUndoCapability, batchContinueCapability, unwrapMode, destroyAction,
            shreddingAlgorithm, rngMode, quantumSafeCapability)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
