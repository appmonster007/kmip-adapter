package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ServerInformation;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ServerInformationTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<ServerInformation, ServerInformation.ServerInformationBuilder> {

    public ServerInformationTtlvDeserializer() {
        super(ServerInformation.kmipTag);
    }

    @Override
    protected ServerInformation.ServerInformationBuilder createBuilder() {
        return ServerInformation.builder();
    }

    @Override
    protected void setValue(ServerInformation.ServerInformationBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, KmipDataType.class));
    }

    @Override
    protected ServerInformation build(ServerInformation.ServerInformationBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return ServerInformation.encodingType;
    }
}