package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.CapabilityInformation;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v2_1.type.*;

public class CapabilityInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CapabilityInformation, CapabilityInformation.CapabilityInformationBuilder> {

    public CapabilityInformationTtlvDeserializer() {
        super(CapabilityInformation.kmipTag, CapabilityInformation.encodingType);
    }

    @Override
    protected CapabilityInformation.CapabilityInformationBuilder createBuilder() {
        return CapabilityInformation.builder();
    }

    @Override
    protected void setValue(CapabilityInformation.CapabilityInformationBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.STREAMING_CAPABILITY -> builder.streamingCapability(mapper.readValue(p, StreamingCapability.class));
            case KmipTag.Standard.ASYNCHRONOUS_CAPABILITY -> builder.asynchronousCapability(mapper.readValue(p, AsynchronousCapability.class));
            case KmipTag.Standard.ATTESTATION_CAPABILITY -> builder.attestationCapability(mapper.readValue(p, AttestationCapability.class));
            case KmipTag.Standard.BATCH_UNDO_CAPABILITY -> builder.batchUndoCapability(mapper.readValue(p, BatchUndoCapability.class));
            case KmipTag.Standard.BATCH_CONTINUE_CAPABILITY -> builder.batchContinueCapability(mapper.readValue(p, BatchContinueCapability.class));
            case KmipTag.Standard.UNWRAP_MODE -> builder.unwrapMode(mapper.readValue(p, UnwrapMode.class));
            case KmipTag.Standard.DESTROY_ACTION -> builder.destroyAction(mapper.readValue(p, DestroyAction.class));
            case KmipTag.Standard.SHREDDING_ALGORITHM -> builder.shreddingAlgorithm(mapper.readValue(p, ShreddingAlgorithm.class));
            case KmipTag.Standard.RNG_MODE -> builder.rngMode(mapper.readValue(p, RngMode.class));
            case KmipTag.Standard.QUANTUM_SAFE_CAPABILITY -> builder.quantumSafeCapability(mapper.readValue(p, QuantumSafeCapability.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CapabilityInformation build(CapabilityInformation.CapabilityInformationBuilder builder) {
        return builder.build();
    }
}