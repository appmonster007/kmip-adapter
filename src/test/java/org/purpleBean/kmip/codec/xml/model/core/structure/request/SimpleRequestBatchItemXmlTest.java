package org.purpleBean.kmip.codec.xml.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SimpleRequestBatchItem XML Serialization")
class SimpleRequestBatchItemXmlTest extends AbstractXmlSerializationTestSuite<SimpleRequestBatchItem> {

    @Override
    protected Class<SimpleRequestBatchItem> type() {
        return SimpleRequestBatchItem.class;
    }

    @Override
    protected SimpleRequestBatchItem createDefault() {
        return SimpleRequestBatchItem.builder()
                .requestPayloadStructure(SimpleRequestPayload.of())
                .build();
    }

    @Override
    protected SimpleRequestBatchItem createVariant() {
        return SimpleRequestBatchItem.builder()
                .requestPayloadStructure(SimpleRequestPayload.of())
                .build();
    }

    @Override
    protected boolean unsupportedSpecShouldFailSerialize() {
        return false; // model supports UnsupportedVersion
    }
}
