package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.MachineIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MachineIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MachineIdentifier, MachineIdentifier.MachineIdentifierBuilder> {

    public MachineIdentifierTtlvDeserializer() {
        super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType);
    }

    @Override
    protected MachineIdentifier.MachineIdentifierBuilder createBuilder() {
        return MachineIdentifier.builder();
    }

    @Override
    protected void setValue(MachineIdentifier.MachineIdentifierBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected MachineIdentifier build(MachineIdentifier.MachineIdentifierBuilder builder) {
        return builder.build();
    }
}
