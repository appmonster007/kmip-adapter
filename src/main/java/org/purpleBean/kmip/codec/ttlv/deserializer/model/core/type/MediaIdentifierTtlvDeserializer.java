package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;

public class MediaIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MediaIdentifier, String> {

    public MediaIdentifierTtlvDeserializer() {
        super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType, String.class, value -> MediaIdentifier.builder().value(value).build());
    }
}