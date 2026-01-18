package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ApplicationSpecificInformationTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<ApplicationSpecificInformation, ApplicationSpecificInformation.ApplicationSpecificInformationBuilder> {

    public ApplicationSpecificInformationTtlvDeserializer() {
        super(ApplicationSpecificInformation.kmipTag);
    }

    @Override
    protected ApplicationSpecificInformation.ApplicationSpecificInformationBuilder createBuilder() {
        return ApplicationSpecificInformation.builder();
    }

    @Override
    protected void setValue(ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.APPLICATION_NAMESPACE ->
                    builder.applicationNamespace(mapper.readValue(p, ApplicationNamespace.class));
            case KmipTag.Standard.APPLICATION_DATA ->
                    builder.applicationData(mapper.readValue(p, ApplicationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ApplicationSpecificInformation build(ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return ApplicationSpecificInformation.encodingType;
    }
}