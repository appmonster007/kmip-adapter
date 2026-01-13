package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MediaIdentifier, String> {

    public MediaIdentifierJsonSerializer() {
        super(MediaIdentifier::getValue);
    }
}