package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MediaIdentifier TTLV Serialization Tests")
class MediaIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<MediaIdentifier> {

    @Override
    public Class<MediaIdentifier> type() {
        return MediaIdentifier.class;
    }

    @Override
    public MediaIdentifier createDefault() {
        return MediaIdentifier.builder().value("test-media-id").build();
    }

    @Override
    public MediaIdentifier createVariant() {
        return MediaIdentifier.builder().value("another-media-id").build();
    }
}