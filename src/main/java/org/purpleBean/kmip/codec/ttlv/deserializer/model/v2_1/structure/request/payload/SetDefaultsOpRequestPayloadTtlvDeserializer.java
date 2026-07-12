package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetDefaultsOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SetDefaultsOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SetDefaultsOpRequestPayload, SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder> {

    public SetDefaultsOpRequestPayloadTtlvDeserializer() {
        super(SetDefaultsOpRequestPayload.kmipTag, SetDefaultsOpRequestPayload.encodingType);
    }

    @Override
    protected SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder createBuilder() {
        return SetDefaultsOpRequestPayload.builder();
    }

    @Override
    protected void setValue(SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.DEFAULTS_INFORMATION -> builder.defaultsInformation(mapper.readValue(p, DefaultsInformation.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SetDefaultsOpRequestPayload build(SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
