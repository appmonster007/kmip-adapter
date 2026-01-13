package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MediaIdentifier, String> {

    public MediaIdentifierJsonDeserializer() {
        super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType, String.class, value -> MediaIdentifier.builder().value(value).build());
    }
}