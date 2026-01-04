package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("MediaIdentifier Domain Tests")
class MediaIdentifierTest extends AbstractKmipDataTypeSuite<MediaIdentifier> {

    @Override
    protected Class<MediaIdentifier> type() {
        return MediaIdentifier.class;
    }

    @Override
    protected MediaIdentifier createDefault() {
        return MediaIdentifier.builder().value("test-media-id").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}