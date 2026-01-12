package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<MediaIdentifier, String> {

    public MediaIdentifierTtlvSerializer() {
        super(MediaIdentifier::getValue);
    }
}