package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ServerInformation;

import java.io.IOException;

public class ServerInformationXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ServerInformation, ServerInformation.ServerInformationBuilder> {

    public ServerInformationXmlDeserializer() {
        super(ServerInformation.kmipTag);
    }

    @Override
    protected ServerInformation.ServerInformationBuilder createBuilder() {
        return ServerInformation.builder();
    }

    @Override
    protected void setValue(ServerInformation.ServerInformationBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected ServerInformation build(ServerInformation.ServerInformationBuilder builder) {
        return builder.build();
    }
}