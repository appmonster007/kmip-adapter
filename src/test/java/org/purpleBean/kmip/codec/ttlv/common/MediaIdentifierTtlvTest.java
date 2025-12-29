package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MediaIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("MediaIdentifier TTLV Serialization Tests")
class MediaIdentifierTtlvTest extends AbstractTtlvSerializationSuite<MediaIdentifier> {

    @Override
    protected Class<MediaIdentifier> type() {
        return MediaIdentifier.class;
    }

    @Override
    protected MediaIdentifier createDefault() {
        return MediaIdentifier.builder().value("test-media-id").build();
    }

    @Override
    protected MediaIdentifier createVariant() {
        return MediaIdentifier.builder().value("another-media-id").build();
    }
}