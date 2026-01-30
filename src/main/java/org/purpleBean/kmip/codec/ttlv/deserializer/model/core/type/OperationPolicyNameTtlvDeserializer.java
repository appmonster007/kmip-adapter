package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.OperationPolicyName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OperationPolicyNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OperationPolicyName, OperationPolicyName.OperationPolicyNameBuilder> {

    public OperationPolicyNameTtlvDeserializer() {
        super(OperationPolicyName.kmipTag, OperationPolicyName.encodingType);
    }

    @Override
    protected OperationPolicyName.OperationPolicyNameBuilder createBuilder() {
        return OperationPolicyName.builder();
    }

    @Override
    protected void setValue(OperationPolicyName.OperationPolicyNameBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected OperationPolicyName build(OperationPolicyName.OperationPolicyNameBuilder builder) {
        return builder.build();
    }
}