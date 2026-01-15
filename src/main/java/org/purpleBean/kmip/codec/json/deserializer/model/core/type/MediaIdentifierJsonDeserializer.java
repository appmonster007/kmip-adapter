package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;

public class MediaIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MediaIdentifier, String> {

    public MediaIdentifierJsonDeserializer() {
        super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType, String.class, value -> MediaIdentifier.builder().value(value).build());
    }
}