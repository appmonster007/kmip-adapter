package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierJsonSerializer extends AbstractKmipJsonSerializer<MediaIdentifier, String> {

    public MediaIdentifierJsonSerializer() {
        super(MediaIdentifier::getValue);
    }
}