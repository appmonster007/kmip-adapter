package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierTtlvDeserializer extends AbstractKmipTtlvDeserializer<MediaIdentifier, String> {

    public MediaIdentifierTtlvDeserializer() {
        super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType, String.class, value -> MediaIdentifier.builder().value(value).build());
    }
}