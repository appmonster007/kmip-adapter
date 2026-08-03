package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;
import org.purpleBean.kmip.model.core.enumeration.RngMode;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;
import org.purpleBean.kmip.model.v2_1.structure.CapabilityInformation;
import org.purpleBean.kmip.model.v2_1.type.AsynchronousCapability;
import org.purpleBean.kmip.model.v2_1.type.AttestationCapability;
import org.purpleBean.kmip.model.v2_1.type.BatchContinueCapability;
import org.purpleBean.kmip.model.v2_1.type.BatchUndoCapability;
import org.purpleBean.kmip.model.v2_1.type.QuantumSafeCapability;
import org.purpleBean.kmip.model.v2_1.type.StreamingCapability;

public class CapabilityInformationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CapabilityInformation,
        CapabilityInformation.CapabilityInformationBuilder> {

  public CapabilityInformationXmlDeserializer() {
    super(CapabilityInformation.kmipTag, CapabilityInformation.encodingType);
  }

  @Override
  protected CapabilityInformation.CapabilityInformationBuilder createBuilder() {
    return CapabilityInformation.builder();
  }

  @Override
  protected void setValue(CapabilityInformation.CapabilityInformationBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.STREAMING_CAPABILITY ->
          builder.streamingCapability(ctxt.readValue(p, StreamingCapability.class));
      case KmipTag.Standard.ASYNCHRONOUS_CAPABILITY ->
          builder.asynchronousCapability(ctxt.readValue(p, AsynchronousCapability.class));
      case KmipTag.Standard.ATTESTATION_CAPABILITY ->
          builder.attestationCapability(ctxt.readValue(p, AttestationCapability.class));
      case KmipTag.Standard.BATCH_UNDO_CAPABILITY ->
          builder.batchUndoCapability(ctxt.readValue(p, BatchUndoCapability.class));
      case KmipTag.Standard.BATCH_CONTINUE_CAPABILITY ->
          builder.batchContinueCapability(ctxt.readValue(p, BatchContinueCapability.class));
      case KmipTag.Standard.UNWRAP_MODE -> builder.unwrapMode(ctxt.readValue(p, UnwrapMode.class));
      case KmipTag.Standard.DESTROY_ACTION ->
          builder.destroyAction(ctxt.readValue(p, DestroyAction.class));
      case KmipTag.Standard.SHREDDING_ALGORITHM ->
          builder.shreddingAlgorithm(ctxt.readValue(p, ShreddingAlgorithm.class));
      case KmipTag.Standard.RNG_MODE -> builder.rngMode(ctxt.readValue(p, RngMode.class));
      case KmipTag.Standard.QUANTUM_SAFE_CAPABILITY ->
          builder.quantumSafeCapability(ctxt.readValue(p, QuantumSafeCapability.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CapabilityInformation build(
      CapabilityInformation.CapabilityInformationBuilder builder) {
    return builder.build();
  }
}