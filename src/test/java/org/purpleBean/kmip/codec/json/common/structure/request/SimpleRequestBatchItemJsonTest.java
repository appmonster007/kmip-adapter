package org.purpleBean.kmip.codec.json.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("SimpleRequestBatchItem JSON Codec Tests")
class SimpleRequestBatchItemJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip JSON: SimpleRequestBatchItem")
    void roundTrip_json() {
        SimpleRequestBatchItem item = SimpleRequestBatchItem.builder().build();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, item, SimpleRequestBatchItem.class);
    }

    @Test
    @DisplayName("UnsupportedVersion: JSON serialization succeeds, deserialization fails")
    void unsupportedVersion_json_behavior() {
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            SimpleRequestBatchItem item = SimpleRequestBatchItem.builder().build();
            String json;
            try {
                json = jsonMapper.writeValueAsString(item);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            org.assertj.core.api.Assertions.assertThatThrownBy(
                    () -> jsonMapper.readValue(json, SimpleRequestBatchItem.class))
                    .isInstanceOf(Exception.class);
        });
    }
}
