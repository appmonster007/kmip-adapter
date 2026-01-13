package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MediaIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MediaIdentifier JSON Serialization Tests")
class MediaIdentifierJsonTest extends AbstractJsonSerializationTestSuite<MediaIdentifier> {

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